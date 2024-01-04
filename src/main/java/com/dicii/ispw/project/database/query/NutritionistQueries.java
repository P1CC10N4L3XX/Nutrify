package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.User;

import java.sql.*;

public class NutritionistQueries extends Queries{
        public static boolean insertIntoNutritionist(Statement statement,User nutritionist) throws SQLException {
            String query = String.format("INSERT INTO nutrizionista (Email,Password) values('%s','%s')",nutritionist.getEmail(),nutritionist.getPassword());
            return statement.execute(query);
        }

}





