package com.chronos.monicov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class medicalProfessionalPatientStatusDayListActivity extends AppCompatActivity {

    ListView daysListView;
    ArrayList<String> daysList = new ArrayList<>();
    DatabaseReference mDatabase, patientListNode, currentPatientNode;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_patient_status_day_list);
        mAuth = FirebaseAuth.getInstance();

        ArrayAdapter<String> dayListAdapter = new ArrayAdapter<>(medicalProfessionalPatientStatusDayListActivity.this, android.R.layout.simple_list_item_1, daysList);
        daysListView = (ListView) findViewById(R.id.dayButton);
        daysListView.setAdapter(dayListAdapter);

        String chosenPatient = getIntent().getStringExtra("patient").toString();
        String targetReference = chosenPatient.replace(".", "");
        mDatabase = FirebaseDatabase.getInstance().getReference("Patient");
        patientListNode = mDatabase.child(targetReference);
        currentPatientNode = patientListNode.child("Status");
        currentPatientNode.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getKey();
                daysList.add(value);
                dayListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                dayListAdapter.notifyDataSetChanged();
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

        daysListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String chosenDay = adapterView.getItemAtPosition(i).toString();

                if(chosenDay.equals("Initial Screening")){
                    startActivity(new Intent(medicalProfessionalPatientStatusDayListActivity.this, patientStatusInitialActivity.class));
                }
                else{
                    Intent passData = new Intent(getBaseContext(), patientStatusDayActivity.class);
                    passData.putExtra("Day", chosenDay);
                    startActivity(passData);
                }
            }
        });
    }

}