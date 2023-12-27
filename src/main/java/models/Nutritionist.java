package models;

import models.record.Credentials;
import models.record.PersonalInfo;

public class Nutritionist extends User {
    private String iban;
    private String partitaIva;

    //aggregazione tra nutrizionista e pianoNutrizionale
    private NutritionalPlan nutritionalPlan;

    public Nutritionist(String username, PersonalInfo personalInfo, Credentials credentials){
        super(username,personalInfo,credentials);
    }

    public Nutritionist(String username, PersonalInfo personalInfo, Credentials credentials, String  partitaIva, String iban){
        super(username, personalInfo, credentials);
        this.iban = iban;
        this.partitaIva=partitaIva;

    }

    public void setIban(String newIban){
        this.iban = newIban;
    }

    public String getIban() {
        return iban;
    }

    public void setPartitaIva(String newPartitaIva){
        this.iban = newPartitaIva;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

}
