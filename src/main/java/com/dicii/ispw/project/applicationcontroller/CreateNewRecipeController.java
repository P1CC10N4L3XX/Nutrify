package com.dicii.ispw.project.applicationcontroller;


import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.beans.NutritionalPlanDayBean;
import com.dicii.ispw.project.database.dao_classes.NutritionalPlanDayDao;
import com.dicii.ispw.project.database.dao_classes.RecipeDao;
import com.dicii.ispw.project.database.dao_classes.RecipeFileSaver;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.NutritionalPlanDay;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Recipe;

public class CreateNewRecipeController{

    private Recipe recipe;

    private NutritionalPlanDay nutritionalPlanDay;

    private Nutritionist nutritionist;

    public void createNewRecipe( RecipeBean recipeBean) throws DuplicatedUserException {

        try {
            recipe = new Recipe(recipeBean.getName(), recipeBean.getDescription(), recipeBean.getIngredients());
            RecipeFileSaver recipeFileSaver = new RecipeFileSaver();
            RecipeDao recipeDao = new RecipeDao();

            recipeDao.saveRecipe(recipe);
            recipeFileSaver.saveRecipeInFile(recipe);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteRecipe(RecipeBean recipeBean){

        try {
            recipe = new Recipe(recipeBean.getName());
            RecipeDao recipeDao = new RecipeDao();
            recipeDao.deleteRecipe(recipe);

        }catch (Exception e){
            System.out.println(e);
        }


    }



}
