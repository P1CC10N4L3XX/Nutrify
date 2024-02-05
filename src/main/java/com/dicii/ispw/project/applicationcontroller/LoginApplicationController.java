package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.database.dao_classes.NutritionistDao;
import com.dicii.ispw.project.database.dao_classes.PatientDao;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.models.UserCredentials;
import com.dicii.ispw.project.patterns.singleton.Session;

public class LoginApplicationController {
    public UserBean loginUser(UserBean userBeanCredentials) throws NotExistentUserException {
        UserCredentials userCredentials = new UserCredentials(userBeanCredentials.getEmail(), userBeanCredentials.getPassword());
        boolean type=userBeanCredentials.getType();
        if(type){
            NutritionistDao nutritionistDAO = new NutritionistDao();
            return nutritionistDAO.loadNutritionistByCredentials(userCredentials);
        }else{
            PatientDao patientDAO = new PatientDao();
            return patientDAO.loadPatientByCredentials(userCredentials);
        }
    }
}
