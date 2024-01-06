package com.dicii.ispw.project.beans;

public class RecipeBean {


    private String name;
    private String description;
    private String ingredients;

    private String type;

    public RecipeBean( String name, String description, String ingredients) {

        this.name = name;
        this.description = description;
        this.ingredients=ingredients;

    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients(){ return ingredients;}
}
