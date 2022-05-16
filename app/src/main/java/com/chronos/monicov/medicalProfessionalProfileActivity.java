package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class medicalProfessionalProfileActivity extends AppCompatActivity {

    ImageButton homeButton, profileButton, medicalProfessionalButton, settingsButton, patientsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_profile);

        homeButton = findViewById(R.id.medicalHomeButton);
        profileButton = findViewById(R.id.medicalProfileButton);
        patientsButton = findViewById(R.id.medicalPatientsButton);
        medicalProfessionalButton = findViewById(R.id.medicalNotificationButton);
        settingsButton = findViewById(R.id.medicalSettingsButton);

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalProfileActivity.this, medicalProfessionalHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalProfileActivity.this, medicalProfessionalProfileActivity.class));
        });

        patientsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalProfileActivity.this, medicalProfessionalPatientsActivity.class));
        });

        medicalProfessionalButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalProfileActivity.this, medicalProfessionalNotificationsActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalProfileActivity.this, medicalProfessionalSettingsActivity.class));
        });

    }
}