package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.*;

import java.sql.SQLException;
import java.sql.Statement;

public class NutritionalPlanQueries {


    public static boolean insertNutritionalPlan(Statement statement, Nutritionist nutritionist, Patient patient, NutritionalPlanBase nutritionalPlanBase) throws SQLException {
        String query = String.format("INSERT INTO mydb.User (Nutrizionista,Paziente,Datacreazione, Descrizione)  values('%s','%s','%s''%s')",nutritionist.getEmail(), patient.getEmail(),
                nutritionalPlanBase.getDate(), nutritionalPlanBase.getDescription());
        return statement.execute(query);
    }


}
