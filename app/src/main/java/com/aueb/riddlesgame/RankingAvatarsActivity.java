package com.aueb.riddlesgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.math.BigInteger;
import java.util.ArrayList;


import org.w3c.dom.Text;
import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

public class RankingAvatarsActivity extends AppCompatActivity {
    private static final String CONTRACT_ADDRESS = "0xFFFD798b32d1B034f70Ec1C06C547A5eA9Fa9E20";
    TextView first;
    TextView second;
    TextView third;
    String mnemonic;
    Web3j web3;
    ArrayList<Avatar> avatars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Bundle extras = getIntent().getExtras();
        mnemonic = extras.getString("mnemonic");
        setContentView(R.layout.activity_ranking_avatars);
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        getAvatars();
    }

    private void getAvatars() {
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                try {
                    web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/78373ee3cac447d7afd989b684806a0f"));
                    try {
                        Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
                    } catch (Exception e) {
                        toastAsync(e.getMessage());
                    }

                    String password = null;
                    int HARDENED_BIT = 0x80000000;

                    // Generate a BIP32 master keypair from the mnemonic phrase
                    Bip32ECKeyPair masterKeypair = Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(mnemonic, password));

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
                        for (int i = 0; i < avatars_length.intValue(); i++) {
                            id = i;
                            name = contract.getName(new BigInteger(String.valueOf(id))).send();
                            level = contract.getLevel((new BigInteger(String.valueOf(id)))).send().intValue();
                            avatars.add(new Avatar(name, level, id));
                        }

                        avatars = sortByLevel(avatars_length.intValue());
                        first.setText(avatars.get(0).username);
                        second.setText(avatars.get(1).username);
                        third.setText(avatars.get(2).username);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };thread2.start();
    }

    ArrayList<Avatar> sortByLevel(int l) {
        Avatar temp;
        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            int i = 0;
            while(i<l-1) {
                if (avatars.get(i).compareTo(avatars.get(i + 1)) > 0) {
                    temp = avatars.get(i);
                    avatars.set(i, avatars.get(i + 1));
                    avatars.set(i + 1, temp);
                    sorted = false;
                }
                i++;
            }
        }
        return avatars;
    }

    public void toastAsync(String message) {
        runOnUiThread(() -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }

}

