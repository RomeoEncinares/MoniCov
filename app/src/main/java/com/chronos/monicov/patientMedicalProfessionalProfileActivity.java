package com.chronos.monicov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class patientMedicalProfessionalProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    ImageButton homeButton, profileButton, statusButton, medicalProfessionalButton, settingsButton;
    Button viewPrescriptionsButton;
    TextView firstNameMedicalTextField, lastNameMedicalTextField, medicalProfessionalAboutField,
            medicalProfessionalGenderField, medicalProfessionalAgeField, medicalProfessionalHospitalNameField,
            medicalProfessionalHospitalAddressField, medicalProfessionalHospitalContactNumberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_medical_professional_profile);

        mAuth = FirebaseAuth.getInstance();

        firstNameMedicalTextField = findViewById(R.id.firstNameMedicalTextView);
        lastNameMedicalTextField = findViewById(R.id.lastNameMedicalTextView);
        medicalProfessionalAboutField = findViewById(R.id.medicalProfessionalAboutView);
        medicalProfessionalGenderField = findViewById(R.id.medicalProfessionalGender);
        medicalProfessionalAgeField = findViewById(R.id.medicalProfessionalAge);
        medicalProfessionalHospitalNameField = findViewById(R.id.medicalProfessionalHospitalName);
        medicalProfessionalHospitalAddressField = findViewById(R.id.medicalProfessionalHospitalAddress);
        medicalProfessionalHospitalContactNumberField = findViewById(R.id.medicalProfessionalHospitalContactNumber);

        viewPrescriptionsButton = findViewById(R.id.btnViewPrescription);

        homeButton = findViewById(R.id.patientHomeButton);
        profileButton = findViewById(R.id.patientProfileButton);
        statusButton = findViewById(R.id.patientAddStatusButton);
        medicalProfessionalButton = findViewById(R.id.patientMedicalButton);
        settingsButton = findViewById(R.id.patientSettingsButton);

        String currentUser = getCurrentPatient();
        queryPatientData(currentUser);

        viewPrescriptionsButton.setOnClickListener(view -> {
            startActivity(new Intent(patientMedicalProfessionalProfileActivity.this, patientsPrescriptionsAcitivty.class));
        });

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(patientMedicalProfessionalProfileActivity.this, patientHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(patientMedicalProfessionalProfileActivity.this, patientProfileActivity.class));
        });

        statusButton.setOnClickListener(view -> {
            startActivity(new Intent(patientMedicalProfessionalProfileActivity.this, patientStatusActivity.class));
        });

        medicalProfessionalButton.setOnClickListener(view -> {
            startActivity(new Intent(patientMedicalProfessionalProfileActivity.this, patientMedicalProfessionalProfileActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(patientMedicalProfessionalProfileActivity.this, patientSettingsActivity.class));
        });
    }

    public String getCurrentPatient(){
        String currentUser = mAuth.getCurrentUser().getEmail().toString();return currentUser;
    }

    public void queryPatientData(String email) {
        String emailKey = email.replace(".", "");
        mDatabase = FirebaseDatabase.getInstance().getReference("Patient").child(emailKey);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, String> map = (HashMap<String, String>) snapshot.getValue();
                String firstName = map.get("firstname");
                String lastName = map.get("lastname");
                String medicalEmail = map.get("medicalProfessionalEmail");
                firstNameMedicalTextField.setText(firstName);
                lastNameMedicalTextField.setText(lastName);
                queryMedicalProfessionalData(medicalEmail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void queryMedicalProfessionalData(String email) {
        String emailKey = email.replace(".", "");
        mDatabase = FirebaseDatabase.getInstance().getReference("Medical Professional").child(emailKey);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, String> map = (HashMap<String, String>) snapshot.getValue();
                String about = map.get("bio");
                String gender = map.get("gender");
                String age = map.get("age");
                String hospitalName = map.get("hospitalName");
                String hospitalAddress = map.get("hospitalAddress");
                String hospitalContact = map.get("hospitalContactNumber");

                medicalProfessionalAboutField.setText(about);
                medicalProfessionalGenderField.setText(gender);
                medicalProfessionalAgeField.setText(age);
                medicalProfessionalHospitalNameField.setText(hospitalName);
                medicalProfessionalHospitalAddressField.setText(hospitalAddress);
                medicalProfessionalHospitalContactNumberField.setText(hospitalContact);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}