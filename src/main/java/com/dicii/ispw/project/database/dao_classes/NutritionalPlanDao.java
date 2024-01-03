package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.NutritionistPlanQueries;
import com.dicii.ispw.project.models.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NutritionalPlanDao {

    public void SaveNutritionalPlan(NutritionalPlanBase nutritionalPlan, Patient patient, Nutritionist nutritionist){
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                NutritionistPlanQueries.INSERT_NUTRITIONALPLAN_QUERY_1);
            PreparedStatement preparedStatement1 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                    NutritionistPlanQueries.INSERT_NUTRITIONALPLAN_QUERY_2)) {
            NutritionistPlanQueries.insertNutritionalPlan(preparedStatement, preparedStatement1, nutritionist  , patient, nutritionalPlan);
        } catch (SQLException e) {
            System.out.println("error");
        }


    }
}
