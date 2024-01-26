package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.*;
import com.dicii.ispw.project.database.dao_classes.*;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanFounded;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.models.*;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.patterns.singleton.Session;

import java.io.IOError;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;



public class CreateNutritionalController{



    private  NutritionalPlanBase nutritionalPlanBase;

    private NutritionalPlanDay nutritionalPlanDay;

    private  Recipe recipe;

    private  Patient patient;

    private  Nutritionist nutritionist;




    public void sendNutritionalPlanDay(NutritionalPlanDayBean nutritionalPlanDayBean){


        try {
            nutritionalPlanDay = new NutritionalPlanDay(nutritionalPlanDayBean.getDay(),nutritionalPlanDayBean.getColazione(), nutritionalPlanDayBean.getPranzo(), nutritionalPlanDayBean.getCena(),nutritionalPlanDayBean.getQuantitaColazione(),nutritionalPlanDayBean.getQuantitaPranzo(),nutritionalPlanDayBean.getQuantitaCena());
            NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();

            nutritionalPlanDayDao.SaveNutritionalPlanDay(nutritionalPlanDay, "patient", recipe, Session.getSessionInstance().getLoggedUser().getEmail());


        }catch (Exception e){
            System.out.println(e);
        }





    }


    public void createNutrutionalPlan(NutritionalPlanBean nutritionalPlanBean) throws DuplicatedUserException {


        if(nutritionalPlanBase==null){

            try{
                nutritionalPlanBase = new NutritionalPlanBase(nutritionalPlanBean.getDescription(),nutritionalPlanBean.getDate());


                //mi sto richiamando l'email del nutrizionista loggato
                //manca l'email del paziente
                NutritionalPlanDao nutritionalPlanDao = new NutritionalPlanDao();
                nutritionalPlanDao.SaveNutritionalPlan(nutritionalPlanBase, Session.getSessionInstance().getLoggedUser().getEmail(),"paziente@gmail.com" );



            }catch (IOError e){
                System.out.println(e.getMessage());

            }

        }


    }


    public NutritionalPlanDayBean displayNutritionalPlanDay(String day) throws DuplicatedUserException,NutritionalPlanNotFoundException{


            NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
            NutritionalPlanDay nutritionalPlanDay = nutritionalPlanDayDao.displayNutritionalPlanDay("patient", Session.getSessionInstance().getLoggedUser().getEmail(),day);


            if(nutritionalPlanDay==null){
                throw new NutritionalPlanNotFoundException("la data selezionata non ha nessun piano nutrizionale corrispondentebisogna prima crearlo, ");
            }



                NutritionalPlanDayBean nutritionalPlanDayBean = new NutritionalPlanDayBean();

                nutritionalPlanDayBean.setColazione(nutritionalPlanDay.getColazione());
                nutritionalPlanDayBean.setPranzo(nutritionalPlanDay.getPranzo());
                nutritionalPlanDayBean.setCena(nutritionalPlanDay.getCena());
                nutritionalPlanDayBean.setQuantitaColazione(nutritionalPlanDay.getQuantitaColazione());
                nutritionalPlanDayBean.setQuantitaPranzo(nutritionalPlanDay.getQuantitaPranzo());
                nutritionalPlanDayBean.setQuantitaCena(nutritionalPlanDay.getQuantitaCena());
                nutritionalPlanDayBean.setDay(nutritionalPlanDay.getDay());


                return nutritionalPlanDayBean;




    }






    public List<RecipeBean> convertList(List<Recipe> recipes) {
        //converto il vettore di Recipe in RecipeBean
        List<RecipeBean> recipeBeanList = new ArrayList<>();

        for (Recipe recipe : recipes) {
            RecipeBean recipeBean = new RecipeBean();

            recipeBean.setName(recipe.getName());
            recipeBean.setIngredients(recipe.getIngredients());


            recipeBeanList.add(recipeBean);
        }

        return recipeBeanList;
    }



    public List<RecipeBean> displayRecipe() throws DuplicatedUserException {
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipes= recipeDao.displayRecipe();
        List<RecipeBean> recipesBean;
        recipesBean=convertList(recipes);
        return recipesBean;
    }














}
























