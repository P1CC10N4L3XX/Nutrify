package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.NutritionistQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.User;
import com.dicii.ispw.project.models.UserCredentials;

import java.io.PrintStream;
import java.sql.*;

public class NutritionistDao {
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";
    private static final String NAME = "Nome";
    private static final String SURNAME = "Cognome";
    private static final String BIRTH = "DataDiNascita";
    private static final String IBAN = "Iban";
    private static final String IVA = "Iva";
    private static final String COST = "CostoMensile";
    private static final String DESCRIPTION = "Descrizione";

    public void saveNutritionist(UserCredentials nutritionist) throws DuplicatedUserException {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            NutritionistQueries.insertIntoNutritionist(statement, nutritionist);
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedUserException(e.getMessage());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void saveNutritionistAll(Nutritionist nutritionist) {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            NutritionistQueries.updateNutritionistAll(statement,nutritionist);
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    public UserBean loadNutritionistByCredentials(UserCredentials nutritionist) throws NotExistentUserException {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        UserBean resultUser = new UserBean();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = NutritionistQueries.selectNutritionistByCredentials(statement,nutritionist);
            if(resultSet.next()){
                resultUser.setEmail(resultSet.getString(EMAIL));
                resultUser.setPassword(resultSet.getString(PASSWORD));
                resultUser.setType(true);
            }else{
                throw new NotExistentUserException("This user doesn't exist");
            }

        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return resultUser;
    }

}
