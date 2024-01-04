package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.NutritionalPlanDay;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Patient;
import com.dicii.ispw.project.models.Recipe;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NutritionistPlanDayQueries {

    public static final String INSERT_NUTRITIONALPLANDAY_QUERY_1 = "INSERT INTO mydb.User (Nutrizionista,Paziente, Ricetta, Quantità, CategoriaPasto, Colazione ,DataConsumazione, Pranzo, Spuntino, Cena) " +
            "VALUES (?, ?, ?, ?, ?, )";
    public static final String INSERT_NUTRITIONALPLANDAY_QUERY_2 = "INSERT INTO mydb.partecipazione (User) VALUES (?)";


    //da rifinire

    public static void insertNutritionalPlanDay(PreparedStatement preparedStatement, PreparedStatement preparedStatement1,NutritionalPlanDay nutritionalPlanDayDay ,Nutritionist nutritionist, Patient patient, Recipe recipe) throws SQLException {
        preparedStatement.setString(1, nutritionist.getEmail());
        preparedStatement.setString(2, patient.getEmail());
        preparedStatement.setString(3, recipe.getName());
        preparedStatement.setString(4, recipe.getName());
        preparedStatement.setDate(5, Date.valueOf(nutritionist.getDateOfBirth()));
        preparedStatement.setString(6, nutritionist.getIban());
        preparedStatement.setString(7, String.valueOf(nutritionist.getIva()));
        preparedStatement.setString(8, nutritionist.getCosto());
        preparedStatement.executeUpdate();
        preparedStatement1.setString(9, nutritionist.getDescription());
        preparedStatement1.executeUpdate();
    }

}
