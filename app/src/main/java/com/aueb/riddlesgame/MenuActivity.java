package com.aueb.riddlesgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.aueb.riddlesgame.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {
    ActivityMenuBinding binding;
    String mnemonic;
    String name;
    int level;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu);
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
        binding.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RiddlesLevelsActivity.class);
                intent.putExtra("mnemonic",mnemonic);
                intent.putExtra("avatar_name", name);
                intent.putExtra("avatar_level",level);
                intent.putExtra("avatar_id",id);
                startActivity(intent);
                finish();
            }
        });

        binding.settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, LoginOrRegisterActivity.class));
                finish();
            }
        });

        binding.rankingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, LoginOrRegisterActivity.class));
                finish();
            }
        });
    }

}
