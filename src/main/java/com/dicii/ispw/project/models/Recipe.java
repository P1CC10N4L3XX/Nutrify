package com.dicii.ispw.project.models;

public class Recipe {
    private int id;
    private String name;

    private  String description;

    private String ingredients;

    private final String category;

    public Recipe(int id, String name, String description, String category){
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
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
