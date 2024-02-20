package com.example.gasstation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gasstation.models.UserModel;
import com.example.gasstation.utils.FirebaseUtil;
import com.example.gasstation.utils.UserPreferences;

public class MainActivity extends AppCompatActivity {
    private TextView toMapBtn;
    private ImageView logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

//        UserModel userModel = FirebaseUtil.getUser();
//
//        if (userModel == null) {
//            Intent i = new Intent(this, LoginPhoneActivity.class);
//            startActivity(i);
//            finish();
//            return;
//        }
//        UserPreferences.init(getApplicationContext());
//        UserPreferences.setUser(userModel);

        toMapBtn.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, MapActivity.class);
            startActivity(i);
        });

        logoutBtn.setOnClickListener(view -> {
            FirebaseUtil.signOut();
            Intent i = new Intent(MainActivity.this, LoginPhoneActivity.class);
            startActivity(i);
        });
    }

    private void initView() {
        toMapBtn = findViewById(R.id.button);
        logoutBtn = findViewById(R.id.logoutBtn);
    }
}