package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class patientInitialScreeningActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase, patientListNode, currentPatientNode, statusListNode;
    private FirebaseDatabase database;

    Button submitButton;
    RadioGroup questionOne, questionTwo, questionThree, questionFour, questionFive, questionSix;
    RadioButton radioOne, radioTwo, radioThree, radioFour, radioFive, radioSix;
    TextInputEditText questionSixText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_intial_screening);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        submitButton = findViewById(R.id.btnSubmit);

        questionOne = findViewById(R.id.radioGroup1);
        questionTwo = findViewById(R.id.radioGroup2);
        questionThree = findViewById(R.id.radioGroup3);
        questionFour = findViewById(R.id.radioGroup4);
        questionFive = findViewById(R.id.radioGroup5);
        questionSix = findViewById(R.id.radioGroup6);
        questionSixText = findViewById(R.id.comorbiditiesTextField);

        String email = getIntent().getStringExtra("email").toString();

        submitButton.setOnClickListener(view -> {
            checkButton(email);

        });
    }

    public void checkButton(String reference){
        int radioIdOne = questionOne.getCheckedRadioButtonId();
        int radioIdTwo = questionTwo.getCheckedRadioButtonId();
        int radioIdThree = questionThree.getCheckedRadioButtonId();
        int radioIdFour = questionFour.getCheckedRadioButtonId();
        int radioIdFive = questionFive.getCheckedRadioButtonId();
        int radioIdSix = questionSix.getCheckedRadioButtonId();

        radioOne = findViewById(radioIdOne);
        radioTwo = findViewById(radioIdTwo);
        radioThree = findViewById(radioIdThree);
        radioFour = findViewById(radioIdFour);
        radioFive = findViewById(radioIdFive);
        radioSix = findViewById(radioIdSix);

        String answerOne = radioOne.getText().toString();
        String answerTwo = radioTwo.getText().toString();
        String answerThree = radioThree.getText().toString();
        String answerFour = radioFour.getText().toString();
        String answerFive = radioFive.getText().toString();
        String answerSix = radioSix.getText().toString();
        String answerSixText = questionSixText.getText().toString();

        addInitialScreening(reference,  answerOne, answerTwo, answerThree, answerFour, answerFive, answerSix, answerSixText);
    }

    public void addInitialScreening(String reference, String answerOne, String answerTwo, String answerThree, String answerFour,
                                    String answerFive, String answerSix, String answerSixText){
        HashMap screeningMap = new HashMap();
        screeningMap.put("Covid-19 Test", answerOne);
        screeningMap.put("Quarantine", answerTwo);
        screeningMap.put("Close Contact", answerThree);
        screeningMap.put("Household", answerFour);
        screeningMap.put("Vaccine Status", answerFive);
        screeningMap.put("Comorbidities", answerSix);
        screeningMap.put("Specify Comorbidities", answerSixText);

        mDatabase = database.getReference("Patient");
        String targetReference = reference.replace(".", "");
        patientListNode = mDatabase.child(targetReference);
        currentPatientNode = patientListNode.child("Status");
        statusListNode = currentPatientNode.child("Initial Screening");
        statusListNode.updateChildren(screeningMap);
        startActivity(new Intent(patientInitialScreeningActivity.this, LoginActivity.class));
    }
}