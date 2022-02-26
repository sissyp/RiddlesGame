package com.aueb.riddlesgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

public class MetamaskActivity extends AppCompatActivity {
    private Web3j web3;
    String mnemonic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metamask);
    }

    public void connectToEthNetwork() {
        toastAsync("Connecting to Ethereum network...");
        web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/78373ee3cac447d7afd989b684806a0f"));
        try {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if(!clientVersion.hasError()){
                toastAsync("Connected!");
            }
            else {
                toastAsync(clientVersion.getError().getMessage());
            }
        }
        catch (Exception e) {
            toastAsync(e.getMessage());
        }
    }

    public void connectWithMetamask(View v) throws ExecutionException, InterruptedException, IOException, TransactionException {
        connectToEthNetwork();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
                public void run() {
                long start = System.currentTimeMillis();
                EditText editText = findViewById(R.id.privateKey);

                mnemonic = editText.getText().toString();
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

                toastAsync("Connected to your metamask wallet account: " + credentials.getAddress());
                long elapsedTimeMillis = System.currentTimeMillis()-start;
                Log.d("MetaMask",String.valueOf(elapsedTimeMillis));
                onConnectedWithMetamask();
            }
        }, 3000);
    }

    public void onConnectedWithMetamask(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MetamaskActivity.this, RegisterActivity.class);
                intent.putExtra("mnemonic",mnemonic);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void downloadMetamask(View v){
        Uri webpage = Uri.parse("https://metamask.io/download");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }

    public void toastAsync(String message) {
        runOnUiThread(() -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }
}