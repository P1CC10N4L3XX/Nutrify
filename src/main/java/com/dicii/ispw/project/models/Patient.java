package com.dicii.ispw.project.models;

public class Patient extends User {
    private String weight;
    private String height;

    public Patient(String email,String name,String surname,String dateOfBirth,String description,String weight, String height){
        super(email,name,surname,dateOfBirth,description);
        this.weight = weight;
        this.height = height;
    }
    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
