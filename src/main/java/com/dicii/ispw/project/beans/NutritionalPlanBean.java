package com.dicii.ispw.project.beans;
import java.util.ArrayList;
import java.util.List;

public class NutritionalPlanBean {

    private String data;

    List<NutritionalPlanDayBean> nutritionalPlanDayBeanList;

    public NutritionalPlanBean(String data){


        this.data=data;

    }


    public NutritionalPlanBean(){
        this.nutritionalPlanDayBeanList = new ArrayList<>();
    }


    public String getDate() {
        return this.data;
    }





}
