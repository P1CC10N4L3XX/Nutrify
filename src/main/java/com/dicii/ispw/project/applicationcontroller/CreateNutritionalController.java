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
            NutritionalPlanBase nutritionalPlanBase = new NutritionalPlanBase();
            nutritionalPlanDay = new NutritionalPlanDay(nutritionalPlanDayBean.getDay(),convertRecipeBeanToModel(nutritionalPlanDayBean.getColazione()),convertRecipeBeanToModel(nutritionalPlanDayBean.getPranzo()), convertRecipeBeanToModel(nutritionalPlanDayBean.getCena()),nutritionalPlanDayBean.getQuantitaColazione(),nutritionalPlanDayBean.getQuantitaPranzo(),nutritionalPlanDayBean.getQuantitaCena());
            NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
            nutritionalPlanBase.addNutritionalPlanDay(nutritionalPlanDay);

            nutritionalPlanDayDao.SaveNutritionalPlanDay(nutritionalPlanDay,  Session.getSessionInstance().getLoggedUser().getEmail(), "nikita@gmail.com");


        }catch (Exception e){
            System.out.println(e);
        }


    }

    public Recipe convertRecipeBeanToModel(RecipeBean recipeBean) {
        Recipe recipeModel = new Recipe();
        recipeModel.setName(recipeBean.getName());
        recipeModel.setDescription(recipeBean.getDescription());
        recipeModel.setIngredients(recipeBean.getIngredients());
        return recipeModel;
    }

    //appena si ha l'email dell utente andra passata come paraetro
    //String emailPatient;
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
        if(Session.getSessionInstance().getLoggedUser().getType()){
            //se sono il nutrizionista
            nutritionalPlanDay = nutritionalPlanDayDao.displayNutritionalPlanDay("luca@gmail.com", Session.getSessionInstance().getLoggedUser().getEmail(),day);

        }else{

            //se sono il paziente
             nutritionalPlanDay = nutritionalPlanDayDao.displayNutritionalPlanDay(Session.getSessionInstance().getLoggedUser().getEmail(),"marco@gmail.com",day);


        }if(nutritionalPlanDay==null){
            throw new NutritionalPlanNotFoundException("la data selezionata non ha nessun piano nutrizionale corrispondente bisogna prima crealo ");

        }

        NutritionalPlanDayBean nutritionalPlanDayBean = new NutritionalPlanDayBean();

        if(ilnesses==null){

            nutritionalPlanDayBean.setColazione(convertModeltoRecipeBean(nutritionalPlanDay.getColazione()));
            nutritionalPlanDayBean.setPranzo(convertModeltoRecipeBean(nutritionalPlanDay.getPranzo()));
            nutritionalPlanDayBean.setCena(convertModeltoRecipeBean(nutritionalPlanDay.getCena()));
            nutritionalPlanDayBean.setQuantitaColazione(nutritionalPlanDay.getQuantitaColazione());
            nutritionalPlanDayBean.setQuantitaPranzo(nutritionalPlanDay.getQuantitaPranzo());
            nutritionalPlanDayBean.setQuantitaCena(nutritionalPlanDay.getQuantitaCena());
            nutritionalPlanDayBean.setDay(nutritionalPlanDay.getDay());


        }
        if( ilnesses != null && ilnesses.equals("Diabete") ){


            DiabeticDecorator diabeticDecorator = new DiabeticDecorator(nutritionalPlanDay);


            System.out.println(diabeticDecorator.getQuantitaColazione());
            nutritionalPlanDayBean.setColazione(convertModeltoRecipeBean(nutritionalPlanDay.getColazione()));
            nutritionalPlanDayBean.setPranzo(convertModeltoRecipeBean(nutritionalPlanDay.getPranzo()));
            nutritionalPlanDayBean.setCena(convertModeltoRecipeBean(nutritionalPlanDay.getCena()));
            nutritionalPlanDayBean.setQuantitaColazione(diabeticDecorator.getQuantitaColazione());
            nutritionalPlanDayBean.setQuantitaPranzo(diabeticDecorator.getQuantitaPranzo());
            nutritionalPlanDayBean.setQuantitaCena(diabeticDecorator.getQuantitaCena());
            nutritionalPlanDayBean.setDay(nutritionalPlanDay.getDay());


        }

        return nutritionalPlanDayBean;

    }

    public RecipeBean convertModeltoRecipeBean(Recipe recipe) {
        RecipeBean recipeBean = new RecipeBean();
        recipeBean.setName(recipe.getName());
        recipeBean.setDescription(recipe.getDescription());
        recipeBean.setIngredients(recipe.getIngredients());
        return recipeBean;
    }



    public List<RecipeBean> convertList(List<Recipe> recipes) {

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



    public RecipeBean readRecipeFromFile(){
        Recipe recipe;
        RecipeBean recipeBean = null;
        RecipeFileSaver recipeFileSaver = new RecipeFileSaver();
        recipe=recipeFileSaver.loadRecipeFromFile();
        recipeBean.setName(recipe.getName());

        return recipeBean;
    }


    public void checkNutritionalPlanDay(String dataSelected) throws NutritionalPlanFounded, DuplicatedUserException {

        NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
        nutritionalPlanDayDao.CheckNutritionalPlanDay(Session.getSessionInstance().getLoggedUser().getEmail(),"luca@gmail.com" ,dataSelected);
    }
}
























