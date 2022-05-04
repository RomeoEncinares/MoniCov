package com.chronos.monicov;

public class Patient extends User {
    String contact;
    String age;
    String gender;
    String birthDate;
    String address;
    String vaccineName;
    String vaccineDate1;
    String vaccineDate2;

    public Patient(){

    }

    public Patient(String email, String password, String userType, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getContact() {
        return contact;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public String getVaccineDate1() {
        return vaccineDate1;
    }

    public String getVaccineDate2() {
        return vaccineDate2;
    }
}
