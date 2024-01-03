package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.Ilnesses;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Patient;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientQueries {

    public static final String INSERT_PATIENT_QUERY_1 = "INSERT INTO mydb.User (Email,Password, Nome, Cognome, Data di nascita, Peso, Altezza, Descrizione, Nutrizionista, Malattia) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?. ?, ?,?)";
    public static final String INSERT_PATIENT_QUERY_2 = "INSERT INTO mydb.paziente (User) VALUES (?)";
    public static void insertPatient(PreparedStatement preparedStatement, PreparedStatement preparedStatement1, Patient patient, Ilnesses ilnesses) throws SQLException {
        preparedStatement.setString(1, patient.getEmail());
        preparedStatement.setString(2, patient.getPassword());
        preparedStatement.setString(3, patient.getName());
        preparedStatement.setString(4, patient.getSurname());
        preparedStatement.setDate(5, Date.valueOf(patient.getDateOfBirth()));
        preparedStatement.setString(6, String.valueOf(patient.getWeight()));
        preparedStatement.setString(7, String.valueOf(patient.getHeight()));
        preparedStatement.setString(8, patient.getDescription());

        preparedStatement.setString(8, patient.getDescription());

        preparedStatement.setString(10, ilnesses.getName());


        preparedStatement1.executeUpdate();
    }


}
