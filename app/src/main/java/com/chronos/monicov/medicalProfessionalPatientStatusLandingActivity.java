package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class medicalProfessionalPatientStatusLandingActivity extends AppCompatActivity {

    Button viewHealthStatusButton, viewMedicationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_patient_status_landing);

        String chosenPatient = getIntent().getStringExtra("patient").toString();

        viewHealthStatusButton = findViewById(R.id.btnHealthStatus);
        viewMedicationButton = findViewById(R.id.btnViewMedication);

        viewHealthStatusButton.setOnClickListener(view -> {
            Intent passData = new Intent(getBaseContext(), medicalProfessionalPatientStatusDayListActivity.class);
            passData.putExtra("patient", chosenPatient);
            startActivity(passData);

        });

        viewMedicationButton.setOnClickListener(view -> {
            Intent passData = new Intent(getBaseContext(), medicalProfessionalPatientViewMedicationActivity.class);
            passData.putExtra("patient", chosenPatient);
            startActivity(passData);
        });
    }
}