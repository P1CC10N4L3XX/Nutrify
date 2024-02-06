package com.dicii.ispw.project.database.dao_interfaces;

import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Patient;
import com.dicii.ispw.project.models.User;
import com.dicii.ispw.project.models.UserCredentials;

public interface PatientDaoInterface {
    void savePatient(UserCredentials patient) throws DuplicatedUserException;
    void savePatientAll(Patient patient);
    UserCredentials loadPatientByCredentials(UserCredentials userCredentials) throws NotExistentUserException;
    void setSubscriptionRequestPatient(Patient patient, Nutritionist nutritionist);
    User loadNutritionistSubscribed(String patientEmail) throws NotExistentUserException;
    Patient selectInfoPatient(String emailPatient);
}
