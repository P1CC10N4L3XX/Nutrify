package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;


import com.dicii.ispw.project.database.query.NutritionalPlanDayQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanFounded;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.models.*;

import java.sql.*;
import java.util.ArrayList;

public class NutritionalPlanDayDao {





    public void SaveNutritionalPlanDay(NutritionalPlanDay nutritionalPlanDay, String emailNutritionist,String emailPatient ,Recipe recipe) throws DuplicatedUserException {



        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            NutritionalPlanDayQueries.insertNutritionalPlanDay(statement,  nutritionalPlanDay ,emailPatient,  emailNutritionist,  recipe );
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedUserException(e.getMessage());
        }catch(SQLException e){
            System.out.println("SQL Error");
        }
    }



    public void CheckNutritionalPlanDay( String emailNutritionist,String emailPatient , String data) throws DuplicatedUserException, NutritionalPlanFounded {

        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try (Statement statement = connection.createStatement() ;
             ResultSet resultSet = NutritionalPlanDayQueries.displayNutritionalPlanDay(statement, emailPatient,  emailNutritionist, data ); ) {

            if (resultSet.next()) {


                throw new NutritionalPlanFounded("la data selezionata ha gia un piano nutrizionale creato ");

            }

        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedUserException(e.getMessage());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public NutritionalPlanDay displayNutritionalPlanDay( String emailPatient,String emailNutritionist, String data) throws DuplicatedUserException {
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
