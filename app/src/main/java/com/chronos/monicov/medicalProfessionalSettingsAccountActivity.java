package com.chronos.monicov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class medicalProfessionalSettingsAccountActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    ImageButton backButton;
    Button saveButton;
    TextInputEditText oldPasswordTextField, newPasswordTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_professional_settings_account);

        mAuth = FirebaseAuth.getInstance();

        backButton = findViewById(R.id.backButton);
        saveButton = findViewById(R.id.btnSave);
        oldPasswordTextField = findViewById(R.id.etOldEditPassword);
        newPasswordTextField = findViewById(R.id.etEditNewPassword);

        String email = getCurrentUser();

        backButton.setOnClickListener(view -> {
            startActivity(new Intent(medicalProfessionalSettingsAccountActivity.this, medicalProfessionalSettingsActivity.class));
        });

        saveButton.setOnClickListener(view -> {
            String oldPassword = oldPasswordTextField.getText().toString();
            String newPassword = newPasswordTextField.getText().toString();

            if (TextUtils.isEmpty(oldPassword)) {
                oldPasswordTextField.setError("Email cannot be empty");
                oldPasswordTextField.requestFocus();
            }
            else if (TextUtils.isEmpty(newPassword)) {
                newPasswordTextField.setError("Email cannot be empty");
                newPasswordTextField.requestFocus();
            }
            else{
                AuthCredential credential = EmailAuthProvider
                        .getCredential(email, oldPassword);
                changePassword(credential, newPassword);

            }

        });

    }

    public String getCurrentUser(){
        String currentUser = mAuth.getCurrentUser().getEmail().toString();return currentUser;
    }

    public void changePassword(AuthCredential credential, String newPassword){
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(medicalProfessionalSettingsAccountActivity.this, "Password changed successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(medicalProfessionalSettingsAccountActivity.this, LandingActivity.class));
                            }
                            else {
                                Toast.makeText(medicalProfessionalSettingsAccountActivity.this, "Password change unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(medicalProfessionalSettingsAccountActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}