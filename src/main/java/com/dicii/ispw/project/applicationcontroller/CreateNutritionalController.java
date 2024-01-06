package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.*;
import com.dicii.ispw.project.database.dao_classes.NutritionalPlanDao;
import com.dicii.ispw.project.database.dao_classes.NutritionalPlanDayDao;
import com.dicii.ispw.project.database.dao_classes.NutritionistDao;
import com.dicii.ispw.project.database.dao_classes.PatientDao;
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











}
























