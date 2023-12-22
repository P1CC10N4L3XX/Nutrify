package com.dicii.ispw.project.beans;

import java.util.ArrayList;
import java.util.List;

public class NutritionalPlanBean {


    private int id;

    private int quantita;
    private String description;

    //aggregazione
    List<RecipeBean> recipeBeanList;

    public NutritionalPlanBean(int id,int quantita, String description) {


        this.id=id;

        this.description=description;

        this.quantita=quantita;

        this.recipeBeanList= new ArrayList<>();

    }

    public int getNutritionalPlanNumber(){
        return id;
    }
    public String getDescription() {
        return description;
    }

    public List<RecipeBean> getRecipeBeanList(){
        return recipeBeanList;
    }

    public int getQuantita(){return quantita;}

    public void setRecipeBeanList(List<RecipeBean> recipeBeanList) {
        this.recipeBeanList = recipeBeanList;
    }

    public void addRecipeBean(RecipeBean recipeBean) {
        recipeBeanList.add(recipeBean);
    }



}
