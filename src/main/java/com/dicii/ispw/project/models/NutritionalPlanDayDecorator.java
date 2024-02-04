package com.dicii.ispw.project.models;

import com.dicii.ispw.project.patterns.Decorator.NutritionalPlan;

public abstract class NutritionalPlanDayDecorator implements NutritionalPlan {

    private NutritionalPlan component;

    public NutritionalPlanDayDecorator(NutritionalPlan component){
        this.component=component;
    }

    @Override
    public String getQuantitaColazione() {
        return this.component.getQuantitaColazione();
    }

    @Override
    public String getQuantitaPranzo() {
        return this.component.getQuantitaPranzo();
    }

    @Override
    public String getQuantitaCena() {
        return this.component.getQuantitaCena();
    }
}
