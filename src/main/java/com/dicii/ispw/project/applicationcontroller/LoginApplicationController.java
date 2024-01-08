package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.database.dao_classes.NutritionistDao;
import com.dicii.ispw.project.database.dao_classes.PatientDao;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.models.UserCredentials;

public class LoginApplicationController {
    public UserBean loginUser(UserBean userBeanCredentials) throws NotExistentUserException {
        UserCredentials userCredentials = new UserCredentials(userBeanCredentials.getEmail(), userBeanCredentials.getPassword());
        if(userBeanCredentials.getType()){
            NutritionistDao nutritionistDAO = new NutritionistDao();
            return nutritionistDAO.loadNutritionistByCredentials(userCredentials);
        }else{
            PatientDao patientDAO = new PatientDao();
            return patientDAO.loadPatientByCredentials(userCredentials);
        }
    }
}
