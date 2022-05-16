package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class medicalProfessionalPatientAddMedicationActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase, patientListNode, currentPatientNode, statusListNode;
    private FirebaseDatabase database;

    TextInputEditText medicineNameField, medicineDurationField, medicineTimeField;
    Button addMedicationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_patient_add_medication);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        medicineNameField = findViewById(R.id.etMedicineName);
        medicineDurationField = findViewById(R.id.etMedicineDuration);
        medicineTimeField = findViewById(R.id.etMedicineTime);
        addMedicationButton = findViewById(R.id.btnAddMedication);

        String targetReference = getIntent().getStringExtra("patient").toString();

        addMedicationButton.setOnClickListener(view -> {
            checkFields(targetReference);
        });
    }

    public void checkFields(String reference){
        String medicineName = medicineNameField.getText().toString();
        String medicineDuration = medicineDurationField.getText().toString();
        String medicineTime = medicineTimeField.getText().toString();

        addMedication(reference, medicineName, medicineDuration, medicineTime);
    }

    public void addMedication(String reference, String medicineName, String medicineDuration, String medicineTime){
        HashMap medicationMap = new HashMap();
        medicationMap.put("medicineName", medicineName);
        medicationMap.put("medicineDuration", medicineDuration);
        medicationMap.put("medicineTime", medicineTime);

        mDatabase = database.getReference("Patient");
        String targetReference = reference.replace(".", "");
        patientListNode = mDatabase.child(targetReference);
        currentPatientNode = patientListNode.child("Medication");
        statusListNode = currentPatientNode.child(medicineName);
        statusListNode.updateChildren(medicationMap);
        startActivity(new Intent(medicalProfessionalPatientAddMedicationActivity.this, medicalProfessionalPatientsActivity.class));
    }
}