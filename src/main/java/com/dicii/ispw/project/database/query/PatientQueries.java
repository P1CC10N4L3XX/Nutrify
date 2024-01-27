package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.Patient;
import com.dicii.ispw.project.models.UserCredentials;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientQueries extends Queries{
    public static boolean insertIntoPatient(Statement statement, UserCredentials patient) throws SQLException {
        String sql = String.format("INSERT INTO paziente (Email,Password) values('%s','%s')",patient.getEmail(),patient.getPassword());
        return statement.execute(sql);
    }

    public static void updatePatientAll(Statement statement, Patient patient) throws SQLException{
        String sql = String.format("UPDATE paziente SET Nome = '%s' , Cognome = '%s', DataDiNascita = '%s' , Peso = '%s' , Altezza = '%s', Descrizione = '%s' WHERE Email = '%s' ",patient.getName(),patient.getSurname(),patient.getDateOfBirth(),patient.getWeight(),patient.getHeight(),patient.getDescription(),patient.getEmail());
        statement.executeUpdate(sql);
    }

    public static ResultSet selectPatientByCredentials(Statement statement, UserCredentials patient) throws SQLException{
        String query = String.format("SELECT * FROM paziente WHERE Email='%s' AND Password='%s'",patient.getEmail(),patient.getPassword());
        return statement.executeQuery(query);
    }

    public static ResultSet selectInfoPatient(Statement statement, String email) throws SQLException{
        String query = String.format("SELECT * FROM paziente WHERE Email='%s'",email);
        return statement.executeQuery(query);
    }




}
