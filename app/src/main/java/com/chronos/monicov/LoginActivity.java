package com.chronos.monicov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText emailField;
    TextInputEditText passwordField;
    TextView registerText, forgotPasswordText;
    Button loginButton;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.etLoginEmail);
        passwordField = findViewById(R.id.etLoginPass);
        loginButton = findViewById(R.id.btnLogin);
        registerText = findViewById(R.id.tvRegisterHere);
        forgotPasswordText = findViewById(R.id.forgotPasswordTextView);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(view -> {
            loginUser();
        });

        registerText.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        forgotPasswordText.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        });
    }

    public void loginUser(){
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (checkFields(email, password)){
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"User sign in successful", Toast.LENGTH_SHORT).show();
                        checkUserType(email);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Sign in Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public boolean checkFields(String email, String password){
        if (TextUtils.isEmpty(email)){
            emailField.setError("Email cannot be empty");
            emailField.requestFocus();
        }
        else if (TextUtils.isEmpty(password)) {
            passwordField.setError("Email cannot be empty");
            passwordField.requestFocus();
        }
        else {
            return true;
        }
        return false;
    }

    public void checkUserType(String email){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Patient").orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getChildren() != null && snapshot.getChildren().iterator().hasNext()){
                    checkDischargeStatus(email);
                }
                else {
                    startActivity(new Intent(LoginActivity.this, medicalProfessionalHomeActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void checkDischargeStatus(String email){
        email = email.replace(".", "");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String finalEmail = email;
        mDatabase.child("Patient").child(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String, String> map = (HashMap<String, String>) snapshot.getValue();
                if(map.get("Discharge") != null){
                    startActivity(new Intent(LoginActivity.this, dischargeStatusListActivity.class));
                }
                else{
                    startActivity(new Intent(LoginActivity.this, patientHomeActivity.class));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}