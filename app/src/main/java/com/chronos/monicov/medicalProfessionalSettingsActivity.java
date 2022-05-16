package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class medicalProfessionalSettingsActivity extends AppCompatActivity {

    ImageButton homeButton, profileButton, medicalProfessionalButton, settingsButton, patientsButton, accountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_settings);

        homeButton = findViewById(R.id.medicalHomeButton);
        profileButton = findViewById(R.id.medicalProfileButton);
        patientsButton = findViewById(R.id.medicalPatientsButton);
        medicalProfessionalButton = findViewById(R.id.medicalNotificationButton);
        settingsButton = findViewById(R.id.medicalSettingsButton);

        accountButton = findViewById(R.id.angleRightbutton);

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalProfileActivity.class));
        });

        patientsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalPatientsActivity.class));
        });

        medicalProfessionalButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalNotificationsActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalSettingsActivity.class));
        });

        accountButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalSettingsAccountActivity.class));
        });

    }
}