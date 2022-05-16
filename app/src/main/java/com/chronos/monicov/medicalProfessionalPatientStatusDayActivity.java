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

public class medicalProfessionalPatientStatusDayActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseDatabase database;

    TextView dayText;
    TextView feverText, headacheText, coughText, breathShortnessText, soreThroatText, bodyChillsText,
            muscleAcheText, lostOfTasteText, lostOfSmellText, otherSymptomsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_patient_status_day);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        dayText = findViewById(R.id.textView22);
        feverText = findViewById(R.id.feverStatus);
        headacheText = findViewById(R.id.headacheStatus);
        coughText = findViewById(R.id.coughStatus);
        breathShortnessText = findViewById(R.id.shortnessStatus);
        soreThroatText = findViewById(R.id.soreThroatStatus);
        bodyChillsText = findViewById(R.id.bodyChillsStatus);
        muscleAcheText = findViewById(R.id.muscleAcheStatus);
        lostOfTasteText = findViewById(R.id.lostOfTasteStatus);
        lostOfSmellText = findViewById(R.id.lostofSmellStatus);
        otherSymptomsText = findViewById(R.id.othersStatus);

        String patientEmail = getIntent().getStringExtra("email").toString();
        String day = getIntent().getStringExtra("Day").toString();
        dayText.setText(day);

        String currentUser = patientEmail.replace(".", "");;
        queryData(currentUser, day);
    }

    public void queryData(String email, String day) {
        mDatabase = FirebaseDatabase.getInstance().getReference("Patient").child(email).child("Status").child(day);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, String> map = (HashMap<String, String>) snapshot.getValue();
                String fever = map.get("Fever");
                String headache = map.get("Headache");
                String cough = map.get("Cough");
                String breathShortness = map.get("Shortness of Breath");
                String soreThroat = map.get("Sore Throat");
                String bodyChills = map.get("Body Chills");
                String muscleAche = map.get("Muscle Ache");
                String lostOfTaste = map.get("Lost of Taste");
                String lostOfSmell = map.get("Lost of Smell");
                String others = map.get("Other Symptoms");

                feverText.setText(fever);
                headacheText.setText(headache);
                coughText.setText(cough);
                breathShortnessText.setText(breathShortness);
                soreThroatText.setText(soreThroat);
                bodyChillsText.setText(bodyChills);
                muscleAcheText.setText(muscleAche);
                lostOfTasteText.setText(lostOfTaste);
                lostOfSmellText.setText(lostOfSmell);
                otherSymptomsText.setText(others);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}