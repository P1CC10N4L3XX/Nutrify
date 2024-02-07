package com.dicii.ispw.project.beans;

public class UserBean {
    private String email;
    private String name;
    private String surname;
    private String dateOfBirth;

    public UserBean(){}

    public UserBean(String email){
        this.email = email;
    }
    public UserBean(String email, String name, String surname, String dateOfBirth){
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }




}
