package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.*;
import com.dicii.ispw.project.database.dao_classes.IlnessesDao;
import com.dicii.ispw.project.database.dao_classes.NutritionistDao;
import com.dicii.ispw.project.database.dao_classes.PatientDao;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.*;

import java.util.ArrayList;
import java.util.List;

public class RegisterApplicationController {
    public void registerUser(UserBean userBean) throws DuplicatedUserException {
        UserCredentials userCredentials = new UserCredentials(userBean.getEmail(), userBean.getPassword());
        boolean type=userBean.getType();
        if(type) {
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

    public List<IlnessesBean> displayIlnesses() throws DuplicatedUserException {

        List<Ilnesses> ilnesses= IlnessesDao.displayIlnesses();
        List<IlnessesBean> ilnessesBean;
        ilnessesBean=convertList(ilnesses);
        return ilnessesBean;
    }

    public List<IlnessesBean> convertList(List<Ilnesses> ilnesses) {

        List<IlnessesBean> ilnessesBeanList = new ArrayList<>();

        for (Ilnesses ilneses : ilnesses) {
            IlnessesBean ilnessesBean = new IlnessesBean();

            ilnessesBean.setName(ilneses.getName());

            ilnessesBeanList.add(ilnessesBean);
        }

        return ilnessesBeanList;
    }

    public Ilnesses convertIlnessesBeanToModel(IlnessesBean ilnessesBean) {
        Ilnesses ilnesses = new Ilnesses(ilnessesBean.getName());
        ilnesses.setName(ilnessesBean.getName());
        return ilnesses;
    }


    public void completeRegistrationPatient(PatientBean patientBean){
        Patient patient = new Patient (patientBean.getEmail(),patientBean.getName(), patientBean.getSurname(),patientBean.getDateOfBirth(),patientBean.getDescription(),patientBean.getWeight(),patientBean.getHeight(),convertIlnessesBeanToModel(patientBean.getIlnessesBean()));
        PatientDao patientDAO = new PatientDao();
        patientDAO.savePatientAll(patient);
    }
}

