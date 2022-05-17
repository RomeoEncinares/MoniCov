package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class medicalProfessionalSettingsActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    Button logoutButton;
    ImageButton homeButton, profileButton, settingsButton, patientsButton, accountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_settings);

        mAuth = FirebaseAuth.getInstance();

        homeButton = findViewById(R.id.medicalHomeButton);
        profileButton = findViewById(R.id.medicalProfileButton);
        patientsButton = findViewById(R.id.medicalPatientsButton);
        settingsButton = findViewById(R.id.medicalSettingsButton);
        logoutButton = findViewById(R.id.buttonLogOut);

        accountButton = findViewById(R.id.angleRightbutton);

        logoutButton.setOnClickListener(view -> {
            logout();
        });

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalProfileActivity.class));
        });

        patientsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalPatientsActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalSettingsActivity.class));
        });

        accountButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalSettingsAccountActivity.class));
        });

    }

    public void logout(){
        mAuth.signOut();
        startActivity(new Intent(medicalProfessionalSettingsActivity.this, LandingActivity.class));
    }
}