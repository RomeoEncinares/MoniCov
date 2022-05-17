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

public class medicalProfessionalSettingsActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    Button logoutButton;
    ImageButton homeButton, profileButton, settingsButton, patientsButton, accountButton;
    TextView firstNameMedicalTextView, lastNameMedicalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_settings);

        mAuth = FirebaseAuth.getInstance();

        firstNameMedicalTextView = findViewById(R.id.firstNameMedicalText);
        lastNameMedicalTextView = findViewById(R.id.lastNameMedicalText);

        homeButton = findViewById(R.id.medicalHomeButton);
        profileButton = findViewById(R.id.medicalProfileButton);
        patientsButton = findViewById(R.id.medicalPatientsButton);
        settingsButton = findViewById(R.id.medicalSettingsButton);
        logoutButton = findViewById(R.id.buttonLogOut);

        accountButton = findViewById(R.id.angleRightbutton);

        String currentUser = getCurrentUser();

        queryData(currentUser);

        logoutButton.setOnClickListener(view -> {
            logout();
        });

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalProfileActivity.class));
        });

        patientsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalPatientsActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalSettingsActivity.class));
        });

        accountButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsActivity.this, medicalProfessionalSettingsAccountActivity.class));
        });

    }

    public void logout(){
        mAuth.signOut();
        startActivity(new Intent(medicalProfessionalSettingsActivity.this, LandingActivity.class));
    }

    public String getCurrentUser(){
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

                firstNameMedicalTextView.setText(firstName);
                lastNameMedicalTextView.setText(lastName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}