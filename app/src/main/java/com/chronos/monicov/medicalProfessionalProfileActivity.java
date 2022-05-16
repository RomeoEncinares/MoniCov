package com.chronos.monicov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class medicalProfessionalProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase, parentMedicalNode;
    private FirebaseDatabase database;

    Button updateButton;
    ImageButton homeButton, profileButton, medicalProfessionalButton, settingsButton, patientsButton;
    TextView firstNameTextField, lastNameTextField, nameInitialTextField;
    TextInputEditText bioField, ageField, contactNumberField, genderField, birthDateField, addressField,
        patientsNumberField, experienceField, hospitalNameField, hospitalAddressField,
        hospitalContactNumberField, vaccineCardNumberField, vaccineBrandField,
        vaccineDate1Field, vaccineDate2Field, healthFacilityField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_profile);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        firstNameTextField = findViewById(R.id.firstNameMedicalText);
        lastNameTextField = findViewById(R.id.lastNameMedicalText);
        nameInitialTextField = findViewById(R.id.nameInitialMedicalText);

        homeButton = findViewById(R.id.medicalHomeButton);
        profileButton = findViewById(R.id.medicalProfileButton);
        patientsButton = findViewById(R.id.medicalPatientsButton);
        medicalProfessionalButton = findViewById(R.id.medicalNotificationButton);
        settingsButton = findViewById(R.id.medicalSettingsButton);

        bioField = findViewById(R.id.describeYourself);
        ageField = findViewById(R.id.etAge);
        genderField = findViewById(R.id.etGender);
        contactNumberField = findViewById(R.id.etcontactNumber);
        birthDateField = findViewById(R.id.etBirthDate);
        addressField = findViewById(R.id.etAddress);
        patientsNumberField = findViewById(R.id.etNumberOfPatients);
        experienceField = findViewById(R.id.etExperience);
        hospitalNameField = findViewById(R.id.etHospitalName);
        hospitalAddressField = findViewById(R.id.etHospitalAddress);
        hospitalContactNumberField = findViewById(R.id.etHospitalContact);
        vaccineCardNumberField = findViewById(R.id.etVaccCardNumber);
        vaccineBrandField = findViewById(R.id.etVaccName);
        vaccineDate1Field = findViewById(R.id.etFirstDose);
        vaccineDate2Field = findViewById(R.id.etSecondDose);
        healthFacilityField = findViewById(R.id.etHealthFacilityName);

        updateButton = findViewById(R.id.btnUpdate);

        String currentUser = getCurrentMedical();
        queryData(currentUser);

        updateButton.setOnClickListener(view -> {
            addMedicalProfessionalProfile(currentUser);
        });

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalProfileActivity.this, medicalProfessionalHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalProfileActivity.this, medicalProfessionalProfileActivity.class));
        });

        patientsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalProfileActivity.this, medicalProfessionalPatientsActivity.class));
        });

        medicalProfessionalButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalProfileActivity.this, medicalProfessionalNotificationsActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalProfileActivity.this, medicalProfessionalSettingsActivity.class));
        });

    }

    public String getCurrentMedical(){
        String currentUser = mAuth.getCurrentUser().getEmail().toString();return currentUser;
    }

    public void queryData(String email) {
        String emailKey = email.replace(".", "");
        mDatabase = FirebaseDatabase.getInstance().getReference("Medical Professional").child(emailKey);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, String> map = (HashMap<String, String>) snapshot.getValue();
                String firstName = map.get("firstname");
                String lastName = map.get("lastname");
                char firstNameChar = firstName.charAt(0);
                char lastNameChar = lastName.charAt(0);

                firstNameTextField.setText(firstName);
                lastNameTextField.setText(lastName);
                nameInitialTextField.setText(String.valueOf(firstNameChar) + String.valueOf(lastNameChar));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addMedicalProfessionalProfile(String reference){
        String bio = bioField.getText().toString();
        String age = ageField.getText().toString();
        String gender = genderField.getText().toString();
        String contact = contactNumberField.getText().toString();
        String birthDate = birthDateField.getText().toString();
        String address = addressField.getText().toString();
        String patientNumber = patientsNumberField.getText().toString();
        String experience = experienceField.getText().toString();
        String hospitalName = hospitalNameField.getText().toString();
        String hospitalAddress = hospitalAddressField.getText().toString();
        String hospitalContactNumber = hospitalContactNumberField.getText().toString();
        String vaccineCardNumber = vaccineCardNumberField.getText().toString();
        String vaccineBrand = vaccineBrandField.getText().toString();
        String vaccineDate1 = vaccineDate1Field.getText().toString();
        String vaccineDate2 = vaccineDate2Field.getText().toString();
        String healthFacility = healthFacilityField.getText().toString();

        HashMap patientProfile = new HashMap();
        patientProfile.put("bio", bio);
        patientProfile.put("age", age);
        patientProfile.put("gender", gender);
        patientProfile.put("contact", contact);
        patientProfile.put("birthdate", birthDate);
        patientProfile.put("address", address);
        patientProfile.put("patientNumber", patientNumber);
        patientProfile.put("experience", experience);
        patientProfile.put("hospitalName", hospitalName);
        patientProfile.put("hospitalAddress",hospitalAddress);
        patientProfile.put("hospitalContactNumber", hospitalContactNumber);
        patientProfile.put("vaccineCardNumber", vaccineCardNumber);
        patientProfile.put("vaccineName", vaccineBrand);
        patientProfile.put("vaccineDate1", vaccineDate1);
        patientProfile.put("vaccineDate2", vaccineDate2);
        patientProfile.put("healthFacility", healthFacility);

        mDatabase = database.getReference("Medical Professional");
        String targetReference = reference.replace(".", "");
        parentMedicalNode = mDatabase.child(targetReference);
        parentMedicalNode.updateChildren(patientProfile);
    }
}