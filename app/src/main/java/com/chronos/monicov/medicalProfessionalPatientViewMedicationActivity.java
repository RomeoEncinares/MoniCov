package com.chronos.monicov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class medicalProfessionalPatientViewMedicationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    UserPrescriptionsAdapter myAdapter;
    ArrayList<User.Prescription> list;

    private DatabaseReference mDatabase;

    TextView firstNameTextField, lastNameTextField;
    Button addMedicationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_patient_view_medication);

        addMedicationButton = findViewById(R.id.btnAddMedication);

        firstNameTextField = findViewById(R.id.firstNameText);
        lastNameTextField = findViewById(R.id.lastNameText);

        String chosenPatient = getIntent().getStringExtra("patient").toString();
        chosenPatient = chosenPatient.replace(".", "");
        queryData(chosenPatient);

        recyclerView = findViewById(R.id.medicationList);
        database = FirebaseDatabase.getInstance().getReference("Patient").child(chosenPatient).child("Medication");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new UserPrescriptionsAdapter(this, list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User.Prescription userPrescription = dataSnapshot.getValue(User.Prescription.class);
                    list.add(userPrescription);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        String finalChosenPatient = chosenPatient;
        addMedicationButton.setOnClickListener(view -> {
            Intent passData = new Intent(getBaseContext(), medicalProfessionalPatientAddMedicationActivity.class);
            passData.putExtra("patient", finalChosenPatient);
            startActivity(passData);
        });

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
                lastNameTextField.setText(String.valueOf(lastNameChar));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}