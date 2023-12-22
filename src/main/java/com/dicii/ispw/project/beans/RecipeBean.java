package com.dicii.ispw.project.beans;

public class RecipeBean {

    private int id;
    private String name;
    private String description;

    private String type;

    public RecipeBean(int id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type=type;
    }

    public RecipeBean(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public String getType() {
        return type;
    }
}
