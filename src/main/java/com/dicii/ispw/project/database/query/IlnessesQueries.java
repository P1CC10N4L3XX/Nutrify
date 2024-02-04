package com.dicii.ispw.project.database.query;

import java.sql.*;

public class IlnessesQueries {

    public IlnessesQueries(){}


    public static ResultSet displayIlnesses(Statement statement) throws SQLException {
        String query = "SELECT Nome FROM malattia ";
        return statement.executeQuery(query);
    }


}
