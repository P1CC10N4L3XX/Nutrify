package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.IlnessesQueries;
import com.dicii.ispw.project.database.query.PatientQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.*;
import com.dicii.ispw.project.database.query.IlnessesQueries;

import java.sql.*;

public class IlnessesDao {



    public void saveIlnesses(Ilnesses ilnesses) throws DuplicatedUserException {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            IlnessesQueries.saveIntoIlnesses(statement,ilnesses );
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedUserException(e.getMessage());
        }catch(SQLException e){
            System.out.println("SQL Error");
        }
    }
}
