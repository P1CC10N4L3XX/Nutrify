package com.dicii.ispw.project.models;

import com.dicii.ispw.project.patterns.decorator.NutritionalPlan;

public abstract class NutritionalPlanDayDecorator extends NutritionalPlan {

    private NutritionalPlan component;

    protected NutritionalPlanDayDecorator(NutritionalPlan component){
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
