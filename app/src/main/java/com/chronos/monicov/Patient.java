package com.chronos.monicov;

public class Patient extends User {
    String code;
    Boolean isCreated;

    public Patient(){

    }

    public Patient(String email, String password, String userType, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
