package com.dicii.ispw.project.models;

import com.dicii.ispw.project.patterns.decorator.NutritionalPlan;

public class NutritionalPlanApplaier {
    //This class has been built with the responsibility of apply Decorator Pattern.
    private NutritionalPlan nutritionalPlan;

    public NutritionalPlanApplaier(NutritionalPlan nutritionalPlan) {
        this.nutritionalPlan = nutritionalPlan;
    }

    public String[] applyDecorator(NutritionalPlanDay nutritionalPlanDay, String illness) {
        if (illness != null && illness.equals("Diabete")) {

            DiabeticDecorator diabeticDecorator = new DiabeticDecorator(nutritionalPlanDay);
            return new String[] {
                    diabeticDecorator.getQuantitaColazione(),
                    diabeticDecorator.getQuantitaPranzo(),
                    diabeticDecorator.getQuantitaCena()
            };
        }
        if(illness != null && illness.equals("Celiacita")){

            NutritionalPlanCeliaci nutritionalPlanCeliaci = new NutritionalPlanCeliaci(nutritionalPlanDay);
            return new String[] {
                    nutritionalPlanCeliaci.getQuantitaColazione(),
                    nutritionalPlanCeliaci.getQuantitaPranzo(),
                    nutritionalPlanCeliaci.getQuantitaCena()
            };

        }else{
            return new String[] {
                    nutritionalPlanDay.getQuantitaColazione(),
                    nutritionalPlanDay.getQuantitaPranzo(),
                    nutritionalPlanDay.getQuantitaCena()
            };
        }
    }


}
