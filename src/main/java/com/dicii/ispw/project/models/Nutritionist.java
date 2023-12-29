package com.dicii.ispw.project.models;

public class Nutritionist extends User{
    private String iva;
    private String iban;

    public Nutritionist(String email,String dateOfBirth, String description, String iva, String iban){
        super(email,dateOfBirth,description);
        this.iva=iva;
        this.iban=iban;
    }
    public String getIva() {
        return this.iva;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

}
