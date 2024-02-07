package com.dicii.ispw.project.models;

import java.io.Serializable;

public class Ilnesses implements Serializable {


    private String name;


    public Ilnesses(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

}
