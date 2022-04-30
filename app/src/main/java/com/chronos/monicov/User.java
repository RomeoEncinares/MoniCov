package com.chronos.monicov;

public class User {
    protected String email;
    protected String password;
    protected String firstname;
    protected String lastname;
    protected String userType;

    public User() {
    }

    public User(String email, String password, String userType, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
