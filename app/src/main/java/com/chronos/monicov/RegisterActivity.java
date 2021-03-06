package com.chronos.monicov;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextInputEditText emailField;
    TextInputEditText passwordField;
    TextInputEditText firstNameField;
    TextInputEditText lastNameField;
    Button registerButton;
    private DatabaseReference mDatabase, medicalProfessionalNode, patientListNode;
    private FirebaseDatabase database;
    private Spinner spinner;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailField = findViewById(R.id.etRegEmail);
        passwordField = findViewById(R.id.etRegPass);
        firstNameField = findViewById(R.id.etFirstName);
        lastNameField = findViewById(R.id.etLastName);
        registerButton = findViewById(R.id.btnRegister);
        spinner = findViewById(R.id.user_type);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        registerButton.setOnClickListener(view -> {
            createUser();
        });

    }

    // Create User in Firebase Authentication
    private void addFirebaseUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "User registered successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //Create User
    private void createUser() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String userType = spinner.getSelectedItem().toString();
        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();

        if (checkFields(email, password, firstName, lastName)) {
            addFirebaseUser(email, password);
            if (userType.equals("Patient")) {
                Patient patient = new Patient(email, password, userType, firstName, lastName);
                mDatabase = database.getReference("Patient");
                String keyId = email.replace(".", "");
                mDatabase.child(keyId).setValue(patient);
                // Pass Data to RegisterIfPatientActivity
                Intent passData = new Intent(getBaseContext(), RegisterIfPatientActivity.class);
                passData.putExtra("email", email);
                passData.putExtra("firstName", firstName);
                passData.putExtra("lastName", lastName);
                startActivity(passData);
            } else {
                MedicalProfessional medicalProfessional = new MedicalProfessional(email, password, userType, firstName, lastName);
                String keyId = email.replace(".", "");
                mDatabase = database.getReference("Medical Professional");
                mDatabase.child(keyId).setValue(medicalProfessional);
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public boolean checkFields(String email, String password, String firstName, String lastName) {
        if (TextUtils.isEmpty(email)) {
            emailField.setError("Email cannot be empty");
            emailField.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            passwordField.setError("Email cannot be empty");
            passwordField.requestFocus();
        } else if (TextUtils.isEmpty(firstName)) {
            firstNameField.setError("Email cannot be empty");
            firstNameField.requestFocus();
        } else if (TextUtils.isEmpty(lastName)) {
            lastNameField.setError("Email cannot be empty");
            lastNameField.requestFocus();
        }
        else {
            return true;
        }
        return false;
    }


}