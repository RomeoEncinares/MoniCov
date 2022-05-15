package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;
    Button  scanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        loginButton = findViewById(R.id.btnLoginLanding);
        registerButton = findViewById(R.id.btnRegisterLanding);
        scanButton = findViewById(R.id.btnScan);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingActivity.this, LoginActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingActivity.this, RegisterActivity.class));
            }
        });

        scanButton.setOnClickListener(view -> {
            startActivity(new Intent(LandingActivity.this, acquaintanceScanActivity.class));
        });
    }
}