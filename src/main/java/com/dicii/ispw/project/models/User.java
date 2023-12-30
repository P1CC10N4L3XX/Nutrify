package com.dicii.ispw.project.models;

import com.dicii.ispw.project.models.record.Credentials;
import com.dicii.ispw.project.models.record.PersonalInfo;

public class User {
    private String email;
    private String dateOfBirth;
    private String description;

    private PersonalInfo personalInfo;

    private Credentials credentials;

    public User(String email, String dateOfBirth, String description){
        this.email=email;
        this.dateOfBirth=dateOfBirth;
        this.description=description;
    }

    public String getName(){ return personalInfo.name();}
    public String getSurname() {
        return personalInfo.surname();
    }
    public String getEmail() {
        return this.email;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPassword() {
        return credentials.password();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
