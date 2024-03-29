package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.NutritionalPlanDayQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanFounded;
import com.dicii.ispw.project.models.*;

import java.sql.*;




public class NutritionalPlanDayDao {

    private static final String RICETTA_COLAZIONE="RicettaColazione";
    private static final String RICETTA_PRANZO="RicettaPranzo";
    private static final String RICETTA_CENA="RicettaCena";
    private static final String QUANTITA_COLAZIONE="QuantitaColazione";
    private static final String QUANTITA_PRANZO="QuantitaPranzo";
    private static final String QUANTITA_CENA="QuantitaCena";
    private static final String DATA_CONSUMAZIONE="DataConsumazione";



    

    public boolean saveNutritionalPlanDay(NutritionalPlanDay nutritionalPlanDay, String emailNutritionist, String emailPatient ){

        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try (Statement statement = connection.createStatement()) {

            return NutritionalPlanDayQueries.insertNutritionalPlanDay(statement, nutritionalPlanDay, emailNutritionist, emailPatient);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return false;

    }


    public void checkNutritionalPlanDay( String emailNutritionist,String emailPatient , String data) throws DuplicatedUserException, NutritionalPlanFounded {

        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try (Statement statement = connection.createStatement() ;
             ResultSet resultSet = NutritionalPlanDayQueries.displayNutritionalPlanDay(statement, emailPatient,  emailNutritionist, data ); ) {

            if (resultSet.next()) {

                throw new NutritionalPlanFounded("la data selezionata ha gia un piano nutrizionale creato ");
            }

        }catch(SQLException e){
            throw new DuplicatedUserException(e.getMessage());
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
        catch (SQLException e) {
            throw new DuplicatedUserException(e.getMessage());
        }


       return nutritionalPlanDay;
    }

    public static Recipe convertStringToRecipe(String ricetta){
        return new Recipe(ricetta);
    }

    public static NutritionalPlanDay createNutritionalPlanDay(ResultSet resultSet) throws SQLException {


        String ricettaColazioneString = resultSet.getString(RICETTA_COLAZIONE);
        Recipe ricettaColazione = convertStringToRecipe(ricettaColazioneString);

        String ricettaPranzoString = resultSet.getString(RICETTA_PRANZO);
        Recipe ricettaPranzo = convertStringToRecipe(ricettaPranzoString);

        String ricettaCenaString = resultSet.getString(RICETTA_CENA);
        Recipe ricettaCena = convertStringToRecipe(ricettaCenaString);

        String quantitaColazione = resultSet.getString(QUANTITA_COLAZIONE);

        String quantitaPranzo = resultSet.getString(QUANTITA_PRANZO);

        String quantitaCena = resultSet.getString(QUANTITA_CENA);

        String dataConsumazione = resultSet.getString(DATA_CONSUMAZIONE);


        return new NutritionalPlanDay(dataConsumazione,ricettaColazione,ricettaPranzo,ricettaCena,quantitaColazione,quantitaPranzo,quantitaCena);

    }


}
