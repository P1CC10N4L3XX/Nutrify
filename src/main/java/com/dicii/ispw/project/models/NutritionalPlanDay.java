package com.dicii.ispw.project.models;
import com.dicii.ispw.project.models.Recipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NutritionalPlanDay {

    protected Integer id;
    protected String day;

    private String colazione;

    private String cena;

    private String pranzo;

    private float quantitaColazione;

    private float quantitaPranzo;

    private float quantitaCena;




    protected transient List<Recipe> recipeList;

    public NutritionalPlanDay(int id, String day, List<Recipe> recipeList){
        this(day);
        this.id = id;
        addAllRecipe(recipeList);
    }

    public NutritionalPlanDay(String day) {
        this();
        this.day = day;
    }

    private NutritionalPlanDay () {
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

    public int getId() {
        return id;
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





}
