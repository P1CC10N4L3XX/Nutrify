package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.dao_interfaces.PatientDaoInterface;
import com.dicii.ispw.project.database.query.PatientQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements PatientDaoInterface {
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";
    private static final String NAME = "Nome";
    private static final String SURNAME = "Cognome";
    private static final String BIRTH = "DataDiNascita";

    private static final String HEIGHT = "Altezza";

    private static final String WHEIGHT = "Peso";

    private static final String ILLNESSES = "Malattia";

    private static final String NUTRITIONIST = "Nutrizionista";



    public void savePatient(UserCredentials patient) throws DuplicatedUserException {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            PatientQueries.insertIntoPatient(statement, patient);
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedUserException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Patient> displayPatient(String nutritionistEmail){
        ArrayList<Patient> patients = new ArrayList<>() ;
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = PatientQueries.displayPatients(statement, nutritionistEmail)) {

            while (resultSet.next()) {

                Patient patient = createPatient(resultSet);
                patients.add(patient);


            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }


        return patients;

    }

    public static Patient createPatient(ResultSet resultSet) throws SQLException {

        String email = resultSet.getString(EMAIL);

        return new Patient(email);

    }

    public void savePatientAll(Patient patient){
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            PatientQueries.updatePatientAll(statement, patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserCredentials loadPatientByCredentials(UserCredentials patient) throws NotExistentUserException {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        UserCredentials resultUser = new UserCredentials();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = PatientQueries.selectPatientByCredentials(statement,patient);
            if(resultSet.next()){
                resultUser.setEmail(resultSet.getString(EMAIL));
                resultUser.setPassword(resultSet.getString(PASSWORD));
            }else{
                throw new NotExistentUserException("This user doesn't exist");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultUser;
    }

    public void setSubscriptionRequestPatient(Patient patient, Nutritionist nutritionist){
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            PatientQueries.setSubscriptionForPatient(statement,patient,nutritionist);
        }catch(SQLException e ){
            throw new RuntimeException(e);
        }
    }


    public User loadNutritionistSubscribed(String patientEmail) throws NotExistentUserException {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        User resultUser = new User();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = PatientQueries.selectSubscribeNutritionist(statement,patientEmail);
            if(resultSet.next()){
                resultUser.setEmail(resultSet.getString(NUTRITIONIST));

            }else{
                throw new NotExistentUserException("This user doesn't exist");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultUser;
    }


    public Patient selectInfoPatient(String emailPatient) {
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

                patient.setIllnesses(ilnesses);

            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return patient;
    }





}
