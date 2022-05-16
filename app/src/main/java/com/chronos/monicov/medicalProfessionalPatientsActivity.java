package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class medicalProfessionalPatientsActivity extends AppCompatActivity {

    ImageButton homeButton, profileButton, medicalProfessionalButton, settingsButton, patientsButton, addPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_patients);

        homeButton = findViewById(R.id.medicalHomeButton);
        profileButton = findViewById(R.id.medicalProfileButton);
        patientsButton = findViewById(R.id.medicalPatientsButton);
        medicalProfessionalButton = findViewById(R.id.medicalNotificationButton);
        settingsButton = findViewById(R.id.medicalSettingsButton);

        addPatient = findViewById(R.id.addPatientButton);

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalPatientsActivity.this, medicalProfessionalHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalPatientsActivity.this, medicalProfessionalProfileActivity.class));
        });

        patientsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalPatientsActivity.this, medicalProfessionalPatientsActivity.class));
        });

        medicalProfessionalButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalPatientsActivity.this, medicalProfessionalNotificationsActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalPatientsActivity.this, medicalProfessionalSettingsActivity.class));
        });

        addPatient.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalPatientsActivity.this, medicalProfessionalAddPatientActivity.class));
        });

    }
}