package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.NutritionalPlanQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanBaseAlreadyCreated;
import com.dicii.ispw.project.models.*;

import java.sql.*;

public class NutritionalPlanDao {



    public void saveNutritionalPlan(NutritionalPlanBase nutritionalPlan, Nutritionist nutritionist, Patient patient) throws NutritionalPlanBaseAlreadyCreated {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            NutritionalPlanQueries.insertNutritionalPlan(statement, nutritionalPlan, nutritionist.getEmail(),patient.getEmail() );
        }catch(SQLException e){
            throw new NutritionalPlanBaseAlreadyCreated(e.getMessage());
        }
    }



}
