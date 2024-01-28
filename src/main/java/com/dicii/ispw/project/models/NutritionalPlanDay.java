package com.dicii.ispw.project.models;
import com.dicii.ispw.project.patterns.Decorator.NutritionalPlan;

import java.util.ArrayList;
import java.util.List;

public class NutritionalPlanDay implements NutritionalPlan {


    protected String day;

    private String colazione;

    private String cena;

    private String pranzo;

    private String quantitaColazione;

    private String quantitaPranzo;

    private String quantitaCena;

    protected List<Recipe> recipeList;


    public NutritionalPlanDay( String day,String colazione, String pranzo, String cena, String quantitaColazioe,String quantitaPranzo,String quantitaCena){

        this.day =day;
        this.colazione=colazione;
        this.pranzo=pranzo;
        this.cena=cena;
        this.quantitaColazione=quantitaColazioe;
        this.quantitaPranzo=quantitaPranzo;
        this.quantitaCena=quantitaCena;

    }




    public NutritionalPlanDay() {
        recipeList = new ArrayList<>();
    }

    public List<Recipe> getRecipeList(){
        return recipeList;
    }

    public void addRecipe(Recipe recipe){
        recipeList.add(recipe);
    }

    public void addAllRecipe(List<Recipe> recipeList){
        this.recipeList = recipeList;
    }

    public String getDay() {
        return day;
    }


    public String getColazione() {
        return colazione;
    }

    public String getPranzo() {
        return pranzo;
    }

    public String getCena() {
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
