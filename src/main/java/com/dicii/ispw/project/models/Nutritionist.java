package com.dicii.ispw.project.models;

public class Nutritionist extends User{

    private String iban;
    private String costo;

    public Nutritionist(String email){
        super(email);
    }

    public Nutritionist(String email,String name,String surname,String dateOfBirth,String description,String iban,String costo){
        super(email,name,surname,dateOfBirth,description);
        this.iban = iban;
        this.costo = costo;
    }


    public String getIban() {
        return this.iban;
    }


    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getCosto() {
        return this.costo;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

}
