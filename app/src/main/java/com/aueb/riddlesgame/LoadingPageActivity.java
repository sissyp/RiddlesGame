package com.aueb.riddlesgame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.aueb.riddlesgame.databinding.ActivityLoadingPageBinding;

public class LoadingPageActivity extends AppCompatActivity {
    ActivityLoadingPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loading_page);
        simulateDelay();
    }

    public void simulateDelay(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingPageActivity.this, LoginOrRegisterActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}