package com.dicii.ispw.project.models;

import com.dicii.ispw.project.patterns.decorator.NutritionalPlan;

public class NutritionalPlanCeliaci extends NutritionalPlanDayDecorator{


    private static final String VERSION="g prendere versione senza glutine";


    protected NutritionalPlanCeliaci(NutritionalPlan component) {
        super(component);
    }

    public String addInformation(String quantita){
        return quantita+VERSION;
    }


    @Override
    public String getQuantitaColazione() {
        String preliminaryResults = super.getQuantitaColazione();
        return addInformation(preliminaryResults);
    }

    @Override
    public String getQuantitaPranzo() {
        String preliminaryResults = super.getQuantitaPranzo();
        return addInformation(preliminaryResults);
    }

    @Override
    public String getQuantitaCena() {
        String preliminaryResults = super.getQuantitaCena();
        return addInformation(preliminaryResults);
    }
}
