package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class patientStatusActivity extends AppCompatActivity {

    Button addStatusButton, viewStatusButton;
    ImageButton homeButton, profileButton, statusButton, medicalProfessionalButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_status);

        homeButton = findViewById(R.id.patientHomeButton);
        profileButton = findViewById(R.id.patientProfileButton);
        statusButton = findViewById(R.id.patientAddStatusButton);
        medicalProfessionalButton = findViewById(R.id.patientMedicalButton);
        settingsButton = findViewById(R.id.patientSettingsButton);

        addStatusButton = findViewById(R.id.btnAddStatus);

        addStatusButton.setOnClickListener(view -> {
            startActivity(new Intent(patientStatusActivity.this, patientStatusQuestionnaireActivity.class));
        });

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(patientStatusActivity.this, patientHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(patientStatusActivity.this, patientProfileActivity.class));
        });

        statusButton.setOnClickListener(view -> {
            startActivity(new Intent(patientStatusActivity.this, patientStatusActivity.class));
        });

        medicalProfessionalButton.setOnClickListener(view -> {
            startActivity(new Intent(patientStatusActivity.this, patientMedicalProfessionalProfileActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(patientStatusActivity.this, patientSettingsActivity.class));
        });
    }
}