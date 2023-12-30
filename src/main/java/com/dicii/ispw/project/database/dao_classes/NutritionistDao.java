package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.NutritionistQueries;
import com.dicii.ispw.project.models.Nutritionist;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NutritionistDao {

    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String USERNAME = "Username";
    private static final String BIRTH = "Birth";
    private static final String FC = "FC";
    private static final String GENDER = "Gender";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";

    public void saveNutritionist(Nutritionist nutritionist) throws SQLException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                NutritionistQueries.INSERT_TRAINER_QUERY_1);
            PreparedStatement preparedStatement1 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                    NutritionistQueries.INSERT_TRAINER_QUERY_2)) {
            NutritionistQueries.insertNutritionist(preparedStatement, preparedStatement1, nutritionist);
        } catch (SQLException e) {
            System.out.println("error");
        }
    }



}
