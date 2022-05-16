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

public class medicalProfessionalProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase, parentPatientNode;
    private FirebaseDatabase database;

    Button updateButton;
    ImageButton homeButton, profileButton, medicalProfessionalButton, settingsButton, patientsButton;
    TextView firstNameTextField, lastNameTextField, nameInitialTextField;

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

        String currentUser = getCurrentMedical();
        queryData(currentUser);

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
}