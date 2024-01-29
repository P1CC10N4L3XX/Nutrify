package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.*;


import java.sql.SQLException;
import java.sql.Statement;

public class NutritionalPlanQueries {


    //aggiungei stringhe di paziente e nutrizionista
    public static boolean insertNutritionalPlan(Statement statement, NutritionalPlanBase nutritionalPlanBase, String emailNutritionist, String emailPatient) throws SQLException {
        String query = String.format("INSERT INTO pianonutrizionale (Nutrizionista,Paziente,Datacreazione)  values('%s','%s','%s')",emailNutritionist ,emailPatient , nutritionalPlanBase.getDate());
        return statement.execute(query);
    }


}
