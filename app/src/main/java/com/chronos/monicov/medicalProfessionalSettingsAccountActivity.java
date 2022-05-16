package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class medicalProfessionalSettingsAccountActivity extends AppCompatActivity {

    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_settings_account);

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsAccountActivity.this, medicalProfessionalSettingsActivity.class));
        });

    }
}