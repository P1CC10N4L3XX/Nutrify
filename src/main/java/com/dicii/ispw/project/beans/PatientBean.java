package com.dicii.ispw.project.beans;

public class PatientBean extends UserBean{
    private String description;
    private String dateOfBirth;
    private String weight;
    private String height;

    public PatientBean(String email,String password,String description, String dateOfBirth, String weight, String height){
        super(email,password,true);
        this.description=description;
        this.dateOfBirth=dateOfBirth;
        this.weight=weight;
        this.height=height;
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
