package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.NutritionistPlanDayQueries;
import com.dicii.ispw.project.database.query.NutritionistQueries;
import com.dicii.ispw.project.models.NutritionalPlanDay;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Patient;
import com.dicii.ispw.project.models.Recipe;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NutritionalPlanDayDao {


    public void SaveNutritionalPlanDay(NutritionalPlanDay nutritionalPlanDay, Patient patient, Recipe recipe, NutritionalPlanDay nutritionalPlanDayDay ,Nutritionist nutritionist){
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                NutritionistPlanDayQueries.INSERT_NUTRITIONALPLANDAY_QUERY_1);
            PreparedStatement preparedStatement1 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                    NutritionistPlanDayQueries.INSERT_NUTRITIONALPLANDAY_QUERY_2)) {
            NutritionistPlanDayQueries.insertNutritionalPlanDay(preparedStatement, preparedStatement1,  nutritionalPlanDayDay ,  nutritionist, patient, recipe);
        } catch (SQLException e) {
            System.out.println("error");
        }

    }


}
