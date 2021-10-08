package com.aueb.riddlesgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.aueb.riddlesgame.databinding.ActivityRegisterBinding;

import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

public class RegisterActivity extends AppCompatActivity {
    private static final String CONTRACT_ADDRESS = "0xFFFD798b32d1B034f70Ec1C06C547A5eA9Fa9E20";
    ActivityRegisterBinding binding;
    Web3j web3;
    String mnemonic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        Bundle extras = getIntent().getExtras();
        mnemonic = extras.getString("mnemonic");
        setClickListeners();
    }

    private void setClickListeners() {
        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUsername().isEmpty()) {
                    showEmptyFieldsDetected();
                    return;
                }

                try{
                    new Thread( new Runnable() {
                        @Override
                        public void run() {
                            web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/78373ee3cac447d7afd989b684806a0f"));
                            try {
                                Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
                            }
                            catch (Exception e) {
                                toastAsync(e.getMessage());
                            }

                            String password = null;
                            int HARDENED_BIT = 0x80000000;

                            // Generate a BIP32 master keypair from the mnemonic phrase
                            Bip32ECKeyPair masterKeypair = Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(mnemonic,password));

                            // custom derivation path
                            int[] derivationPath = {44 | HARDENED_BIT, 60 | HARDENED_BIT, 0 | HARDENED_BIT, 0, 0};

                            // Derived the key using the derivation path
                            Bip32ECKeyPair derivedKeyPair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, derivationPath);

                            // Load the wallet for the derived key
                            Credentials credentials = Credentials.create(derivedKeyPair);
                            ContractGasProvider gasProvider = new StaticGasProvider(BigInteger.valueOf(4_100_000_000L),BigInteger.valueOf(4_000_000));

                            toastAsync("Connected to your metamask wallet account: " + credentials.getAddress());

                            PlayerContract_sol_PlayerContract contract = PlayerContract_sol_PlayerContract.load(CONTRACT_ADDRESS, web3, credentials, gasProvider);

                            String name = "";
                            int level = 0;

                            try {
                                BigInteger avatars_length = contract.getAvatarsLength().send();
                                Log.d("avatars_length",avatars_length.toString());
                                toastAsync(avatars_length.toString());
                                boolean nameNotTaken = true;
                                for (int i=0;i<avatars_length.intValue();i++) {
                                    if (getUsername().equals(contract.getName(new BigInteger(String.valueOf(i))).send())) {
                                        nameNotTaken = false;
                                        break;
                                    }
                                }
                                if (!nameNotTaken) {
                                    toastAsync("This username is already taken.Choose a different one to continue...");
                                    return;
                                }

                                contract.createNewAvatar(getUsername()).send();
                                BigInteger avatarId = contract.getAvatarId().send().add(new BigInteger("-1"));
                                toastAsync(avatarId.toString());
                                name = contract.getName(avatarId).send();
                                Log.d("avatar_name", name);
                                toastAsync(name);
                                level = contract.getLevel(avatarId).send().intValue();
                                Log.d("avatar_level", String.valueOf(level));

                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
                            intent.putExtra("mnemonic",mnemonic);
                            intent.putExtra("avatar_name", name);
                            intent.putExtra("avatar_level",level);
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


    public void showEmptyFieldsDetected() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        DialogWindow.showDialog(this,
                "Register failed",
                "Empty fields detected. \nFill them to register..",
                "OKAY",
                runnable);
    }

    public void toastAsync(String message) {
        runOnUiThread(() -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }

}
