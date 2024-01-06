package com.dicii.ispw.project.beans;

import java.util.ArrayList;
import java.util.List;

public class NutritionalPlanDayBean {

    private int id;

    private String colazione;

    private String cena;

    private String pranzo;

    private float quantitaColazione;

    private float quantitaPranzo;

    private float quantitaCena;

    private final String day;
    List<RecipeBean> recipeBeanList;

    public NutritionalPlanDayBean(String day,String colazione, String pranzo, String cena, float quantitaColazioe,float quantitaPranzo,float quantitaCena) {
        this.day = day;
        this.colazione = colazione;
        this.pranzo=pranzo;
        this.cena=cena;
        this.quantitaColazione=quantitaColazioe;
        this.quantitaPranzo=quantitaPranzo;
        this.quantitaCena=quantitaCena;

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



    public float getQuantitaColazione() {
        return quantitaColazione;
    }

    public float getQuantitaPranzo() {
        return quantitaPranzo;
    }

    public float getQuantitaCena() {
        return quantitaCena;
    }



    public void setColazione(float quantitaColazione) {
        this.colazione = colazione;
    }

    public void setPranzo(float quantitaPranzo) {
        this.pranzo = pranzo;
    }

    public void setCena(float quantitaCena) {
        this.cena = cena;
    }


    public void setQuantitaColazione(float quantitaColazione) {
        this.quantitaColazione = quantitaColazione;
    }

    public void setQuantitaPranzo(float quantitaPranzo) {
        this.quantitaPranzo = quantitaPranzo;
    }

    public void setQuantitaCena(float quantitaCena) {
        this.quantitaCena = quantitaCena;
    }




    public List<RecipeBean> getRecipeBeanList() {
        return recipeBeanList;
    }

    public void setRecipeBeanList(List<RecipeBean> exerciseBeanList) {
        this.recipeBeanList = exerciseBeanList;
    }

    public String getDay() {
        return day;
    }

    public void addRecipeBean(RecipeBean exerciseBean) {
        recipeBeanList.add(exerciseBean);
    }
}
