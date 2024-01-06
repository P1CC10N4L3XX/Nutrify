package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.*;

import java.sql.SQLException;
import java.sql.Statement;

public class NutritionalPlanDayQueries {


    public static boolean insertNutritionalPlanDay(Statement statement, NutritionalPlanDay nutritionalPlanDay ,Nutritionist nutritionist, Patient patient, Recipe recipe) throws SQLException {
        String query = String.format("INSERT INTO mydb.partecipazione (Nutrizionista,Paziente, RicettaColazione, RicettaPranzo, RicettaCena, QuantitaColazione" +
                        " ,QuantitaPranzo,QuantitaCena,DataConsumazione) values('%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                nutritionist.getEmail(),patient.getEmail(),nutritionalPlanDay.getColazione(),nutritionalPlanDay.getPranzo(),
                nutritionalPlanDay.getCena(),nutritionalPlanDay.getQuantitaColazione(),nutritionalPlanDay.getQuantitaPranzo(),
                nutritionalPlanDay.getQuantitaCena(),nutritionalPlanDay.getDay());
        return statement.execute(query);
    }

}
