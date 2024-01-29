package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.*;

import java.sql.*;

public class IlnessesQueries {


    public static ResultSet displayIlnesses(Statement statement) throws SQLException {
        String query = String.format("SELECT Nome FROM malattia ");
        return statement.executeQuery(query);
    }


}
