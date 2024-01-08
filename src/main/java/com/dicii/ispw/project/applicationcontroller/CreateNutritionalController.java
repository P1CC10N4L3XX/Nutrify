package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.*;
import com.dicii.ispw.project.database.dao_classes.*;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.*;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.patterns.singleton.Session;

import java.util.List;



public class CreateNutritionalController{



    private  NutritionalPlanBase nutritionalPlanBase;

    private NutritionalPlanDay nutritionalPlanDay;

    private  Recipe recipe;

    private  Patient patient;

    private  Nutritionist nutritionist;



    public void createNutrutionalPlanDay(NutritionalPlanDayBean nutritionalPlanDayBean, UserBean userBean,PatientBean patientBean, NutritionistBean nutritionistBean, RecipeBean recipeBean) throws DuplicatedUserException {

        if (nutritionalPlanDay == null) {
            nutritionalPlanDay = new NutritionalPlanDay(nutritionalPlanDayBean.getDay(),nutritionalPlanDayBean.getColazione(),
                    nutritionalPlanDayBean.getPranzo(),nutritionalPlanDayBean.getCena(),nutritionalPlanDayBean.getQuantitaColazione(),
                    nutritionalPlanDayBean.getQuantitaPranzo(), nutritionalPlanDayBean.getQuantitaPranzo());
            NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
            nutritionalPlanDayDao.SaveNutritionalPlanDay(nutritionalPlanDay, patient, recipe, nutritionist);
        }
    }


    public void createNutrutionalPlan(NutritionalPlanBean nutritionalPlanBean) throws DuplicatedUserException {

        if(nutritionalPlanBase==null){

            nutritionalPlanBase = new NutritionalPlanBase(nutritionalPlanBean.getDescription(),nutritionalPlanBean.getDate());


            //mi sto richiamando l'email del nutrizionista loggato
            //manca l'email del paziente
            NutritionalPlanDao nutritionalPlanDao = new NutritionalPlanDao();
            nutritionalPlanDao.SaveNutritionalPlan(nutritionalPlanBase, Session.getSessionInstance().getLoggedUser().getEmail(),"paziente@gmail.com" );

        }


    }




    //mi deve ritornare un vettore di ricette
    public List<Recipe> displayRecipe() throws DuplicatedUserException {
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipes= recipeDao.displayRecipe();
        return recipes;
    }

















}
























