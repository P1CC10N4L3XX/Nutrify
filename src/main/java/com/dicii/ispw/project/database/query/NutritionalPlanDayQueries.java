package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NutritionalPlanDayQueries {


    public static boolean insertNutritionalPlanDay(Statement statement, NutritionalPlanDay nutritionalPlanDay ,String emailNutritionist, String emailPatient) throws SQLException {
        String query = String.format("INSERT INTO partecipazione (Nutrizionista,Paziente, RicettaColazione, RicettaPranzo, RicettaCena, QuantitaColazione" +
                        " ,QuantitaPranzo,QuantitaCena,DataConsumazione) values('%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                emailNutritionist,emailPatient,nutritionalPlanDay.getColazione().getName(),nutritionalPlanDay.getPranzo().getName(),
                nutritionalPlanDay.getCena().getName(),nutritionalPlanDay.getQuantitaColazione(),nutritionalPlanDay.getQuantitaPranzo(),
                nutritionalPlanDay.getQuantitaCena(),nutritionalPlanDay.getDay());
        return statement.execute(query);
    }

    public static ResultSet displayNutritionalPlanDay(Statement statement, String emailPaziente,String emailNutrizionista,String data) throws SQLException {
        String query = String.format("SELECT * FROM partecipazione Where Nutrizionista= '%s' && Paziente= '%s' AND DataConsumazione='%s' ",emailNutrizionista, emailPaziente, data);
        return statement.executeQuery(query);
    }



}
