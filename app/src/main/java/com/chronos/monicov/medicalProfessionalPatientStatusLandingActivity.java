package com.chronos.monicov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class medicalProfessionalPatientStatusLandingActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    Button viewHealthStatusButton, viewMedicationButton;
    TextView firstNameTextField, lastNameTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_patient_status_landing);

        String chosenPatient = getIntent().getStringExtra("patient").toString();
        queryData(chosenPatient);

        mAuth = FirebaseAuth.getInstance();

        firstNameTextField = findViewById(R.id.firstNameText);
        lastNameTextField = findViewById(R.id.lastNameText);

        viewHealthStatusButton = findViewById(R.id.btnHealthStatus);
        viewMedicationButton = findViewById(R.id.btnViewMedication);

        viewHealthStatusButton.setOnClickListener(view -> {
            Intent passData = new Intent(getBaseContext(), medicalProfessionalPatientStatusDayListActivity.class);
            passData.putExtra("patient", chosenPatient);
            startActivity(passData);

        });

        viewMedicationButton.setOnClickListener(view -> {
            Intent passData = new Intent(getBaseContext(), medicalProfessionalPatientViewMedicationActivity.class);
            passData.putExtra("patient", chosenPatient);
            startActivity(passData);
        });
    }

    public void queryData(String email){
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