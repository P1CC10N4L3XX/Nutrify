package com.dicii.ispw.project.models;


public class Patient extends User {
    private String weight;
    private String height;

    private Ilnesses ilnesses;



    public Patient(String email){
        super(email);
    }

    public Patient(String email,String name,String surname,String dateOfBirth,String description,String weight, String height){
        super(email,name,surname,dateOfBirth,description);
        this.weight = weight;
        this.height = height;


    }



    public Patient(String email,String name,String surname,String dateOfBirth,String description,String weight, String height, Ilnesses ilnesses){
        super(email,name,surname,dateOfBirth,description);
        this.weight = weight;
        this.height = height;
        this.ilnesses=ilnesses;

    }

    public Patient(){}

    public Ilnesses getIlnesses(){
        return ilnesses;
    }

    public void setIlnesses(Ilnesses ilnesses){

        this.ilnesses=ilnesses;
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
