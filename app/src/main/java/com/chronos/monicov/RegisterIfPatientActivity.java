package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterIfPatientActivity extends AppCompatActivity {

    TextInputEditText medicalProfessionalEmailField;
    private DatabaseReference mDatabase, medicalProfessionalNode, patientListNode;;
    private FirebaseDatabase database;

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_if_patient);

        medicalProfessionalEmailField = findViewById(R.id.etmedicalProfessionalEmail);
        database = FirebaseDatabase.getInstance();

        registerButton = findViewById(R.id.btnRegister);

        registerButton.setOnClickListener(view -> {
            createUser();
        });

    }

    public void createUser(){
        String medicalProfessionalEmail = medicalProfessionalEmailField.getText().toString();
        String email = getIntent().getStringExtra("email").toString();
        String firstName = getIntent().getStringExtra("firstName").toString();
        String lastName = getIntent().getStringExtra("lastName").toString();

        System.out.println(medicalProfessionalEmail);
        System.out.println(email);
        System.out.println(firstName);
        System.out.println(lastName);

        if (checkFields(medicalProfessionalEmail)){
            addPatient(medicalProfessionalEmail, email, firstName, lastName);
            startActivity(new Intent(RegisterIfPatientActivity.this, LoginActivity.class));
        }
    }

    public boolean checkFields(String medicalProfessionalEmail) {
        if (TextUtils.isEmpty(medicalProfessionalEmail)) {
            medicalProfessionalEmailField.setError("Email cannot be empty");
            medicalProfessionalEmailField.requestFocus();
        }
        else{
                return true;
            }
            return false;
        }

    public void addPatient(String reference, String patientEmail, String patientFirstname, String patientLastname){
        MedicalProfessional.assignedPatient newPatient = new MedicalProfessional.assignedPatient(patientEmail, patientFirstname, patientLastname);
        mDatabase = database.getReference("Medical Professional");
        String targetReference = reference.replace(".", "");
        medicalProfessionalNode = mDatabase.child(targetReference);
        patientListNode = medicalProfessionalNode.child("Patient List");
        String keyId = patientEmail.replace(".", "");
        patientListNode.child(keyId).setValue(newPatient);
    }
}