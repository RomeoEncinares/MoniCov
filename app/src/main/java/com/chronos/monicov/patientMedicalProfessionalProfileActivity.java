package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class patientMedicalProfessionalProfileActivity extends AppCompatActivity {

    ImageButton homeButton, profileButton, statusButton, medicalProfessionalButton, settingsButton;
    Button viewPrescriptionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_medical_professional_profile);

        viewPrescriptionsButton = findViewById(R.id.btnViewPrescription);

        homeButton = findViewById(R.id.patientHomeButton);
        profileButton = findViewById(R.id.patientProfileButton);
        statusButton = findViewById(R.id.patientAddStatusButton);
        medicalProfessionalButton = findViewById(R.id.patientMedicalButton);
        settingsButton = findViewById(R.id.patientSettingsButton);

        viewPrescriptionsButton.setOnClickListener(view -> {
            startActivity(new Intent(patientMedicalProfessionalProfileActivity.this, patientsPrescriptionsAcitivty.class));
        });

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(patientMedicalProfessionalProfileActivity.this, patientHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(patientMedicalProfessionalProfileActivity.this, patientProfileActivity.class));
        });

        statusButton.setOnClickListener(view -> {
            startActivity(new Intent(patientMedicalProfessionalProfileActivity.this, patientStatusActivity.class));
        });

        medicalProfessionalButton.setOnClickListener(view -> {
            startActivity(new Intent(patientMedicalProfessionalProfileActivity.this, patientMedicalProfessionalProfileActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(patientMedicalProfessionalProfileActivity.this, patientSettingsActivity.class));
        });
    }
}