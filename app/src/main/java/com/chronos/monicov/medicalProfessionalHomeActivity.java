package com.chronos.monicov;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class medicalProfessionalHomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    ImageButton homeButton, profileButton, medicalProfessionalButton, settingsButton, patientsButton;
    TextView firstNameTextField, lastNameTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional);

        mAuth = FirebaseAuth.getInstance();

        homeButton = findViewById(R.id.medicalHomeButton);
        profileButton = findViewById(R.id.medicalProfileButton);
        patientsButton = findViewById(R.id.medicalPatientsButton);
        medicalProfessionalButton = findViewById(R.id.medicalNotificationButton);
        settingsButton = findViewById(R.id.medicalSettingsButton);

        firstNameTextField = findViewById(R.id.firstNameText);
        lastNameTextField = findViewById(R.id.lastNameText);

        queryData(getCurrentMedical());

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalHomeActivity.this, medicalProfessionalHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalHomeActivity.this, medicalProfessionalProfileActivity.class));
        });

        patientsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalHomeActivity.this, medicalProfessionalPatientsActivity.class));
        });

        medicalProfessionalButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalHomeActivity.this, medicalProfessionalNotificationsActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalHomeActivity.this, medicalProfessionalSettingsActivity.class));
        });
    }

    public String getCurrentMedical(){
        String currentUser = mAuth.getCurrentUser().getEmail().toString();return currentUser;
    }

    public void queryData(String email){
        String emailKey = email.replace(".", "");
        mDatabase = FirebaseDatabase.getInstance().getReference("Medical Professional").child(emailKey);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, String> map = (HashMap<String, String>) snapshot.getValue();
                String firstName = map.get("firstname");
                String lastName = map.get("lastname");
                char lastNameChar = lastName.charAt(0);

                firstNameTextField.setText("Dr. " + firstName);
                lastNameTextField.setText(String.valueOf(lastNameChar));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}