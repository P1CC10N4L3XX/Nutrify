package com.dicii.ispw.project.models;

import java.util.ArrayList;
import java.util.List;

public class NutritionalPlanBase  {


    private String data;



    private List<NutritionalPlanDay> nutritionalPlanDayList;




    public NutritionalPlanBase(String data){


        this.data=data;

    }


    public NutritionalPlanBase(){
        this.nutritionalPlanDayList = new ArrayList<>();
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
