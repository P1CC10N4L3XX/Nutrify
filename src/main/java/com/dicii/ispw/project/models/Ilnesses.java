package com.dicii.ispw.project.models;

public class Ilnesses {

    private int id;
    private final String name;

    private final String description;


    public Ilnesses(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;

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
