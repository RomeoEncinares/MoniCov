package com.chronos.monicov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class patientStatusInitialActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase, parentPatientNode;
    private FirebaseDatabase database;

    TextView dayText;
    TextView oneTextField, twoTextField, threeTextField, fourTextField,
            fiveTextField, sixTextField, sixComorbidityTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_status_initial);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        oneTextField = findViewById(R.id.answerOne);
        twoTextField = findViewById(R.id.answerTwo);
        threeTextField = findViewById(R.id.answerThree);
        fourTextField = findViewById(R.id.answerFour);
        fiveTextField = findViewById(R.id.answerFive);
        sixTextField = findViewById(R.id.answerSix);
        sixComorbidityTextField = findViewById(R.id.answerSixComorbidities);

        String currentUser = getCurrentPatient();
        queryData(currentUser);
    }

    public String getCurrentPatient(){
        String currentUser = mAuth.getCurrentUser().getEmail().toString();return currentUser;
    }

    public void queryData(String email){
        String emailKey = email.replace(".", "");
        mDatabase = FirebaseDatabase.getInstance().getReference("Patient").child(emailKey).child("Status").child("Initial Screening");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, String> map = (HashMap<String, String>) snapshot.getValue();
                String one = map.get("Covid-19 Test");
                String two = map.get("Quarantine");
                String three = map.get("Close Contact");
                String four = map.get("Household");
                String five = map.get("Vaccine Status");
                String six = map.get("Comorbidities");
                String sixComorbidities = map.get("Specify Comorbidities");

                oneTextField.setText(one);
                twoTextField.setText(two);
                threeTextField.setText(three);
                fourTextField.setText(four);
                fiveTextField.setText(five);
                sixTextField.setText(six);
                sixComorbidityTextField.setText(sixComorbidities);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}