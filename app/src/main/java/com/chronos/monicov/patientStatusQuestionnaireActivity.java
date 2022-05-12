package com.chronos.monicov;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class patientStatusQuestionnaireActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase, patientListNode, currentPatientNode, statusListNode;
    private FirebaseDatabase database;

    private Spinner spinner;
    Button submitButton;
    RadioGroup questionOne, questionTwo, questionThree, questionFour, questionFive, questionSix, questionSeven, questionEight, questionNine;
    RadioButton radioOne, radioTwo, radioThree, radioFour, radioFive, radioSix, radioSeven, radioEight, radioNine;
    TextInputEditText questionTen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_status_questionnaire);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        spinner = findViewById(R.id.current_day);
        submitButton = findViewById(R.id.btnSubmit);

        questionOne = findViewById(R.id.radioGroup1);
        questionTwo = findViewById(R.id.radioGroup2);
        questionThree = findViewById(R.id.radioGroup3);
        questionFour = findViewById(R.id.radioGroup4);
        questionFive = findViewById(R.id.radioGroup5);
        questionSix = findViewById(R.id.radioGroup6);
        questionSeven = findViewById(R.id.radioGroup7);
        questionEight = findViewById(R.id.radioGroup8);
        questionNine = findViewById(R.id.radioGroup9);
        questionTen = findViewById(R.id.etOtherSymptoms);

        String currentUser = getCurrentPatient();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.current_day, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        submitButton.setOnClickListener(view -> {
            checkButton(currentUser);

        });

    }

    public void checkButton(String reference){
        int radioIdOne = questionOne.getCheckedRadioButtonId();
        int radioIdTwo = questionTwo.getCheckedRadioButtonId();
        int radioIdThree = questionThree.getCheckedRadioButtonId();
        int radioIdFour = questionFour.getCheckedRadioButtonId();
        int radioIdFive = questionFive.getCheckedRadioButtonId();
        int radioIdSix = questionSix.getCheckedRadioButtonId();
        int radioIdSeven = questionSeven.getCheckedRadioButtonId();
        int radioIdEight = questionEight.getCheckedRadioButtonId();
        int radioIdNine = questionNine.getCheckedRadioButtonId();

        radioOne = findViewById(radioIdOne);
        radioTwo = findViewById(radioIdTwo);
        radioThree = findViewById(radioIdThree);
        radioFour = findViewById(radioIdFour);
        radioFive = findViewById(radioIdFive);
        radioSix = findViewById(radioIdSix);
        radioSeven = findViewById(radioIdSeven);
        radioEight = findViewById(radioIdEight);
        radioNine = findViewById(radioIdNine);

        String currentDay = spinner.getSelectedItem().toString();
        String answerOne = radioOne.getText().toString();
        String answerTwo = radioTwo.getText().toString();
        String answerThree = radioThree.getText().toString();
        String answerFour = radioFour.getText().toString();
        String answerFive = radioFive.getText().toString();
        String answerSix = radioSix.getText().toString();
        String answerSeven = radioSeven.getText().toString();
        String answerEight = radioEight.getText().toString();
        String answerNine = radioNine.getText().toString();
        String answerTen = questionTen.getText().toString();

        addStatus(reference, currentDay, answerOne, answerTwo, answerThree, answerFour, answerFive, answerSix, answerSeven, answerEight, answerNine, answerTen);
    }

    public void addStatus(String reference, String currentDay, String answerOne, String answerTwo, String answerThree, String answerFour,
                          String answerFive, String answerSix, String answerSeven, String answerEight, String answerNine,
                          String answerTen){
        HashMap StatusMap = new HashMap();
        StatusMap.put("Fever", answerOne);
        StatusMap.put("Headache", answerTwo);
        StatusMap.put("Cough", answerThree);
        StatusMap.put("Shortness of Breath", answerFour);
        StatusMap.put("Sore Throat", answerFive);
        StatusMap.put("Body Chills", answerSix);
        StatusMap.put("Muscle Ache", answerSeven);
        StatusMap.put("Lost of Smell", answerEight);
        StatusMap.put("Lost of Taste", answerNine);
        StatusMap.put("Other Symptoms", answerTen);

        mDatabase = database.getReference("Patient");
        String targetReference = reference.replace(".", "");
        patientListNode = mDatabase.child(targetReference);
        currentPatientNode = patientListNode.child("Status");
        statusListNode = currentPatientNode.child(currentDay);
        statusListNode.updateChildren(StatusMap);
    }

    public String getCurrentPatient(){
        String currentUser = mAuth.getCurrentUser().getEmail().toString();return currentUser;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}