package com.dicii.ispw.project.beans;

import java.util.ArrayList;
import java.util.List;

public class RecipeBean {


    private String name;
    private String description;
    private String ingredients;

    private List<RecipeBean> recipeBeanList;

    public RecipeBean( String name, String description, String ingredients) {

        this.name = name;
        this.description = description;
        this.ingredients=ingredients;

    }

    public RecipeBean( String name) {
        this.name = name;

    }

    public RecipeBean(){
        this.recipeBeanList = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients(){ return ingredients;}
}
