package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.UserCredentialsBean;
import com.dicii.ispw.project.database.TypesOfPersistenceLayer;
import com.dicii.ispw.project.database.csv_classes.NutritionistCSV;
import com.dicii.ispw.project.database.csv_classes.PatientCSV;
import com.dicii.ispw.project.database.dao_classes.NutritionistDao;
import com.dicii.ispw.project.database.dao_classes.PatientDao;
import com.dicii.ispw.project.database.dao_interfaces.NutritionistDaoInterface;
import com.dicii.ispw.project.database.dao_interfaces.PatientDaoInterface;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.models.UserCredentials;

import static com.dicii.ispw.project.database.TypesOfPersistenceLayer.CSV;
import static com.dicii.ispw.project.database.TypesOfPersistenceLayer.JDBC;

public class LoginApplicationController {

    private PatientDaoInterface patientDAO;
    private NutritionistDaoInterface nutritionistDAO;

    public LoginApplicationController(){
        patientDAO = new PatientDao();
        nutritionistDAO = new NutritionistDao();
    }

    public LoginApplicationController(TypesOfPersistenceLayer p){
        if(p == JDBC) {
            patientDAO = new PatientDao();
            nutritionistDAO = new NutritionistDao();
        }else if(p == CSV){
            patientDAO = new PatientCSV();
            nutritionistDAO = new NutritionistCSV();
        }
    }

    public UserCredentialsBean loginUser(UserCredentialsBean userBeanCredentials) throws NotExistentUserException {
        UserCredentials userCredentials = new UserCredentials(userBeanCredentials.getEmail(), userBeanCredentials.getPassword());
        UserCredentials userCredentialsResult;
        boolean type=userBeanCredentials.getType();
        if(type){
            userCredentialsResult = nutritionistDAO.loadNutritionistByCredentials(userCredentials);
        }else{
            userCredentialsResult = patientDAO.loadPatientByCredentials(userCredentials);
        }
        return new UserCredentialsBean(userCredentialsResult.getEmail(),userCredentialsResult.getPassword(),type);
    }
}
