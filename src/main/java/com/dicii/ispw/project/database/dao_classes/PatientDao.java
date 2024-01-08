package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.NutritionistQueries;
import com.dicii.ispw.project.database.query.PatientQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.models.Patient;
import com.dicii.ispw.project.models.UserCredentials;

import java.sql.*;

public class PatientDao {
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";
    private static final String NAME = "Nome";
    private static final String SURNAME = "Cognome";
    private static final String BIRTH = "DataDiNascita";
    private static final String IBAN = "Iban";
    private static final String IVA = "Iva";
    private static final String COST = "CostoMensile";
    private static final String DESCRIPTION = "Descrizione";

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

    public UserBean loadPatientByCredentials(UserCredentials patient) throws NotExistentUserException {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        UserBean resultUser = new UserBean();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = PatientQueries.selectPatientByCredentials(statement,patient);
            if(resultSet.next()){
                resultUser.setEmail(resultSet.getString(EMAIL));
                resultUser.setPassword(resultSet.getString(PASSWORD));
                resultUser.setType(false);
            }else{
                throw new NotExistentUserException("This user doesn't exist");
            }

        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return resultUser;
    }





}
