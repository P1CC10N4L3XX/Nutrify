package com.dicii.ispw.project.models;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    private int id;
    private String name;

    private  String description;

    private String ingredients;


    public Recipe( String name, String description, String ingredients){

        this.name = name;
        this.description = description;
        this.ingredients=ingredients;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description=description;
    }
    public String getDescription() {
        return description;
    }


    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public String getIngredients() {
        return ingredients;
    }


}
