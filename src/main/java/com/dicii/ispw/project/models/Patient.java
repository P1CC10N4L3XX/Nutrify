package com.dicii.ispw.project.models;

public class Patient extends User {
    private float weight;
    private float height;

    public Patient(String email,String password){
        super(email,password);
    }
    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
