package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.*;
import com.dicii.ispw.project.database.dao_classes.*;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanFounded;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.models.*;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.patterns.decorator.NutritionalPlan;
import com.dicii.ispw.project.patterns.singleton.Session;
import java.util.ArrayList;
import java.util.List;



public class ManageNutritionalController{



    private  NutritionalPlanBase nutritionalPlanBase;

    private NutritionalPlanDay nutritionalPlanDay;

    private Recipe recipe;

    private  Patient patient;



    public boolean sendNutritionalPlanDay(NutritionalPlanDayBean nutritionalPlanDayBean, String emailPatient)  {

            nutritionalPlanBase = new NutritionalPlanBase();
            nutritionalPlanDay = new NutritionalPlanDay(nutritionalPlanDayBean.getDay(),convertRecipeBeanToModel(nutritionalPlanDayBean.getColazione()),convertRecipeBeanToModel(nutritionalPlanDayBean.getPranzo()), convertRecipeBeanToModel(nutritionalPlanDayBean.getCena()),nutritionalPlanDayBean.getQuantitaColazione(),nutritionalPlanDayBean.getQuantitaPranzo(),nutritionalPlanDayBean.getQuantitaCena());
            NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
            nutritionalPlanBase.addNutritionalPlanDay(nutritionalPlanDay);

            return nutritionalPlanDayDao.saveNutritionalPlanDay(nutritionalPlanDay, Session.getSessionInstance().getLoggedUser().getEmail(), emailPatient);

    }

    public Recipe convertRecipeBeanToModel(RecipeBean recipeBean) {
        Recipe recipeModel = new Recipe();
        recipeModel.setName(recipeBean.getName());
        recipeModel.setDescription(recipeBean.getDescription());
        recipeModel.setIngredients(recipeBean.getIngredients());
        return recipeModel;
    }


    public PatientBean displayUserInfo(String patientSelected) {


        PatientDao patientDao = new PatientDao();
        patient = patientDao.selectInfoPatient(patientSelected);

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



    public List<PatientBean> convertPatientList(List<Patient> patients) {

        List<PatientBean> patientBeanList = new ArrayList<>();

        for (Patient patientName : patients) {
            PatientBean patientBean = new PatientBean();

            patientBean.setEmail(patientName.getEmail());

            patientBeanList.add(patientBean);
        }

        return patientBeanList;
    }


    public List<PatientBean> displayPatient(String emailNutritionist){

        List<Patient> patients= PatientDao.displayPatient(emailNutritionist);
        List<PatientBean> patientsBean;
        patientsBean=convertPatientList(patients);
        return patientsBean;

    }


    public Patient convertPatientBeanToModel(PatientBean patientBean){
        Patient patient = new Patient();
        patient.setEmail(patientBean.getEmail());
        return patient;

    }

    public Nutritionist convertNutritionistBeanToModel(NutritionistBean nutritionistBean){
        Nutritionist nutritionist = new Nutritionist(Session.getSessionInstance().getLoggedUser().getEmail());
        return nutritionist;

    }

    public void createNutrutionalPlan(NutritionalPlanBean nutritionalPlanBean) throws DuplicatedUserException {


        if(nutritionalPlanBase==null){

                nutritionalPlanBase = new NutritionalPlanBase(nutritionalPlanBean.getDate(),convertPatientBeanToModel(nutritionalPlanBean.getPatient()),convertNutritionistBeanToModel(nutritionalPlanBean.getNutritionist()));
                NutritionalPlanDao nutritionalPlanDao = new NutritionalPlanDao();
                nutritionalPlanDao.saveNutritionalPlan(nutritionalPlanBase, nutritionalPlanBase.getNutritionist(),nutritionalPlanBase.getPatient());

        }


    }

    public UserBean loadNutritionistSubscribed(String patient) throws NotExistentUserException {
        UserBean userBean = new UserBean();
        User users;
        PatientDao patientDao = new PatientDao();
        users=patientDao.loadNutritionistSubscribed(patient);
        userBean.setEmail(users.getEmail());
        return userBean;
    }


    public NutritionalPlanDayBean displayNutritionalPlanDay(String day,String ilnesses, String email) throws DuplicatedUserException,NutritionalPlanNotFoundException{

        NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
        boolean type = Session.getSessionInstance().getLoggedUser().getType();
        NutritionalPlanDay nutritionalPlanDay;


        if (type) {
            nutritionalPlanDay = nutritionalPlanDayDao.displayNutritionalPlanDay(email, Session.getSessionInstance().getLoggedUser().getEmail(), day);
        } else {
            nutritionalPlanDay = nutritionalPlanDayDao.displayNutritionalPlanDay(Session.getSessionInstance().getLoggedUser().getEmail(), email, day);
        }


        if (nutritionalPlanDay == null) {
            throw new NutritionalPlanNotFoundException("La data selezionata non ha nessun piano nutrizionale corrispondente, bisogna prima crearlo");
        }

        NutritionalPlanDayBean nutritionalPlanDayBean = new NutritionalPlanDayBean();


        NutritionalPlanApplaier nutritionalPlanApplaier = new NutritionalPlanApplaier(nutritionalPlanDay);
        String[] decoratedPlan = nutritionalPlanApplaier.applyDecorator(nutritionalPlanDay, ilnesses);


        nutritionalPlanDayBean.setColazione(convertModeltoRecipeBean(nutritionalPlanDay.getColazione()));
        nutritionalPlanDayBean.setPranzo(convertModeltoRecipeBean(nutritionalPlanDay.getPranzo()));
        nutritionalPlanDayBean.setCena(convertModeltoRecipeBean(nutritionalPlanDay.getCena()));
        nutritionalPlanDayBean.setQuantitaColazione(decoratedPlan[0]);
        nutritionalPlanDayBean.setQuantitaPranzo(decoratedPlan[1]);
        nutritionalPlanDayBean.setQuantitaCena(decoratedPlan[2]);
        nutritionalPlanDayBean.setDay(nutritionalPlanDay.getDay());

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


    public void checkNutritionalPlanDay(String dataSelected, String emailPatient) throws NutritionalPlanFounded, DuplicatedUserException {

        NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
        nutritionalPlanDayDao.checkNutritionalPlanDay(Session.getSessionInstance().getLoggedUser().getEmail(),emailPatient,dataSelected);
    }


    public void createNewRecipe( RecipeBean recipeBean) throws DuplicatedUserException {

        recipe = new Recipe(recipeBean.getName(), recipeBean.getDescription(), recipeBean.getIngredients());
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.saveRecipe(recipe);

    }

    public void deleteRecipe(RecipeBean recipeBean) throws DuplicatedUserException {

        recipe = new Recipe(recipeBean.getName());
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.deleteRecipe(recipe);

    }
}
























