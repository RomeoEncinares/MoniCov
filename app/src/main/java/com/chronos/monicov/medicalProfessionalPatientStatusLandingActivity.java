package com.chronos.monicov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
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
    private DatabaseReference mDatabase, pDatabase;

    Button viewHealthStatusButton, viewMedicationButton, dischargePatientButton;
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
        dischargePatientButton = findViewById(R.id.btnDischargePatient);

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

        dischargePatientButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Are you sure you want to discharge this patient?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dischargePatient(chosenPatient);
                            startActivity(new Intent(medicalProfessionalPatientStatusLandingActivity.this, medicalProfessionalPatientsActivity.class));
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
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

    public void dischargePatient(String chosenPatient){
        String emailKey = getCurrentMedical();
        emailKey = emailKey.replace(".", "");
        chosenPatient = chosenPatient.replace(".", "");
        mDatabase = FirebaseDatabase.getInstance().getReference("Medical Professional").child(emailKey).child("Patient List").child(chosenPatient);
        mDatabase.removeValue();
        pDatabase = FirebaseDatabase.getInstance().getReference("Patient").child(chosenPatient);
        HashMap map = new HashMap();
        map.put("Discharge", "true");
        pDatabase.updateChildren(map);
    }

    public String getCurrentMedical(){
        String currentUser = mAuth.getCurrentUser().getEmail().toString();return currentUser;
    }
}