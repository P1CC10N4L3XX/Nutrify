package com.dicii.ispw.project.models;

import com.dicii.ispw.project.patterns.decorator.NutritionalPlan;

public class NutritionalPlanCeliaci extends NutritionalPlanDayDecorator{


    private static final String VERSION="g prendere versione senza glutine";


    protected NutritionalPlanCeliaci(NutritionalPlan component) {
        super(component);
    }



    @Override
    public String getQuantitaColazione() {
        String preliminaryResults = super.getQuantitaColazione();
        return preliminaryResults + VERSION ;
    }

    @Override
    public String getQuantitaPranzo() {
        String preliminaryResults = super.getQuantitaPranzo();
        return preliminaryResults + VERSION ;
    }

    @Override
    public String getQuantitaCena() {
        String preliminaryResults = super.getQuantitaCena();
        return preliminaryResults  + VERSION;
    }
}
