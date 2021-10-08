package com.aueb.riddlesgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.aueb.riddlesgame.databinding.ActivityDisplayRiddlesBinding;


public class DisplayRiddlesActivity extends AppCompatActivity {
    ActivityDisplayRiddlesBinding binding;
    String mnemonic;
    String name;
    String level;
    int avatar_level;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_riddles);
        Bundle extras = getIntent().getExtras();
        mnemonic = extras.getString("mnemonic");
        name = extras.getString("avatar_name");
        avatar_level = extras.getInt("avatar_level");
        level = extras.getString("level");
        id = extras.getInt("avatar_id");
        setNameAndLevel();
        setClickListeners();
    }

    private void setNameAndLevel(){
        binding.avatarName.setText(name);
        binding.avatarLevel.setText(String.valueOf(avatar_level));
        binding.level.setText(level);
    }

    private void setClickListeners() {
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayRiddlesActivity.this, RiddleActivity.class);
                intent.putExtra("mnemonic", mnemonic);
                intent.putExtra("riddle_number","1");
                intent.putExtra("level",level);
                intent.putExtra("avatar_name",name);
                intent.putExtra("avatar_level",avatar_level);
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayRiddlesActivity.this, RiddleActivity.class);
                intent.putExtra("mnemonic", mnemonic);
                intent.putExtra("riddle_number","2");
                intent.putExtra("level",level);
                intent.putExtra("avatar_name",name);
                intent.putExtra("avatar_level",avatar_level);
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });

        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayRiddlesActivity.this, RiddleActivity.class);
                intent.putExtra("mnemonic", mnemonic);
                intent.putExtra("riddle_number","3");
                intent.putExtra("level",level);
                intent.putExtra("avatar_name",name);
                intent.putExtra("avatar_level",avatar_level);
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });

        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayRiddlesActivity.this, RiddleActivity.class);
                intent.putExtra("mnemonic", mnemonic);
                intent.putExtra("riddle_number","4");
                intent.putExtra("level",level);
                intent.putExtra("avatar_name",name);
                intent.putExtra("avatar_level",avatar_level);
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });

        binding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayRiddlesActivity.this, RiddleActivity.class);
                intent.putExtra("mnemonic", mnemonic);
                intent.putExtra("riddle_number","5");
                intent.putExtra("level",level);
                intent.putExtra("avatar_name",name);
                intent.putExtra("avatar_level",avatar_level);
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });

        binding.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayRiddlesActivity.this, RiddleActivity.class);
                intent.putExtra("mnemonic", mnemonic);
                intent.putExtra("riddle_number","6");
                intent.putExtra("level",level);
                intent.putExtra("avatar_name",name);
                intent.putExtra("avatar_level",avatar_level);
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });

        binding.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayRiddlesActivity.this, RiddleActivity.class);
                intent.putExtra("mnemonic", mnemonic);
                intent.putExtra("riddle_number","7");
                intent.putExtra("level",level);
                intent.putExtra("avatar_name",name);
                intent.putExtra("avatar_level",avatar_level);
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });

        binding.btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayRiddlesActivity.this, RiddleActivity.class);
                intent.putExtra("mnemonic", mnemonic);
                intent.putExtra("riddle_number","8");
                intent.putExtra("level",level);
                intent.putExtra("avatar_name",name);
                intent.putExtra("avatar_level",avatar_level);
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });

        binding.btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayRiddlesActivity.this, RiddleActivity.class);
                intent.putExtra("mnemonic", mnemonic);
                intent.putExtra("riddle_number","9");
                intent.putExtra("level",level);
                intent.putExtra("avatar_name",name);
                intent.putExtra("avatar_level",avatar_level);
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });

        binding.btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayRiddlesActivity.this, RiddleActivity.class);
                intent.putExtra("mnemonic", mnemonic);
                intent.putExtra("riddle_number","10");
                intent.putExtra("level",level);
                intent.putExtra("avatar_name",name);
                intent.putExtra("avatar_level",avatar_level);
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });

        binding.btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayRiddlesActivity.this, RiddleActivity.class);
                intent.putExtra("mnemonic", mnemonic);
                intent.putExtra("riddle_number","11");
                intent.putExtra("level",level);
                intent.putExtra("avatar_name",name);
                intent.putExtra("avatar_level",avatar_level);
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });

        binding.btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayRiddlesActivity.this, RiddleActivity.class);
                intent.putExtra("mnemonic", mnemonic);
                intent.putExtra("riddle_number","12");
                intent.putExtra("level",level);
                intent.putExtra("avatar_name",name);
                intent.putExtra("avatar_level",avatar_level);
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });
    }
}
