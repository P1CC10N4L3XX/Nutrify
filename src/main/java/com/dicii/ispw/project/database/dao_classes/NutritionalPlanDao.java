package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.NutritionalPlanQueries;

import com.dicii.ispw.project.database.query.RecipeQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.*;

import java.sql.*;

public class NutritionalPlanDao {



    public void SaveNutritionalPlan(NutritionalPlanBase nutritionalPlan, String emailNutritionist, String emailPatient) throws DuplicatedUserException {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            NutritionalPlanQueries.insertNutritionalPlan(statement, nutritionalPlan, emailNutritionist,emailPatient );
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedUserException(e.getMessage());
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.err.println("Errore SQL: " + e.getMessage());
            System.err.println("Codice di stato SQL: " + e.getSQLState());
            System.err.println("Codice di errore SQL: " + e.getErrorCode());
            e.printStackTrace();
        }
    }



}
