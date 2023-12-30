package com.dicii.ispw.project.models;
import com.dicii.ispw.project.models.Recipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NutritionalPlanDay {

    protected Integer id;
    protected String day;
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



    public void removeRecipe(String name, String description) {
        for(Recipe recipe: recipeList) {
            if(Objects.equals(recipe.getName(), name) && Objects.equals(recipe.getDescription(), description)) {
                recipeList.remove(recipe);
                return;
            }
        }
    }


}
