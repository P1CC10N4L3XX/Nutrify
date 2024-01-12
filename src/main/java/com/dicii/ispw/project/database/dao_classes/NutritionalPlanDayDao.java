package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;


import com.dicii.ispw.project.database.query.NutritionalPlanDayQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.*;

import java.sql.*;

public class NutritionalPlanDayDao {




    public void SaveNutritionalPlanDay(NutritionalPlanDay nutritionalPlanDay, String emailPatient, Recipe recipe,String emailNutritionist) throws DuplicatedUserException {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            NutritionalPlanDayQueries.insertNutritionalPlanDay(statement,  nutritionalPlanDay ,emailPatient,  emailNutritionist,  recipe );
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedUserException(e.getMessage());
        }catch(SQLException e){
            System.out.println("SQL Error");
        }
    }


}
