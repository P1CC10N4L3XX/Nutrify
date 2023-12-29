package com.dicii.ispw.project.beans;

public class NutritionistSearchBean {
    private String name;
    private Float costFilter;
    private Float valutationFilter;

    public NutritionistSearchBean(String name,Float costFilter,Float valutationFilter){
        this.name = name;
        this.costFilter = costFilter;
        this.valutationFilter = valutationFilter;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCostFilter(){
        return this.costFilter;
    }
    public void setCostFilter(Float costFilter){
        this.costFilter = costFilter;
    }

    public Float getValutationFilter(){
        return this.valutationFilter;
    }
    public void setValutationFilter(Float valutationFilter){
        this.valutationFilter = valutationFilter;
    }
}
