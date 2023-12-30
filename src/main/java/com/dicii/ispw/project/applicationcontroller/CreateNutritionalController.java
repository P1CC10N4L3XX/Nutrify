package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.*;
import com.dicii.ispw.project.models.*;
import com.dicii.ispw.project.beans.RecipeBean;

import java.util.ArrayList;
import java.util.List;



public class CreateNutritionalController{



    private  NutritionalPlanBase nutritionalPlanBase;

    private NutritionalPlanDay nutritionalPlanDay;

    private  Recipe recipe;

    private  Patient patient;

    private  Nutritionist nutritionist;


    //bisogna inserire colazione pranzo e cena
    public void addRecipeToNutritionalPlanDay(RecipeBean bean, NutritionalPlanDayBean bean2){

        if(nutritionalPlanDay == null ) {
            nutritionalPlanDay =new NutritionalPlanDay( bean2.getDay());


        }

        recipe =new Recipe( bean.getId(),bean.getName(), bean.getDescription(), bean.getType());

        nutritionalPlanDay.addRecipe(recipe);


    }

    public void removeRecipeToNutritionalPlanDay(RecipeBean bean, NutritionalPlanDayBean bean2){

        if(nutritionalPlanDay == null ) {
            nutritionalPlanDay =new NutritionalPlanDay( bean2.getDay());


        }

        recipe =new Recipe( bean.getId(),bean.getName(), bean.getDescription(), bean.getType());

        nutritionalPlanDay.removeRecipe(recipe.getName(),recipe.getDescription());


    }

    /*
    public List<RecipeBean> searchRecipe(SearchBean searchBean) {
        List<RecipeBean> exerciseList = new ArrayList<>();
        for(Recipe recipe: exerciseCatalogue.getExerciseList()) {
            if((exercise.getName().toLowerCase()).contains(searchBean.getName().toLowerCase())) {
                exerciseList.add(exercise);
            }
        }
        return getExerciseBeanList(exerciseList);
    }

     */


















}
