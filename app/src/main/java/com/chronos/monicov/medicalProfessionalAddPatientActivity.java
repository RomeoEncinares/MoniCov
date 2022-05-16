package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class medicalProfessionalAddPatientActivity extends AppCompatActivity {

    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_add_patient);

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalAddPatientActivity.this, medicalProfessionalPatientsActivity.class));
        });

    }
}