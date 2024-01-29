package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.NutritionalPlanQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.*;

import java.sql.*;

public class NutritionalPlanDao {



    public void SaveNutritionalPlan(NutritionalPlanBase nutritionalPlan, String emailNutritionist, String emailPatient) throws DuplicatedUserException {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            NutritionalPlanQueries.insertNutritionalPlan(statement, nutritionalPlan, emailNutritionist,emailPatient );
        }catch(SQLException e){
            throw new DuplicatedUserException(e.getMessage());
        }
    }



}
