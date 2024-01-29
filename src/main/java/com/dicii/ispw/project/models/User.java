package com.dicii.ispw.project.models;


public class User {
    private String email;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String description;

    public User(String email,String name,String surname,String dateOfBirth,String description){
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.description = description;
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
    public void setDescription(String description) {
        this.description = description;
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

    public String getDescription() {
        return description;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
