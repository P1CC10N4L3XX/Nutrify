package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.database.dao_classes.NutritionistDao;
import com.dicii.ispw.project.database.dao_classes.PatientDao;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.User;
import com.dicii.ispw.project.patterns.factory.UserFactory;

public class RegisterApplicationController {
    UserFactory factory;
    public RegisterApplicationController(){
        factory = new UserFactory();
    }
    public boolean verifyEmailField(String email){
        for(int i=0;i<email.length();i++){
            if(email.charAt(i)=='@'){
                return true;
            }
        }
        return false;
    }
    public boolean verifyPasswordField(String password, String confirmPassword){
        return password.equals(confirmPassword);
    }
    public void registerUser(UserBean userBean) throws DuplicatedUserException {
        if(userBean.getType()) {
            User nutritionist = factory.createNutritionist(userBean.getEmail(), userBean.getPassword());
            NutritionistDao nutritionistDAO = new NutritionistDao();
            nutritionistDAO.saveNutritionist(nutritionist);
        }else{
            User patient = factory.createPatient(userBean.getEmail(),userBean.getPassword());
            PatientDao patientDAO = new PatientDao();
            patientDAO.savePatient(patient);
        }


    }
}

