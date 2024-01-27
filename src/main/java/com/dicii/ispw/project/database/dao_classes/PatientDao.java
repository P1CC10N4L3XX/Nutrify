package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.NutritionalPlanDayQueries;
import com.dicii.ispw.project.database.query.NutritionistQueries;
import com.dicii.ispw.project.database.query.PatientQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.models.Ilnesses;
import com.dicii.ispw.project.models.NutritionalPlanDay;
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

    private static final String HEIGHT = "Altezza";

    private static final String WHEIGHT = "Peso";

    private static final String ILLNESSES = "Malattia";

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


    public Patient selectInfoPatient(String emailPatient) throws DuplicatedUserException {
        Patient patient = new Patient();
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = PatientQueries.selectInfoPatient(statement, emailPatient ); ) {

            if (resultSet.next()) {

                patient.setName(resultSet.getString(NAME));
                patient.setSurname(resultSet.getString(SURNAME));
                patient.setDateOfBirth(resultSet.getString(BIRTH));
                patient.setWeight(resultSet.getString(HEIGHT));
                patient.setHeight(resultSet.getString(WHEIGHT));

                String ilnessesName=resultSet.getString(ILLNESSES);
                Ilnesses ilnesses = new Ilnesses(ilnessesName);

                patient.setIlnesses(ilnesses);

            }

        }
        catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedUserException(e.getMessage());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return patient;
    }





}
