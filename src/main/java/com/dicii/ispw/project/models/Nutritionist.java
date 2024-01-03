package com.dicii.ispw.project.models;

import com.dicii.ispw.project.models.record.Credentials;
import com.dicii.ispw.project.models.record.PersonalInfo;

import java.util.List;

public class Nutritionist extends User{
    private String iva;
    private String iban;
    private String costo;

    protected transient List<NutritionalPlanBase> NutritionalPlanBaseList;


    public Nutritionist(String email, PersonalInfo personalInfo, Credentials credentials, String iva, String iban, String costo){
        super(email,personalInfo,credentials);
        this.iva=iva;
        this.iban=iban;
        this.costo=costo;
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
