package com.dicii.ispw.project.models;

import com.dicii.ispw.project.models.NutritionalPlanDay;
import java.util.ArrayList;
import java.util.List;

public abstract class NutritionalPlanBase implements NutritionalPlan{

    private int id;

    private String data;

    private String description;
    private List<NutritionalPlanDay> nutritionalPlanDayList;

    public NutritionalPlanBase(int id){
        this();
        this.id = id;
    }

    public NutritionalPlanBase(){
        this.nutritionalPlanDayList = new ArrayList<>();
    }

    public int getId() {
        return id;
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



    public void addNutritionalPlanDay(NutritionalPlanDay nutritionalPlanDay) {
        nutritionalPlanDayList.add(nutritionalPlanDay);
    }

    public void removeNutritionalPlanDay(NutritionalPlanDay nutritionalPlanDay) {
        nutritionalPlanDayList.remove(nutritionalPlanDay);
    }
}
