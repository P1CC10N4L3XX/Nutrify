package com.dicii.ispw.project.models;

import com.dicii.ispw.project.patterns.decorator.NutritionalPlan;

public class NutritionalPlanCeliaci extends NutritionalPlanDayDecorator{
    protected NutritionalPlanCeliaci(NutritionalPlan component) {
        super(component);
    }

    //funzionalita da implementare
}
