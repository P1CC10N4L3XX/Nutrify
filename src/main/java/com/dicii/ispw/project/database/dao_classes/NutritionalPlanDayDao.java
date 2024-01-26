package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;


import com.dicii.ispw.project.database.query.NutritionalPlanDayQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.models.*;

import java.sql.*;
import java.util.ArrayList;

public class NutritionalPlanDayDao {




    public void SaveNutritionalPlanDay(NutritionalPlanDay nutritionalPlanDay, String emailPatient, Recipe recipe,String emailNutritionist) throws DuplicatedUserException {



        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            NutritionalPlanDayQueries.insertNutritionalPlanDay(statement,  nutritionalPlanDay ,emailPatient,  emailNutritionist,  recipe );
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedUserException(e.getMessage());
        }catch(SQLException e){
            System.out.println("SQL Error");
        }
    }

    public NutritionalPlanDay displayNutritionalPlanDay( String emailPatient,String emailNutritionist, String data) throws DuplicatedUserException,NutritionalPlanNotFoundException {
        NutritionalPlanDay nutritionalPlanDay=null;
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();

        try (Statement statement = connection.createStatement() ;
             ResultSet resultSet = NutritionalPlanDayQueries.displayNutritionalPlanDay(statement, emailPatient,  emailNutritionist, data ); ) {




            if (resultSet.next()) {

                nutritionalPlanDay = createNutritionalPlanDay(resultSet) ;

            }

        }
        catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedUserException(e.getMessage());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
       // System.out.println(nutritionalPlanDay.getColazione());
       return nutritionalPlanDay;
    }

    public static NutritionalPlanDay createNutritionalPlanDay(ResultSet resultSet) throws SQLException {



        String ricettaColazione = resultSet.getString("RicettaColazione");

        String ricettaPranzo = resultSet.getString("RicettaPranzo");

        String ricettaCena = resultSet.getString("RicettaCena");

        String quantitaColazione = resultSet.getString("QuantitaColazione");

        String quantitaPranzo = resultSet.getString("QuantitaPranzo");

        String quantitaCena = resultSet.getString("QuantitaCena");

        String dataConsumazione = resultSet.getString("DataConsumazione");



        return new NutritionalPlanDay(dataConsumazione,ricettaColazione,ricettaPranzo,ricettaCena,quantitaColazione,quantitaPranzo,quantitaCena);

    }


}
