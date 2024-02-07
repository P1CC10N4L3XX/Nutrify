package com.dicii.ispw.project.models;


import java.util.ArrayList;
import java.util.List;

public class NutritionalPlanBase  {


    private String data;

    private Patient patient;

    private Nutritionist nutritionist;
    private List<NutritionalPlanDay> nutritionalPlanDayList;


    public NutritionalPlanBase(String data, Patient patient,Nutritionist nutritionist){

        this.data=data;
        this.patient=patient;
        this.nutritionist=nutritionist;
    }
    public NutritionalPlanBase(){
        this.nutritionalPlanDayList = new ArrayList<>();
    }



    public String getDate() {
        return this.data;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public Nutritionist getNutritionist() {
        return this.nutritionist;
    }


    public void setNutritionist(Nutritionist nutritionist) {
        this.nutritionist = nutritionist;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }



    public void setData(String data) {
        this.data = data;
    }


    public void addNutritionalPlanDay(NutritionalPlanDay nutritionalPlanDay){
        nutritionalPlanDayList.add(nutritionalPlanDay);
    }










}
