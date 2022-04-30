package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class patientHomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        mAuth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logoutPatient);

        logoutButton.setOnClickListener(view -> {
            logout();
        });
    }

    public void logout(){
        mAuth.signOut();
        startActivity(new Intent(patientHomeActivity.this, LandingActivity.class));
    }
}