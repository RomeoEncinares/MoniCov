package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class medicalProfessionalPatientViewMedicationActivity extends AppCompatActivity {

    Button addMedicationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_patient_view_medication);

        addMedicationButton = findViewById(R.id.btnAddMedication);

        String chosenPatient = getIntent().getStringExtra("patient").toString();

        addMedicationButton.setOnClickListener(view -> {
            Intent passData = new Intent(getBaseContext(), medicalProfessionalPatientAddMedicationActivity.class);
            passData.putExtra("patient", chosenPatient);
            startActivity(passData);
        });

    }
}