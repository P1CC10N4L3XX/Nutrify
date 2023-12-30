package com.dicii.ispw.project.database.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQueries extends Queries{

    private UserQueries() {}


    public static final String LOAD_USER_1_QUERY = SELECT_ALL +
            " FROM mydb.User " +
            " WHERE Email = ? AND Password = ?";
    public static ResultSet loadUser(PreparedStatement preparedStatement, String email, String password) throws SQLException {
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        return preparedStatement.executeQuery();
    }



    public static final String DELETE_USER_QUERY = "DELETE FROM mydb.User " +
            "WHERE Email = ?";
    public static void deleteUser(PreparedStatement preparedStatement, String email) throws SQLException {
        preparedStatement.setString(1, email);
        preparedStatement.executeUpdate();
    }
}
