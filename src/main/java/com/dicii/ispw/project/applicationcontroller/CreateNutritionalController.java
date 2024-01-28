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
import java.lang.reflect.InvocationTargetException;
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

            nutritionalPlanDayDao.SaveNutritionalPlanDay(nutritionalPlanDay,  Session.getSessionInstance().getLoggedUser().getEmail(), "nikita@gmail.com",recipe);


        }catch (Exception e){
            System.out.println(e);
        }


    }

    public PatientBean displayUserInfo() throws DuplicatedUserException {

        //questo e il metodo in cui aggiungere quale utente e stato selezioato dalla View NutritionalPlan Dashboard
        PatientDao patientDao = new PatientDao();
        patient = patientDao.selectInfoPatient("luca@gmail.com");

        PatientBean patientBean = new PatientBean();

        patientBean.setName(patient.getName());
        patientBean.setSurname(patient.getSurname());
        patientBean.setDateOfBirth(patient.getDateOfBirth());
        patientBean.setWeight(patient.getWeight());
        patientBean.setHeight(patient.getHeight());
        patientBean.setIlnesses(patient.getIlnesses().getName());
        return patientBean;




    }




    public void createNutrutionalPlan(NutritionalPlanBean nutritionalPlanBean) throws DuplicatedUserException {


        if(nutritionalPlanBase==null){

            try{
                nutritionalPlanBase = new NutritionalPlanBase(nutritionalPlanBean.getDate());


                //mi sto richiamando l'email del nutrizionista loggato
                //manca l'email del paziente
                NutritionalPlanDao nutritionalPlanDao = new NutritionalPlanDao();
                nutritionalPlanDao.SaveNutritionalPlan(nutritionalPlanBase, Session.getSessionInstance().getLoggedUser().getEmail(),"nikita@gmail.com" );



            }catch (IOError e){
                System.out.println(e.getMessage());

            }

        }


    }


    public NutritionalPlanDayBean displayNutritionalPlanDay(String day,String ilnesses) throws DuplicatedUserException,NutritionalPlanNotFoundException, InvocationTargetException {




        NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
        NutritionalPlanDay nutritionalPlanDay = nutritionalPlanDayDao.displayNutritionalPlanDay("luca@gmail.com", Session.getSessionInstance().getLoggedUser().getEmail(),day);

        if(nutritionalPlanDay==null){
            throw new NutritionalPlanNotFoundException("la data selezionata non ha nessun piano nutrizionale corrispondente bisogna prima crealo ");

        }

        NutritionalPlanDayBean nutritionalPlanDayBean = new NutritionalPlanDayBean();

        if(ilnesses==null){



            nutritionalPlanDayBean.setColazione(nutritionalPlanDay.getColazione());
            nutritionalPlanDayBean.setPranzo(nutritionalPlanDay.getPranzo());
            nutritionalPlanDayBean.setCena(nutritionalPlanDay.getCena());
            nutritionalPlanDayBean.setQuantitaColazione(nutritionalPlanDay.getQuantitaColazione());
            nutritionalPlanDayBean.setQuantitaPranzo(nutritionalPlanDay.getQuantitaPranzo());
            nutritionalPlanDayBean.setQuantitaCena(nutritionalPlanDay.getQuantitaCena());
            nutritionalPlanDayBean.setDay(nutritionalPlanDay.getDay());


        }
        if( ilnesses != null && ilnesses.equals("Diabete") ){


            DiabeticDecorator diabeticDecorator = new DiabeticDecorator(nutritionalPlanDay);


            System.out.println(diabeticDecorator.getQuantitaColazione());
            nutritionalPlanDayBean.setColazione(nutritionalPlanDay.getColazione());
            nutritionalPlanDayBean.setPranzo(nutritionalPlanDay.getPranzo());
            nutritionalPlanDayBean.setCena(nutritionalPlanDay.getCena());
            nutritionalPlanDayBean.setQuantitaColazione(diabeticDecorator.getQuantitaColazione());
            nutritionalPlanDayBean.setQuantitaPranzo(diabeticDecorator.getQuantitaPranzo());
            nutritionalPlanDayBean.setQuantitaCena(diabeticDecorator.getQuantitaCena());
            nutritionalPlanDayBean.setDay(nutritionalPlanDay.getDay());


        }






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


    public void checkNutritionalPlanDay(String dataSelected) throws NutritionalPlanFounded, DuplicatedUserException {

        NutritionalPlanDay nutritionalPlanDay =new NutritionalPlanDay();
        NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
        nutritionalPlanDayDao.CheckNutritionalPlanDay(Session.getSessionInstance().getLoggedUser().getEmail(),"luca@gmail.com" ,dataSelected);
    }
}
























