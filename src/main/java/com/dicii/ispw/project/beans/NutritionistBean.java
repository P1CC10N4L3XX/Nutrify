package com.dicii.ispw.project.beans;

public class NutritionistBean extends UserBean{
    private String iban;
    private String cost;
    private String description;
    public NutritionistBean(String email){
        super(email);
    }
    public NutritionistBean(String email,String name,String surname,String description, String dateOfBirth, String iban, String cost){
        super(email,name,surname,dateOfBirth);
        this.iban=iban;
        this.cost=cost;
        this.description=description;
    }

    public String getIBAN() {
        return iban;
    }

    public String getCost() {
        return cost;
    }

    public void setIBAN(String iban) {
        this.iban = iban;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
    public String getDescription() {
        return description;
    }

}
