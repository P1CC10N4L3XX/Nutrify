package com.dicii.ispw.project.models;

public class Recipe {
    private int id;
    private final String name;

    private final String description;

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

    public String getDescription() {
        return description;
    }




}
