package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NutritionistQueries extends Queries{


    public static final String INSERT_TRAINER_QUERY_1 = "INSERT INTO mydb.User (Email,Password, Nome, Cognome, Data di nascita, Iban, P. Iva, Costo mensile, Descrizione) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_TRAINER_QUERY_2 = "INSERT INTO mydb.nutrizionista (User) VALUES (?)";
    public static void insertNutritionist(PreparedStatement preparedStatement, PreparedStatement preparedStatement1, Nutritionist nutritionist) throws SQLException {
        preparedStatement.setString(1, nutritionist.getEmail());
        preparedStatement.setString(2, nutritionist.getPassword());
        preparedStatement.setString(3, nutritionist.getName());
        preparedStatement.setString(4, nutritionist.getSurname());
        preparedStatement.setDate(5, Date.valueOf(nutritionist.getDateOfBirth()));
        preparedStatement.setString(6, nutritionist.getIban());
        preparedStatement.setString(7, String.valueOf(nutritionist.getIva()));
        preparedStatement.setString(8, nutritionist.getCosto());
        preparedStatement.executeUpdate();
        preparedStatement1.setString(9, nutritionist.getDescription());
        preparedStatement1.executeUpdate();
    }




}
