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

public class patientProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase, parentPatientNode;
    private FirebaseDatabase database;

    Button updateButton;
    ImageButton homeButton, profileButton, statusButton, medicalProfessionalButton, settingsButton;
    TextView firstNameTextField, lastNameTextField, lastNameInitialTextField;
    TextInputEditText contactNumberField, ageField, genderField, birthDateField, addressField, vaccineNameField,
            vaccineDate1Field, vaccineDate2Field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        homeButton = findViewById(R.id.patientHomeButton);
        profileButton = findViewById(R.id.patientProfileButton);
        statusButton = findViewById(R.id.patientAddStatusButton);
        medicalProfessionalButton = findViewById(R.id.patientMedicalButton);
        settingsButton = findViewById(R.id.patientSettingsButton);

        firstNameTextField = findViewById(R.id.firstNameText);
        lastNameTextField = findViewById(R.id.lastNameText);
        lastNameInitialTextField = findViewById(R.id.etLastNameInitial);
        contactNumberField = findViewById(R.id.etcontactNumber);
        ageField = findViewById(R.id.etAge);
        genderField = findViewById(R.id.etGender);
        birthDateField = findViewById(R.id.etBirthDate);
        addressField = findViewById(R.id.etAddress);
        vaccineNameField = findViewById(R.id.etVaccineName);
        vaccineDate1Field = findViewById(R.id.etVaccine1);
        vaccineDate2Field = findViewById(R.id.etVaccine2);
        updateButton = findViewById(R.id.btnUpdate);

        String currentUser = getCurrentPatient();

        queryData(currentUser);
        checkProfile(currentUser);

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(patientProfileActivity.this, patientHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(patientProfileActivity.this, patientProfileActivity.class));
        });

        statusButton.setOnClickListener(view -> {
            startActivity(new Intent(patientProfileActivity.this, patientStatusActivity.class));
        });

        medicalProfessionalButton.setOnClickListener(view -> {
            startActivity(new Intent(patientProfileActivity.this, patientMedicalProfessionalProfileActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(patientProfileActivity.this, patientSettingsActivity.class));
        });

        updateButton.setOnClickListener(view -> {
            addPatientProfile(currentUser);
        });
    }

    public String getCurrentPatient(){
        String currentUser = mAuth.getCurrentUser().getEmail().toString();return currentUser;
    }

    public void queryData(String email) {
        String emailKey = email.replace(".", "");
        mDatabase = FirebaseDatabase.getInstance().getReference("Patient").child(emailKey);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, String> map = (HashMap<String, String>) snapshot.getValue();
                String firstName = map.get("firstname");
                String lastName = map.get("lastname");
                char lastNameChar = lastName.charAt(0);

                firstNameTextField.setText(firstName);
                lastNameTextField.setText(lastName);
                lastNameInitialTextField.setText(String.valueOf(lastNameChar));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addPatientProfile(String reference){
        String contact = contactNumberField.getText().toString();
        String age = ageField.getText().toString();
        String gender = genderField.getText().toString();
        String birthDate = birthDateField.getText().toString();
        String address = addressField.getText().toString();
        String vaccineName = vaccineNameField.getText().toString();
        String vaccineDate1 = vaccineDate1Field.getText().toString();
        String vaccineDate2 = vaccineDate2Field.getText().toString();


        HashMap map = new HashMap();
        map.put("contact", contact);
        map.put("age", age);
        map.put("gender", gender);
        map.put("birthdate", birthDate);
        map.put("address", address);
        map.put("vaccineName", vaccineName);
        map.put("vaccineDate1", vaccineDate1);
        map.put("vaccineDate2", vaccineDate2);
        mDatabase = database.getReference("Patient");
        String targetReference = reference.replace(".", "");
        parentPatientNode = mDatabase.child(targetReference);
        parentPatientNode.updateChildren(map);
    }

    public void checkProfile(String reference){
        String emailKey = reference.replace(".", "");
        mDatabase = FirebaseDatabase.getInstance().getReference("Patient").child(emailKey);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, String> map = (HashMap<String, String>) snapshot.getValue();
                String contact = map.get("contact");
                String age = map.get("age");
                String gender = map.get("gender");
                String birthDate = map.get("birthdate");
                String address = map.get("address");
                String vaccineName = map.get("vaccineName");
                String vaccineDate1 = map.get("vaccineDate1");
                String vaccineDate2 = map.get("vaccineDate2");

                setProfileHint(contact, age, gender, birthDate, address, vaccineName, vaccineDate1, vaccineDate2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setProfileHint(String contact, String age, String gender, String birthDate, String address,
                               String vaccineName, String vaccineDate1, String vaccineDate2){
        if(contact != null){
            contactNumberField.setText(contact);
        }
        if (age != null){
            ageField.setText(age);
        }
        if (gender != null){
            genderField.setText(gender);
        }
        if (birthDate != null){
            System.out.println(birthDate);
            birthDateField.setText(birthDate);
        }
        if (address != null){
            addressField.setText(address);
        }
        if (vaccineName != null){
            vaccineNameField.setText(vaccineName);
        }
        if (vaccineDate1 != null){
            vaccineDate1Field.setText(vaccineDate1);
        }
        if (vaccineDate2 != null){
            vaccineDate2Field.setText(vaccineDate2);
        }
    }

}