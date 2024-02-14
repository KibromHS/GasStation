package com.example.gasstation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    private TextView toLoginBtn;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();

        signupBtn.setOnClickListener(view -> {
            Intent i = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(i);
        });

        toLoginBtn.setOnClickListener(view -> {
            Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(i);
        });
    }

    private void initView() {
        toLoginBtn = findViewById(R.id.tvToLoginBtn);
        signupBtn = findViewById(R.id.signupBtn);
    }
}