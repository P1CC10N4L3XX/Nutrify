package com.dicii.ispw.project.models;

import com.dicii.ispw.project.patterns.singleton.Decorator.NutritionalPlan;

import java.util.ArrayList;
import java.util.List;

public class NutritionalPlanBase  {


    private String data;


    private String description;


    private List<NutritionalPlanDay> nutritionalPlanDayList;




    public NutritionalPlanBase(String description, String data){

        this.description=description;
        this.data=data;

    }


    public NutritionalPlanBase(){
        this.nutritionalPlanDayList = new ArrayList<>();
    }





    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description=description;
    }

    public String getDate() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<NutritionalPlanDay> getNutritionalPlanBaseDayList() {
        return nutritionalPlanDayList;
    }







}
