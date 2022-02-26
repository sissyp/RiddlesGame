package com.aueb.riddlesgame;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.aueb.riddlesgame.databinding.ActivityRiddleBinding;

import org.web3j.crypto.Bip32ECKeyPair;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;

import io.ipfs.api.IPFS;

import io.ipfs.multihash.Multihash;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;


public class RiddleActivity extends AppCompatActivity {
    private static final String CONTRACT_ADDRESS = "0x27232C655b8C2874EBaB7a1aA5Cc11C8940670b0";
    ActivityRiddleBinding binding;
    IPFS ipfs;
    String mnemonic;
    String level;
    String riddle_number;
    String avatar_name;
    int avatar_level;
    String digit_str = "";
    Web3j web3;
    int riddle_solution = 45;
    int id;
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(4_100_000_000L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(3_000_000);

    private final static String RECIPIENT = CONTRACT_ADDRESS;

    String hash = "QmVV8SErurxVy4Xep6f48BX99iTrWu3c5biEQDoUoLT7rZ";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_riddle);
        Bundle extras = getIntent().getExtras();
        mnemonic = extras.getString("mnemonic");
        riddle_number = extras.getString("riddle_number");
        level = extras.getString("level");
        avatar_name = extras.getString("avatar_name");
        avatar_level = extras.getInt("avatar_level");
        id = extras.getInt("avatar_id");
        readRiddle();
        setClickListeners();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void readRiddle() {
        try {
            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    try {
                        long start = System.currentTimeMillis();
                        ipfs = new IPFS("/dnsaddr/ipfs.infura.io/tcp/5001/https");
                        Multihash multihash = Multihash.fromBase58(hash);
                        byte[] content = ipfs.cat(multihash);
                        String content_str = new String(content);
                        long elapsedTimeMillis = System.currentTimeMillis()-start;
                        Log.d("ExtractFromIPFS",String.valueOf(elapsedTimeMillis));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.riddleText.setText(content_str);
                            }
                        });
                    }
                    catch (IOException ex) {
                        throw new RuntimeException("Error while communicating with the IPFS node", ex);
                    }
                }
            };thread2.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setButtonText(int digit){
        if (digit == 100)
            digit_str = "";
        else {
            digit_str += String.valueOf(digit);
            binding.solution.setText(digit_str);
        }
    }

    private void setClickListeners() {

        binding.btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonText(0);
            }
        });

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonText(1);
            }
        });

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonText(2);
            }
        });

        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonText(3);
            }
        });

        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonText(4);
            }
        });

        binding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonText(5);
            }
        });

        binding.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonText(6);
            }
        });

        binding.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonText(7);
            }
        });

        binding.btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonText(8);
            }
        });

        binding.btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonText(9);
            }
        });

        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.solution.setText("");
                setButtonText(100);
            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int user_solution = Integer.parseInt(binding.solution.getText().toString());
                //if(user_solution == riddle_solution) {
                    try {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                long start1 = System.currentTimeMillis();
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
                                ContractGasProvider gasProvider = new StaticGasProvider(BigInteger.valueOf(4_100_000_000L),BigInteger.valueOf(4_000_000));

                                toastAsync("Connected to your metamask wallet account: " + credentials.getAddress());

                                PlayerContract_sol_PlayerContract contract = PlayerContract_sol_PlayerContract.load(CONTRACT_ADDRESS, web3, credentials, gasProvider);


                                try {
                                    TransactionManager transactionManager = new RawTransactionManager(
                                            web3,
                                            credentials
                                    );

                                    Transfer transfer = new Transfer(web3, transactionManager);

                                    TransactionReceipt transactionReceipt = transfer.sendFunds(
                                            RECIPIENT,
                                            BigDecimal.ZERO,
                                            Convert.Unit.ETHER,
                                            GAS_PRICE,
                                            GAS_LIMIT
                                    ).send();
                                    contract.solve(new BigInteger(String.valueOf(id))).send();
                                    //Log.d("Transaction = " , transactionReceipt.getTransactionHash());
                                    // String x = contract.solve(new BigInteger(String.valueOf(id))).send().getGasUsed().toString();
                                    int numberOfriddles = contract.getSolved(new BigInteger(String.valueOf(id))).send().intValue();
                                    long elapsedTimeMillis1 = System.currentTimeMillis()-start1;
                                    Log.d("RiddleActivity", String.valueOf(elapsedTimeMillis1));
                                    Log.d("number", String.valueOf(numberOfriddles));
                                    Intent intent = new Intent(RiddleActivity.this, RiddleSolvedActivity.class);
                                    intent.putExtra("mnemonic", mnemonic);
                                    intent.putExtra("avatar_name", avatar_name);
                                    intent.putExtra("avatar_level", avatar_level);
                                    intent.putExtra("level", level);
                                    intent.putExtra("avatar_id", id);
                                    startActivity(intent);
                                    finish();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        }).start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                //}
                //else
                    //toastAsync("Wrong answer. Try again...");
            }
        });
    }

    public void toastAsync(String message) {
        runOnUiThread(() -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }

}
