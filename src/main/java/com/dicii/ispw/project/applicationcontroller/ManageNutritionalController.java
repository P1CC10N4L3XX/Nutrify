package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.*;
import com.dicii.ispw.project.database.dao_classes.*;
import com.dicii.ispw.project.exceptions.*;
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
        Patient patientResult = new Patient();
        patientResult.setEmail(patientBean.getEmail());
        return patientResult;

    }

    public Nutritionist convertNutritionistBeanToModel(NutritionistBean nutritionistBean){
        return new Nutritionist(Session.getSessionInstance().getLoggedUser().getEmail());

    }

    public void createNutrutionalPlan(NutritionalPlanBean nutritionalPlanBean) throws NutritionalPlanBaseAlreadyCreated {


        if(nutritionalPlanBase==null){

                nutritionalPlanBase = new NutritionalPlanBase(nutritionalPlanBean.getDate(),convertPatientBeanToModel(nutritionalPlanBean.getPatient()),convertNutritionistBeanToModel(nutritionalPlanBean.getNutritionist()));
                NutritionalPlanDao nutritionalPlanDao = new NutritionalPlanDao();
                nutritionalPlanDao.saveNutritionalPlan(nutritionalPlanBase, nutritionalPlanBase.getNutritionist(),nutritionalPlanBase.getPatient());

        }


    }

    public UserCredentialsBean loadNutritionistSubscribed(String patient) throws NotExistentUserException {
        UserCredentialsBean userCredentialsBean = new UserCredentialsBean();
        User users;
        PatientDao patientDao = new PatientDao();
        users=patientDao.loadNutritionistSubscribed(patient);
        userCredentialsBean.setEmail(users.getEmail());
        return userCredentialsBean;
    }


    public NutritionalPlanDayBean displayNutritionalPlanDay(String day,String ilnesses, String email) throws NutritionalPlanNotFoundException, DuplicatedUserException {

        NutritionalPlanDayDao nutritionalPlanDayDao = new NutritionalPlanDayDao();
        boolean type = Session.getSessionInstance().getLoggedUser().getType();
        NutritionalPlanDay nutritionalPlanDayResult;


        if (type) {
            nutritionalPlanDayResult = nutritionalPlanDayDao.displayNutritionalPlanDay(email, Session.getSessionInstance().getLoggedUser().getEmail(), day);
        } else {
            nutritionalPlanDayResult = nutritionalPlanDayDao.displayNutritionalPlanDay(Session.getSessionInstance().getLoggedUser().getEmail(), email, day);
        }


        if (nutritionalPlanDayResult == null) {
            throw new NutritionalPlanNotFoundException("La data selezionata non ha nessun piano nutrizionale corrispondente, bisogna prima crearlo");
        }

        NutritionalPlanDayBean nutritionalPlanDayBean = new NutritionalPlanDayBean();


        NutritionalPlanApplaier nutritionalPlanApplaier = new NutritionalPlanApplaier(nutritionalPlanDayResult);
        String[] decoratedPlan = nutritionalPlanApplaier.applyDecorator(nutritionalPlanDayResult, ilnesses);


        nutritionalPlanDayBean.setColazione(convertModeltoRecipeBean(nutritionalPlanDayResult.getColazione()));
        nutritionalPlanDayBean.setPranzo(convertModeltoRecipeBean(nutritionalPlanDayResult.getPranzo()));
        nutritionalPlanDayBean.setCena(convertModeltoRecipeBean(nutritionalPlanDayResult.getCena()));
        nutritionalPlanDayBean.setQuantitaColazione(decoratedPlan[0]);
        nutritionalPlanDayBean.setQuantitaPranzo(decoratedPlan[1]);
        nutritionalPlanDayBean.setQuantitaCena(decoratedPlan[2]);
        nutritionalPlanDayBean.setDay(nutritionalPlanDayResult.getDay());

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
























