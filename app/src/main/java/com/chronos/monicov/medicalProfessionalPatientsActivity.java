package com.chronos.monicov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class medicalProfessionalPatientsActivity extends AppCompatActivity {

    ListView patientListView;
    ArrayList<String> patientList = new ArrayList<>();
    DatabaseReference mDatabase, patientListNode, currentPatientNode;
    FirebaseAuth mAuth;
    ImageButton homeButton, profileButton, medicalProfessionalButton, settingsButton, patientsButton, addPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_patients);

        mAuth = FirebaseAuth.getInstance();

        ArrayAdapter<String> patientListAdapter = new ArrayAdapter<>(medicalProfessionalPatientsActivity.this, android.R.layout.simple_list_item_1, patientList);
        patientListView = (ListView) findViewById(R.id.patientListButton);
        patientListView.setAdapter(patientListAdapter);

        homeButton = findViewById(R.id.medicalHomeButton);
        profileButton = findViewById(R.id.medicalProfileButton);
        patientsButton = findViewById(R.id.medicalPatientsButton);
        medicalProfessionalButton = findViewById(R.id.medicalNotificationButton);
        settingsButton = findViewById(R.id.medicalSettingsButton);

        addPatient = findViewById(R.id.addPatientButton);

        String currentUser = getCurrentPatient();
        String targetReference = currentUser.replace(".", "");
        mDatabase = FirebaseDatabase.getInstance().getReference("Medical Professional");
        patientListNode = mDatabase.child(targetReference);
        currentPatientNode = patientListNode.child("Patient List");
        currentPatientNode.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getKey();
                patientList.add(value);
                patientListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                patientListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String chosenPatient = adapterView.getItemAtPosition(i).toString();
                Intent passData = new Intent(getBaseContext(), medicalProfessionalPatientStatusDayListActivity.class);
                passData.putExtra("patient", chosenPatient);
                startActivity(passData);
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

        medicalProfessionalButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalPatientsActivity.this, medicalProfessionalNotificationsActivity.class));
        });

        settingsButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalPatientsActivity.this, medicalProfessionalSettingsActivity.class));
        });

        addPatient.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalPatientsActivity.this, medicalProfessionalAddPatientActivity.class));
        });

    }

    public String getCurrentPatient(){
        String currentUser = mAuth.getCurrentUser().getEmail().toString();return currentUser;
    }
}