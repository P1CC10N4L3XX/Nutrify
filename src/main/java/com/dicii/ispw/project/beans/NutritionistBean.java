package com.dicii.ispw.project.beans;

public class NutritionistBean extends UserBean{
    private String description;
    private String dateOfBirth;
    private String IVA;
    private String IBAN;
    private Float cost;

    public NutritionistBean(String email,String password,String description, String dateOfBirth, String IVA, String IBAN, Float cost){
        super(email,password,true);
        this.description=description;
        this.dateOfBirth=dateOfBirth;
        this.IVA=IVA;
        this.IBAN=IBAN;
        this.cost=cost;
    }

    public String getDescription() {
        return description;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getIVA() {
        return IVA;
    }

    public String getIBAN() {
        return IBAN;
    }

    public Float getCost() {
        return cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setIVA(String IVA) {
        this.IVA = IVA;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }
}
