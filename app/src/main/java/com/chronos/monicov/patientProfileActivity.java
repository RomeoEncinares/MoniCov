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

public class patientProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    ImageButton homeButton, profileButton;
    TextView firstNameTextField, lastNameTextField, lastNameInitialTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        mAuth = FirebaseAuth.getInstance();
        homeButton = findViewById(R.id.patientHomeButton);
        profileButton = findViewById(R.id.patientProfileButton);
        firstNameTextField = findViewById(R.id.firstNameText);
        lastNameTextField = findViewById(R.id.lastNameText);
        lastNameInitialTextField = findViewById(R.id.etLastNameInitial);

        queryData(getCurrentPatient());

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(patientProfileActivity.this, patientHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(patientProfileActivity.this, patientProfileActivity.class));
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
}