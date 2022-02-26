package com.aueb.riddlesgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.aueb.riddlesgame.databinding.ActivityLoginBinding;

import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

public class LoginActivity extends AppCompatActivity {
    private static final String CONTRACT_ADDRESS = "0x27232C655b8C2874EBaB7a1aA5Cc11C8940670b0";
    ActivityLoginBinding binding;
    String mnemonic;
    Web3j web3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setClickListeners();
    }

    private void setClickListeners() {
        binding.loginInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getUsername().isEmpty() || getMnemonic().isEmpty()) {
                    showEmptyFieldsDetected();
                    return;
                }

                try{
                    new Thread( new Runnable() {
                        @Override
                        public void run() {
                            long start = System.currentTimeMillis();
                            web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/78373ee3cac447d7afd989b684806a0f"));
                            try {
                                Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
                            }
                            catch (Exception e) {
                                toastAsync(e.getMessage());
                            }

                            String password = null;
                            int HARDENED_BIT = 0x80000000;
                            mnemonic = getMnemonic();

                            // Generate a BIP32 master keypair from the mnemonic phrase
                            Bip32ECKeyPair masterKeypair = Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(mnemonic,password));

                            // custom derivation path
                            int[] derivationPath = {44 | HARDENED_BIT, 60 | HARDENED_BIT, 0 | HARDENED_BIT, 0, 0};

                            // Derived the key using the derivation path
                            Bip32ECKeyPair derivedKeyPair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, derivationPath);

                            // Load the wallet for the derived key
                            Credentials credentials = Credentials.create(derivedKeyPair);
                            ContractGasProvider gasProvider = new DefaultGasProvider();

                            toastAsync("Connected to your metamask wallet account: " + credentials.getAddress());

                            PlayerContract_sol_PlayerContract contract = PlayerContract_sol_PlayerContract.load(CONTRACT_ADDRESS, web3, credentials, gasProvider);

                            String name = "";
                            int level = 0;
                            int id = 0;

                            try {
                                BigInteger avatars_length = contract.getAvatarsLength().send();
                                Log.d("avatars_length",avatars_length.toString());
                                toastAsync(avatars_length.toString());
                                boolean invalidUsername = true;
                                for (int i=0;i<avatars_length.intValue();i++) {
                                    if (getUsername().equals(contract.getName(new BigInteger(String.valueOf(i))).send())) {
                                        invalidUsername = false;
                                        id = i ;
                                        break;
                                    }
                                }
                                if (invalidUsername) {
                                    toastAsync("This username is invalid.Please enter a valid username...");
                                    return;
                                }

                                name = contract.getName(new BigInteger(String.valueOf(id))).send();
                                Log.d("avatar_name", name);
                                toastAsync(name);
                                level = contract.getLevel((new BigInteger(String.valueOf(id)))).send().intValue();
                                Log.d("avatar_level", String.valueOf(level));
                                Log.d("avatar_id",String.valueOf(id));

                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                            long elapsedTimeMillis = System.currentTimeMillis()-start;
                            Log.d("Login", String.valueOf(elapsedTimeMillis));
                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                            intent.putExtra("mnemonic",mnemonic);
                            intent.putExtra("avatar_name", name);
                            intent.putExtra("avatar_level",level);
                            intent.putExtra("avatar_id",id);
                            startActivity(intent);
                            finish();

                        }} ).start();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String getUsername() {
        return binding.usernameEt.getText().toString();
    }

    public void setUsername(String username) {
        binding.usernameEt.setText(username);
    }

    public String getMnemonic(){return binding.recoveryPhrase.getText().toString();}

    public void toastAsync(String message) {
        runOnUiThread(() -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }

    public void showEmptyFieldsDetected() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        DialogWindow.showDialog(this,
                "Login failed",
                "Empty fields detected. \nFill them to login..",
                "OKAY",
                runnable);
    }

}
