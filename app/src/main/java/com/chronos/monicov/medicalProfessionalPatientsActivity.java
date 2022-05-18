package com.chronos.monicov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class medicalProfessionalPatientsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MedicalProfessionalPatientListAdapter myAdapter;
    ArrayList<MedicalProfessional.assignedPatient> list;

    FirebaseAuth mAuth;
    ImageButton homeButton, profileButton, settingsButton, patientsButton, addPatient;
    TextView patientCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_patients);

        mAuth = FirebaseAuth.getInstance();

        homeButton = findViewById(R.id.medicalHomeButton);
        profileButton = findViewById(R.id.medicalProfileButton);
        patientsButton = findViewById(R.id.medicalPatientsButton);
        settingsButton = findViewById(R.id.medicalSettingsButton);

        patientCount = findViewById(R.id.viewTotalPatient);

        String currentUser = getCurrentPatient();
        String targetReference = currentUser.replace(".", "");

        recyclerView = findViewById(R.id.patientList);
        database = FirebaseDatabase.getInstance().getReference("Medical Professional").child(targetReference).child("Patient List");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MedicalProfessionalPatientListAdapter(this, list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MedicalProfessional.assignedPatient assignedPatient = dataSnapshot.getValue(MedicalProfessional.assignedPatient.class);
                    int size = (int) snapshot.getChildrenCount();
                    patientCount.setText(String.valueOf(size));
                    list.add(assignedPatient);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        homeButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalPatientsActivity.this, medicalProfessionalHomeActivity.class));
        });

        profileButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalPatientsActivity.this, medicalProfessionalProfileActivity.class));
        });

        patientsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalPatientsActivity.this, medicalProfessionalPatientsActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalPatientsActivity.this, medicalProfessionalSettingsActivity.class));
        });

    }

    public String getCurrentPatient(){
        String currentUser = mAuth.getCurrentUser().getEmail().toString();return currentUser;
    }
}