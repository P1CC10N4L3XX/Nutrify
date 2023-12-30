package com.dicii.ispw.project.beans;

import java.util.ArrayList;
import java.util.List;

public class NutritionalPlanDayBean {

    private int id;

    private final String day;
    List<RecipeBean> recipeBeanList;

    public NutritionalPlanDayBean(String day) {
        this.day = day;
        this.recipeBeanList = new ArrayList<>();
    }

    public List<RecipeBean> getExerciseBeanList() {
        return recipeBeanList;
    }

    public void setExerciseBeanList(List<RecipeBean> exerciseBeanList) {
        this.recipeBeanList = exerciseBeanList;
    }

    public String getDay() {
        return day;
    }

    public void addExerciseBean(RecipeBean exerciseBean) {
        recipeBeanList.add(exerciseBean);
    }
}
