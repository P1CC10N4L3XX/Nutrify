package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.database.dao_classes.NutritionistDao;
import com.dicii.ispw.project.database.dao_classes.PatientDao;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Patient;
import com.dicii.ispw.project.models.UserCredentials;

public class RegisterApplicationController {
    public void registerUser(UserBean userBean) throws DuplicatedUserException {
        UserCredentials userCredentials = new UserCredentials(userBean.getEmail(), userBean.getPassword());
        if(userBean.getType()) {
            NutritionistDao nutritionistDAO = new NutritionistDao();
            nutritionistDAO.saveNutritionist(userCredentials);
        }else{
            PatientDao patientDAO = new PatientDao();
            patientDAO.savePatient(userCredentials);
        }
    }
    public void completeRegistrationNutritionist(NutritionistBean nutritionistBean) {
        Nutritionist nutritionist = new Nutritionist (nutritionistBean.getEmail(),nutritionistBean.getName(),nutritionistBean.getSurname(),nutritionistBean.getDateOfBirth(),nutritionistBean.getDescription(),nutritionistBean.getIVA(),nutritionistBean.getIBAN(),nutritionistBean.getCost());
        NutritionistDao nutritionistDAO = new NutritionistDao();
        nutritionistDAO.saveNutritionistAll(nutritionist);
    }
    public void completeRegistrationPatient(PatientBean patientBean){
        Patient patient = new Patient (patientBean.getEmail(),patientBean.getName(), patientBean.getSurname(),patientBean.getDateOfBirth(),patientBean.getDescription(),patientBean.getWeight(),patientBean.getHeight());
        PatientDao patientDAO = new PatientDao();
        patientDAO.savePatientAll(patient);
    }
}

