package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class patientHomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    Button logoutButton;
    ImageButton homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        mAuth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logoutPatient);
        homeButton = findViewById(R.id.patientHomeButton);

        logoutButton.setOnClickListener(view -> {
            logout();
        });

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(patientHomeActivity.this, patientHomeActivity.class));
        });

    }

    public void logout(){
        mAuth.signOut();
        startActivity(new Intent(patientHomeActivity.this, LandingActivity.class));
    }
}