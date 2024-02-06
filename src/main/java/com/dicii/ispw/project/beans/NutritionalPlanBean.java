package com.dicii.ispw.project.beans;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Patient;

import java.util.ArrayList;
import java.util.List;

public class NutritionalPlanBean {

    private String data;

    private PatientBean patientBean;

    private NutritionistBean nutritionistBean;

    List<NutritionalPlanDayBean> nutritionalPlanDayBeanList;

    public NutritionalPlanBean(String data, PatientBean patientBean,NutritionistBean nutritionistBean){

        this.data=data;
        this.patientBean=patientBean;
        this.nutritionistBean=nutritionistBean;

    }

    public PatientBean getPatient() {
        return this.patientBean;
    }

    public NutritionistBean getNutritionist() {
        return this.nutritionistBean;
    }


    public void setNutritionist(NutritionistBean nutritionistBean) {
        this.nutritionistBean = nutritionistBean;
    }
    public void setPatient(PatientBean patientBean) {
        this.patientBean = patientBean;
    }


    public NutritionalPlanBean(){
        this.nutritionalPlanDayBeanList = new ArrayList<>();
    }


    public String getDate() {
        return this.data;
    }





}
