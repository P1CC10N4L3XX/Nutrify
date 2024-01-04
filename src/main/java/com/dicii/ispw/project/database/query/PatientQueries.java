package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.User;


import java.sql.SQLException;
import java.sql.Statement;

public class PatientQueries extends Queries{
    public static boolean insertIntoPatient(Statement statement, User patient) throws SQLException {
        String query = String.format("INSERT INTO paziente (Email,Password) values('%s','%s')",patient.getEmail(),patient.getPassword());
        return statement.execute(query);
    }


}
