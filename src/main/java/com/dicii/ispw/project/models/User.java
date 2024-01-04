package com.dicii.ispw.project.models;

import com.dicii.ispw.project.models.record.Credentials;
import com.dicii.ispw.project.models.record.PersonalInfo;

public class User {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String description;

    public User(String email,String password){
        this.email = email;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDescription() {
        return description;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
