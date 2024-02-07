package com.dicii.ispw.project.models;


import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String name;
    private String surname;
    private String dateOfBirth;


    public User(String email,String name,String surname,String dateOfBirth){
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;

    }

    public User(String email){
        this.email = email;
    }

    public User(){}
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
