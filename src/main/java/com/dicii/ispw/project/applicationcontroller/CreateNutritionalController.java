package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.*;
import com.dicii.ispw.project.database.dao_classes.*;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
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



    public void CreateNutrutionalPlanDay(NutritionalPlanDayBean nutritionalPlanDayBean, PatientBean patientBean,NutritionistBean nutritionistBean, RecipeBean recipeBean) throws DuplicatedUserException {

        if (nutritionalPlanDay == null) {
            nutritionalPlanDay = new NutritionalPlanDay(nutritionalPlanDayBean.getDay(),nutritionalPlanDayBean.getColazione(),
                    nutritionalPlanDayBean.getPranzo(),nutritionalPlanDayBean.getCena(),nutritionalPlanDayBean.getQuantitaColazione(),
                    nutritionalPlanDayBean.getQuantitaPranzo(), nutritionalPlanDayBean.getQuantitaPranzo());
            NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
            nutritionalPlanDayDao.SaveNutritionalPlanDay(nutritionalPlanDay, patient, recipe, nutritionist);
        }
    }




    //mi deve ritornare un vettore di ricette
    public List<Recipe> displayRecipe() throws DuplicatedUserException {
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipes= recipeDao.displayRecipe();
        return recipes;
    }














}
























