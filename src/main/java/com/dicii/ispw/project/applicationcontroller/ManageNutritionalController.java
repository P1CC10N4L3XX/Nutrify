package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.*;
import com.dicii.ispw.project.database.dao_classes.*;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanFounded;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.models.*;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.patterns.singleton.Session;
import java.util.ArrayList;
import java.util.List;



public class ManageNutritionalController{



    private  NutritionalPlanBase nutritionalPlanBase;

    private NutritionalPlanDay nutritionalPlanDay;

    private Recipe recipe;

    private  Patient patient;



    public boolean sendNutritionalPlanDay(NutritionalPlanDayBean nutritionalPlanDayBean)  {


            nutritionalPlanBase = new NutritionalPlanBase();
            nutritionalPlanDay = new NutritionalPlanDay(nutritionalPlanDayBean.getDay(),convertRecipeBeanToModel(nutritionalPlanDayBean.getColazione()),convertRecipeBeanToModel(nutritionalPlanDayBean.getPranzo()), convertRecipeBeanToModel(nutritionalPlanDayBean.getCena()),nutritionalPlanDayBean.getQuantitaColazione(),nutritionalPlanDayBean.getQuantitaPranzo(),nutritionalPlanDayBean.getQuantitaCena(),nutritionalPlanDayBean.getDescription());
            NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
            nutritionalPlanBase.addNutritionalPlanDay(nutritionalPlanDay);

            return nutritionalPlanDayDao.saveNutritionalPlanDay(nutritionalPlanDay, Session.getSessionInstance().getLoggedUser().getEmail(), "nikita@gmail.com");



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
        patientBean.setIlnesses(convertIlnessesModelToBean(patient.getIlnesses()));
        return patientBean;

    }

    public IlnessesBean convertIlnessesModelToBean(Ilnesses ilnesses) {
        IlnessesBean ilnessesBean = new IlnessesBean();
        ilnessesBean.setName(ilnesses.getName());
        return ilnessesBean;
    }





    public void createNutrutionalPlan(NutritionalPlanBean nutritionalPlanBean) throws DuplicatedUserException {


        if(nutritionalPlanBase==null){

                nutritionalPlanBase = new NutritionalPlanBase(nutritionalPlanBean.getDate());

                //mi sto richiamando l'email del nutrizionista loggato
                //manca l'email del paziente
                NutritionalPlanDao nutritionalPlanDao = new NutritionalPlanDao();
                nutritionalPlanDao.saveNutritionalPlan(nutritionalPlanBase, Session.getSessionInstance().getLoggedUser().getEmail(),"nikita@gmail.com" );

        }


    }


    public NutritionalPlanDayBean displayNutritionalPlanDay(String day,String ilnesses) throws DuplicatedUserException,NutritionalPlanNotFoundException{

        NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
        boolean type=Session.getSessionInstance().getLoggedUser().getType();
        if(type){

            nutritionalPlanDay = nutritionalPlanDayDao.displayNutritionalPlanDay("luca@gmail.com", Session.getSessionInstance().getLoggedUser().getEmail(),day);

        }else{
             nutritionalPlanDay = nutritionalPlanDayDao.displayNutritionalPlanDay(Session.getSessionInstance().getLoggedUser().getEmail(),"marco@gmail.com",day);

        }
        if(nutritionalPlanDay==null){
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

        for (Recipe recipeName : recipes) {
            RecipeBean recipeBean = new RecipeBean();

            recipeBean.setName(recipeName.getName());
            recipeBean.setIngredients(recipeName.getIngredients());


            recipeBeanList.add(recipeBean);
        }

        return recipeBeanList;
    }



    public List<RecipeBean> displayRecipe() throws DuplicatedUserException {
        List<Recipe> recipes= RecipeDao.displayRecipe();
        List<RecipeBean> recipesBean;
        recipesBean=convertList(recipes);
        return recipesBean;
    }


    public void checkNutritionalPlanDay(String dataSelected) throws NutritionalPlanFounded, DuplicatedUserException {

        NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
        nutritionalPlanDayDao.checkNutritionalPlanDay(Session.getSessionInstance().getLoggedUser().getEmail(),"luca@gmail.com" ,dataSelected);
    }


    public void createNewRecipe( RecipeBean recipeBean) throws DuplicatedUserException {

        recipe = new Recipe(recipeBean.getName(), recipeBean.getDescription(), recipeBean.getIngredients());
        RecipeFileSaver recipeFileSaver = new RecipeFileSaver();
        RecipeDao recipeDao = new RecipeDao();

        recipeDao.saveRecipe(recipe);
        recipeFileSaver.saveRecipeInFile(recipe);

    }

    public void deleteRecipe(RecipeBean recipeBean) throws DuplicatedUserException {

        recipe = new Recipe(recipeBean.getName());
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.deleteRecipe(recipe);

    }
}
























