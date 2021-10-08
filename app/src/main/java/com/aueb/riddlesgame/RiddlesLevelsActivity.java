package com.aueb.riddlesgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.aueb.riddlesgame.databinding.ActivityRiddlesLevelsBinding;

public class RiddlesLevelsActivity extends AppCompatActivity {

    ActivityRiddlesLevelsBinding binding;
    String mnemonic;
    String name;
    int level;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_riddles_levels);
        Bundle extras = getIntent().getExtras();
        mnemonic = extras.getString("mnemonic");
        name = extras.getString("avatar_name");
        level = extras.getInt("level");
        id = extras.getInt("avatar_id");
        setNameAndLevel();
        setClickListeners();
    }

    private void setNameAndLevel(){
        binding.avatarName.setText(name);
        binding.avatarLevel.setText(String.valueOf(level));
    }

    private void setClickListeners() {
        binding.easyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RiddlesLevelsActivity.this, DisplayRiddlesActivity.class);
                intent.putExtra("mnemonic",mnemonic);
                intent.putExtra("avatar_name", name);
                intent.putExtra("avatar_level",level);
                intent.putExtra("level","EASY");
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });

        binding.mediumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RiddlesLevelsActivity.this, DisplayRiddlesActivity.class);
                intent.putExtra("mnemonic",mnemonic);
                intent.putExtra("avatar_name", name);
                intent.putExtra("avatar_level",level);
                intent.putExtra("level","MEDIUM");
                startActivity(intent);
                finish();
            }
        });

        binding.hardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RiddlesLevelsActivity.this, DisplayRiddlesActivity.class);
                intent.putExtra("mnemonic",mnemonic);
                intent.putExtra("avatar_name", name);
                intent.putExtra("avatar_level",level);
                intent.putExtra("level","HARD");
                startActivity(intent);
                finish();
            }
        });
    }
}
