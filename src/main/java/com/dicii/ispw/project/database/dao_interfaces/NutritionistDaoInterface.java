package com.dicii.ispw.project.database.dao_interfaces;

import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.UserCredentials;

import java.util.List;

public interface NutritionistDaoInterface {
    void saveNutritionist(UserCredentials nutritionist) throws DuplicatedUserException;
    void saveNutritionistAll(Nutritionist nutritionist);
    UserCredentials loadNutritionistByCredentials(UserCredentials nutritionist) throws NotExistentUserException;
    List<Nutritionist> getNutritionistList(int from, int to);
}
