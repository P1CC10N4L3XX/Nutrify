package com.dicii.ispw.project.models;
import com.dicii.ispw.project.patterns.Decorator.NutritionalPlan;

public class NutritionalPlanDay implements NutritionalPlan {


    protected String day;

    private Recipe colazione;

    private Recipe cena;

    private Recipe pranzo;

    private String quantitaColazione;

    private String quantitaPranzo;

    private String quantitaCena;




    public NutritionalPlanDay( String day,Recipe colazione, Recipe pranzo, Recipe cena, String quantitaColazioe,String quantitaPranzo,String quantitaCena){

        this.day =day;
        this.colazione=colazione;
        this.pranzo=pranzo;
        this.cena=cena;
        this.quantitaColazione=quantitaColazioe;
        this.quantitaPranzo=quantitaPranzo;
        this.quantitaCena=quantitaCena;

    }


    public String getDay() {
        return day;
    }


    public Recipe getColazione() {
        return colazione;
    }

    public Recipe getPranzo() {
        return pranzo;
    }

    public Recipe getCena() {
        return cena;
    }

    @Override
    public String getQuantitaColazione() {
        return quantitaColazione;
    }
    @Override
    public String getQuantitaPranzo() {
        return quantitaPranzo;
    }
    @Override
    public String getQuantitaCena() {
        return quantitaCena;
    }








}
