package com.dicii.ispw.project.applicationcontroller;


import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.database.dao_classes.NutritionalPlanDayDao;
import com.dicii.ispw.project.database.dao_classes.RecipeDao;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.NutritionalPlanDay;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Recipe;

public class CreateNewRecipeController{

    private Recipe recipe;

    private Nutritionist nutritionist;

    public void CreateNewRecipe( RecipeBean recipeBean) throws DuplicatedUserException {

        try {
            recipe = new Recipe(recipeBean.getName(), recipeBean.getDescription(), recipeBean.getIngredients());
            RecipeDao recipeDao = new RecipeDao();
            recipeDao.saveRecipe(recipe);
        }catch (Exception e){
            System.out.println(e);
        }
    }



/*
    public void CreateNewRecipe( RecipeBean recipeBean) throws DuplicatedUserException {

        try {
            recipe = new Recipe(recipeBean.getName(), recipeBean.getDescription(), recipeBean.getIngredients());
            RecipeDao recipeDao = new RecipeDao();
            recipeDao.saveRecipe(recipe, nutritionist);
        }catch (Exception e){
            System.out.println(e);
        }
    }


 */
}
