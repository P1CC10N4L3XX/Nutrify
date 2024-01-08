package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.*;
import com.dicii.ispw.project.patterns.singleton.Session;

import java.sql.SQLException;
import java.sql.Statement;

public class NutritionalPlanQueries {


    //aggiungei stringhe di paziente e nutrizionista
    public static boolean insertNutritionalPlan(Statement statement, NutritionalPlanBase nutritionalPlanBase, String emailNutritionist, String emailPatient) throws SQLException {
        String query = String.format("INSERT INTO mydb.User (Nutrizionista,Paziente,Datacreazione, Descrizione)  values('%s','%s','%s''%s')",emailNutritionist ,emailPatient ,
                nutritionalPlanBase.getDate(), nutritionalPlanBase.getDescription());
        return statement.execute(query);
    }


}
