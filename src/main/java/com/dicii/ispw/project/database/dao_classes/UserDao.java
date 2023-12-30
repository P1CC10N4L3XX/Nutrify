package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.UserQueries;
import com.dicii.ispw.project.models.User;
import com.dicii.ispw.project.database.query.UserQueries;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {






    public void deleteUser(User user)  {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UserQueries.DELETE_USER_QUERY)) {
            UserQueries.deleteUser(preparedStatement, user.getEmail());
        } catch (SQLException e) {
           System.out.println("error query");
        }
    }



}
