package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class IlnessesQueries {


    public static boolean saveIntoIlnesses(Statement statement, Ilnesses ilnesses) throws SQLException {
        String query = String.format("INSERT INTO malattia (Nome, Descrizione) values('%s','%s')",ilnesses.getName(),ilnesses.getDescription());
        return statement.execute(query);
    }
}
