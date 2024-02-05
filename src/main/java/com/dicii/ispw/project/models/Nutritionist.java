package com.dicii.ispw.project.models;

public class Nutritionist extends User{
    private String iva;
    private String iban;
    private String costo;

    private NutritionalPlanBase nutritionalPlanBase;
    public Nutritionist(String email){
        super(email);
    }

    public Nutritionist(String email,String name,String surname,String dateOfBirth,String description,String iva,String iban,String costo){
        super(email,name,surname,dateOfBirth,description);
        this.iva = iva;
        this.iban = iban;
        this.costo = costo;
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
