package com.chronos.monicov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class medicalProfessionalHomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference medicalProfessionalNode, patientListNode;
    private FirebaseDatabase database;

    Button logoutButton;
    Button generateCodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional);

        mAuth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logoutMedicalProfessional);
        generateCodeButton = findViewById(R.id.generateCode);
        database = FirebaseDatabase.getInstance();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        generateCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = generateCode();
                String currentUser = getCurrent();
            }
        });
    }

    public void logout(){
        mAuth.signOut();
        startActivity(new Intent(medicalProfessionalHomeActivity.this, LandingActivity.class));
    }

    public String generateCode(){
        int max = 60000;
        int min = 10000;
        int code;
        code = (int) (Math.random() * (max - min + 1) + min);
        return String.valueOf(code);
    }

    public String getCurrent(){
        String getCurrentMedicalProfessional = mAuth.getInstance().getCurrentUser().getEmail().toString();
        return getCurrentMedicalProfessional.replace(".", "");
    }

}