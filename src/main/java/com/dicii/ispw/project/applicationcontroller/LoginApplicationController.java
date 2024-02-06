package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.database.TypesOfPersistenceLayer;
import com.dicii.ispw.project.database.csv_classes.NutritionistCSV;
import com.dicii.ispw.project.database.csv_classes.PatientCSV;
import com.dicii.ispw.project.database.dao_classes.NutritionistDao;
import com.dicii.ispw.project.database.dao_classes.PatientDao;
import com.dicii.ispw.project.database.dao_interfaces.NutritionistDaoInterface;
import com.dicii.ispw.project.database.dao_interfaces.PatientDaoInterface;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.models.UserCredentials;

public class LoginApplicationController {

    private PatientDaoInterface patientDAO;
    private NutritionistDaoInterface nutritionistDAO;

    public LoginApplicationController(){
        patientDAO = new PatientDao();
        nutritionistDAO = new NutritionistDao();
    }

    public LoginApplicationController(TypesOfPersistenceLayer p){
        switch (p){
            case JDBC:
                patientDAO = new PatientDao();
                nutritionistDAO = new NutritionistDao();
                break;
            case CSV:
                patientDAO = new PatientCSV();
                nutritionistDAO = new NutritionistCSV();
                break;
        }
    }

    public UserBean loginUser(UserBean userBeanCredentials) throws NotExistentUserException {
        UserCredentials userCredentials = new UserCredentials(userBeanCredentials.getEmail(), userBeanCredentials.getPassword());
        UserCredentials userCredentialsResult;
        boolean type=userBeanCredentials.getType();
        if(type){
            userCredentialsResult = nutritionistDAO.loadNutritionistByCredentials(userCredentials);
        }else{
            userCredentialsResult = patientDAO.loadPatientByCredentials(userCredentials);
        }
        return new UserBean(userCredentialsResult.getEmail(),userCredentialsResult.getPassword(),type);
    }
}
