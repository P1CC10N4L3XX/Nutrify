package com.dicii.ispw.project.beans;

public class PatientBean {
    private String email;
    private String name;
    private String surname;
    private String description;
    private String dateOfBirth;
    private String weight;
    private String height;

    public PatientBean(String email,String name,String surname, String description, String dateOfBirth, String weight, String height){
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.description=description;
        this.dateOfBirth=dateOfBirth;
        this.weight=weight;
        this.height=height;
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

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
