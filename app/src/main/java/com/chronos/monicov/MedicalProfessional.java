package com.chronos.monicov;

public class MedicalProfessional extends User{

    public MedicalProfessional() {
    }

    public MedicalProfessional(String email, String password, String userType, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUserType() {
        return userType;
    }
}
