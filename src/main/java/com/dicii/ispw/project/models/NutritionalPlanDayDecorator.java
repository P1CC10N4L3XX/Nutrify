package com.dicii.ispw.project.models;

import com.dicii.ispw.project.patterns.singleton.Decorator.NutritionalPlan;

public class NutritionalPlanDayDecorator implements NutritionalPlan {

    private NutritionalPlan component;

    public NutritionalPlanDayDecorator(NutritionalPlan nutritionalPlan){
        this.component=component;
    }

    @Override
    public String getQuantitaColazione() {
        String resutlFromRedirection=this.component.getQuantitaColazione();
        return resutlFromRedirection;
    }

    @Override
    public String getQuantitaPranzo() {
        String resutlFromRedirection=this.component.getQuantitaPranzo();
        return resutlFromRedirection;
    }

    @Override
    public String getQuantitaCena() {
        String resutlFromRedirection=this.component.getQuantitaCena();
        return resutlFromRedirection;
    }
}
