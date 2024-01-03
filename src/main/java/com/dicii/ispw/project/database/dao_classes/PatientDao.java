package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.models.Ilnesses;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Patient;
import com.dicii.ispw.project.database.query.PatientQueries;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientDao {

    public void savePatient(Patient patient, Ilnesses ilnesses) throws SQLException{
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                PatientQueries.INSERT_ATHLETE_QUERY_1);
            PreparedStatement preparedStatement1 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                    PatientQueries.INSERT_ATHLETE_QUERY_2)) {
            PatientQueries.insertPatient(preparedStatement, preparedStatement1, patient, ilnesses);
        } catch (SQLException e) {
          System.out.println("error");
        }
    }




}
