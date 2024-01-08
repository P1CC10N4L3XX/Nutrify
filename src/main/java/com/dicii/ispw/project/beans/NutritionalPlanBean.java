package com.dicii.ispw.project.beans;

import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Patient;

import java.util.ArrayList;
import java.util.List;

public class NutritionalPlanBean {

    private String data;

    private  String description;


    List<NutritionalPlanDayBean> nutritionalPlanDayBeanList;

    public NutritionalPlanBean(String description, String data){

        this.description=description;
        this.data=data;

    }


    public NutritionalPlanBean(){
        this.nutritionalPlanDayBeanList = new ArrayList<>();
    }




    public String getDescription() {
        return description;
    }

    public String getDate() {
        return this.data;
    }





}
