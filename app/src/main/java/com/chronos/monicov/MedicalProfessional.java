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

    static class assignedPatient{
        String code;
        String patientFirstName;
        String patientLastName;
        String patientEmail;

        public assignedPatient(){
        }

        public assignedPatient(String code, String patientEmail, String patientFirstName, String patientLastName){
            this.code = code;
            this.patientEmail = patientEmail;
            this.patientFirstName = patientFirstName;
            this.patientLastName = patientLastName;
        }

        public String getCode() {
            return code;
        }

        public String getPatientFirstName() {
            return patientFirstName;
        }

        public String getPatientLastName() {
            return patientLastName;
        }

        public String getPatientEmail() {
            return patientEmail;
        }
    }
}
