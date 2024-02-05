package com.dicii.ispw.project.beans;

public class NutritionistBean{
    private String email;
    private String name;
    private String surname;
    private String description;
    private String dateOfBirth;
    private String iva;
    private String iban;
    private String cost;
    public NutritionistBean(String email){
        this.email = email;
    }
    public NutritionistBean(String email,String name,String surname,String description, String dateOfBirth, String iban, String cost){
        this.email=email;
        this.name=name;
        this.surname=surname;
        this.description=description;
        this.dateOfBirth=dateOfBirth;
        this.iban=iban;
        this.cost=cost;
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



    public String getIBAN() {
        return iban;
    }

    public String getCost() {
        return cost;
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

    public void setIBAN(String iban) {
        this.iban = iban;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
