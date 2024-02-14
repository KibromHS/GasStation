package com.example.gasstation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView toMapBtn;
    private ImageView logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        toMapBtn.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, MapActivity.class);
            startActivity(i);
        });

        logoutBtn.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        });
    }

    private void initView() {
        toMapBtn = findViewById(R.id.button);
        logoutBtn = findViewById(R.id.logoutBtn);
    }
}