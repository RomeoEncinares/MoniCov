package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class patientSettingsActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    Button logoutButton;
    ImageButton homeButton, profileButton, statusButton, medicalProfessionalButton, settingsButton;
    TextView firstNameTextField, lastNameTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_settings);

        String firstName = getIntent().getStringExtra("firstName").toString();
        String lastName = getIntent().getStringExtra("lastName").toString();

        homeButton = findViewById(R.id.patientHomeButton);
        profileButton = findViewById(R.id.patientProfileButton);
        statusButton = findViewById(R.id.patientAddStatusButton);
        medicalProfessionalButton = findViewById(R.id.patientMedicalButton);
        settingsButton = findViewById(R.id.patientSettingsButton);

        mAuth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logoutPatient);
        firstNameTextField = findViewById(R.id.firstNameText);
        lastNameTextField = findViewById(R.id.lastNameText);
        firstNameTextField.setText(firstName);
        lastNameTextField.setText(lastName);

        logoutButton.setOnClickListener(view -> {
            logout();
        });

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(patientSettingsActivity.this, patientHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(patientSettingsActivity.this, patientProfileActivity.class));
        });

        statusButton.setOnClickListener(view -> {
            startActivity(new Intent(patientSettingsActivity.this, patientStatusActivity.class));
        });

        medicalProfessionalButton.setOnClickListener(view -> {
            startActivity(new Intent(patientSettingsActivity.this, patientMedicalProfessionalProfileActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(patientSettingsActivity.this, patientSettingsActivity.class));
        });

    }

    public void logout(){
        mAuth.signOut();
        startActivity(new Intent(patientSettingsActivity.this, LandingActivity.class));
    }
}