package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class patientSettingsActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    Button logoutButton;
    TextView firstNameTextField, lastNameTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_settings);

        String firstName = getIntent().getStringExtra("firstName").toString();
        String lastName = getIntent().getStringExtra("lastName").toString();

        mAuth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logoutPatient);
        firstNameTextField = findViewById(R.id.firstNameText);
        lastNameTextField = findViewById(R.id.lastNameText);
        firstNameTextField.setText(firstName);
        lastNameTextField.setText(lastName);

        logoutButton.setOnClickListener(view -> {
            logout();
        });
    }

    public void logout(){
        mAuth.signOut();
        startActivity(new Intent(patientSettingsActivity.this, LandingActivity.class));
    }
}