package com.dicii.ispw.project.patterns.factory;

import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Patient;
import com.dicii.ispw.project.models.User;

public class UserFactory {
    public User createPatient(String email, String password){
        return new Patient(email,password);
    }
    public User createNutritionist(String email,String password){
        return new Nutritionist(email,password);
    }
}
