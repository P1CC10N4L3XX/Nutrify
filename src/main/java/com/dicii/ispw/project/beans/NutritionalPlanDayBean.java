package com.dicii.ispw.project.beans;




import java.util.List;

public class NutritionalPlanDayBean {


    private RecipeBean colazione;

    private RecipeBean cena;

    private RecipeBean pranzo;

    private String quantitaColazione;

    private String quantitaPranzo;

    private String quantitaCena;

    private String day;


    List<RecipeBean> recipeBeanList;

    public NutritionalPlanDayBean(String day,RecipeBean colazione, RecipeBean pranzo, RecipeBean cena, String quantitaColazioe,String quantitaPranzo,String quantitaCena) {
        this.day = day;
        this.colazione=colazione;
        this.pranzo=pranzo;
        this.cena=cena;
        this.quantitaColazione=quantitaColazioe;
        this.quantitaPranzo=quantitaPranzo;
        this.quantitaCena=quantitaCena;

    }

    public NutritionalPlanDayBean(){}




    public RecipeBean getColazione() {

        return colazione;
    }

    public RecipeBean getPranzo() {
        return pranzo;
    }

    public RecipeBean getCena() {
        return cena;
    }



    public String getQuantitaColazione() {
        return quantitaColazione;
    }

    public String getQuantitaPranzo() {
        return quantitaPranzo;
    }

    public String getQuantitaCena() {
        return quantitaCena;
    }



    public void setColazione(RecipeBean colazione) {
        this.colazione = colazione;
    }

    public void setPranzo(RecipeBean pranzo) {
        this.pranzo = pranzo;
    }

    public void setCena(RecipeBean cena) {
        this.cena = cena;
    }


    public void setQuantitaColazione(String quantitaColazione) {
        this.quantitaColazione = quantitaColazione;
    }

    public void setQuantitaPranzo(String quantitaPranzo) {
        this.quantitaPranzo = quantitaPranzo;
    }

    public void setQuantitaCena(String quantitaCena) {
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

    public void setDay(String day) {

        this.day = day;
    }

    public void addRecipeBean(RecipeBean exerciseBean) {
        recipeBeanList.add(exerciseBean);
    }
}
