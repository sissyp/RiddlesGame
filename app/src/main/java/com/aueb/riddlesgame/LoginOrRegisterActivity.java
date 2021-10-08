package com.aueb.riddlesgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.aueb.riddlesgame.databinding.ActivityLoginOrRegisterBinding;

public class LoginOrRegisterActivity extends AppCompatActivity {
    ActivityLoginOrRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_or_register);
        setUpClickListeners();
    }

    private void setUpClickListeners() {
        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin();
            }
        });

        binding.createAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateAccount();
            }
        });
    }

    public void onLogin() {
        startActivity(new Intent(LoginOrRegisterActivity.this, LoginActivity.class));
        finish();
    }

    public void onCreateAccount() {
        startActivity(new Intent(LoginOrRegisterActivity.this, MetamaskActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
