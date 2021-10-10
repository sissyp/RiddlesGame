package com.aueb.riddlesgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.aueb.riddlesgame.databinding.ActivityRiddleSolvedBinding;


public class RiddleSolvedActivity extends AppCompatActivity {
    ActivityRiddleSolvedBinding binding;
    String mnemonic;
    String level;
    String avatar_name;
    int avatar_level;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_riddle_solved);
        Bundle extras = getIntent().getExtras();
        mnemonic = extras.getString("mnemonic");
        avatar_name = extras.getString("avatar_name");
        avatar_level = extras.getInt("avatar_level");
        level = extras.getString("level");
        id = extras.getInt("avatar_id");
        setClickListeners();
    }

    private void setClickListeners() {

        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RiddleSolvedActivity.this, DisplayRiddlesActivity.class);
                intent.putExtra("mnemonic", mnemonic);
                intent.putExtra("avatar_name", avatar_name);
                intent.putExtra("avatar_level", avatar_level);
                intent.putExtra("level", level);
                intent.putExtra("avatar_id", id);
                startActivity(intent);
                finish();
            }
        });
    }
}
