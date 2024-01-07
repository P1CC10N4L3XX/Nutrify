package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.PatientQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.Patient;
import com.dicii.ispw.project.models.UserCredentials;

import java.sql.*;

public class PatientDao {

    public void savePatient(UserCredentials patient) throws DuplicatedUserException {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            PatientQueries.insertIntoPatient(statement, patient);
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedUserException(e.getMessage());
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void savePatientAll(Patient patient){
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            PatientQueries.updatePatientAll(statement, patient);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }





}
