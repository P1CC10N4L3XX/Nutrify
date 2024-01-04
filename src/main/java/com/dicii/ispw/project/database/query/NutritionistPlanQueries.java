package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NutritionistPlanQueries {


    public static final String INSERT_NUTRITIONALPLAN_QUERY_1 = "INSERT INTO mydb.User (Nutrizionista,Paziente,Data creazione, Descrizione) " +
            "VALUES (?, ?, ?, ?, ?, )";
    public static final String INSERT_NUTRITIONALPLAN_QUERY_2 = "INSERT INTO mydb.partecipazione (User) VALUES (?)";


    //da rifinire

    public static void insertNutritionalPlan(PreparedStatement preparedStatement, PreparedStatement preparedStatement1, Nutritionist nutritionist, Patient patient, NutritionalPlanBase nutritionalPlanBase) throws SQLException {
        preparedStatement.setString(1, nutritionist.getEmail());
        preparedStatement.setString(2, patient.getEmail());
        preparedStatement.setDate(3, Date.valueOf(nutritionalPlanBase.getDate()));
        preparedStatement.setString(4, nutritionalPlanBase.getDescription());
        preparedStatement1.executeUpdate();
    }


}
