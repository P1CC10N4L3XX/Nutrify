package com.dicii.ispw.project.models;

public class Patient extends User {
    private float weight;
    private float height;

    public Patient(String email,String dateOfBirth, String description, float weight, float height){
        super(email,dateOfBirth,description);
        this.weight=weight;
        this.height=height;
    }
    public float getWeight() {
        return this.weight;
    }

    public float getHeight() {
        return this.height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
