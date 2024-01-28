package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.beans.NutritionistBean;
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
import java.util.ArrayList;
import java.util.List;

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
    public List<Nutritionist> getNutritionistList(int from, int to){
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        List<Nutritionist> nutritionistResultList = new ArrayList<>();
        String email,name,surname,dateOfBirth,description,iva,iban,costo;
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = NutritionistQueries.selectListOfNutritionist(statement,from,to);
            while(resultSet.next()){
                email=resultSet.getString(EMAIL);
                name=resultSet.getString(NAME);
                surname=resultSet.getString(SURNAME);
                dateOfBirth=resultSet.getString(BIRTH);
                description=resultSet.getString(DESCRIPTION);
                iva=resultSet.getString(IVA);
                iban=resultSet.getString(IBAN);
                costo=resultSet.getString(COST);
                nutritionistResultList.add(new Nutritionist(email,name,surname,dateOfBirth,description,iva,iban,costo));
            }
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
        return nutritionistResultList;
    }

}
