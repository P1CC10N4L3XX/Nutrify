package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.database.DatabaseConnectionSingleton;
import com.dicii.ispw.project.database.query.IlnessesQueries;
import com.dicii.ispw.project.database.query.RecipeQueries;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.Ilnesses;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {


    public void saveRecipe(Recipe recipe) throws DuplicatedUserException {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            RecipeQueries.saveIntoRecipe(statement,recipe );
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedUserException(e.getMessage());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }



    //ritorna un vettore di ricette
    public static List<Recipe> displayRecipe() throws DuplicatedUserException {
        ArrayList<Recipe> recipes = new ArrayList<>() ;
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = RecipeQueries.displayRecipe(statement)) {

            while (resultSet.next()) {

                Recipe recipe = createRecipe(resultSet);

                recipes.add(recipe);

            }
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedUserException(e.getMessage());
        }catch(SQLException e){

            System.out.println(e.getMessage());
        }

        return recipes;

    }





   //crea un oggetto di tipo Recipe
    public static Recipe createRecipe(ResultSet resultSet) throws SQLException {


        String name = resultSet.getString("Nome");

        String descriptions = resultSet.getString("Ingredienti");
        String ingredients = resultSet.getString("Descrizione");


        return new Recipe(name, ingredients,descriptions);

    }




/*

    public void saveRecipe(Recipe recipe, Nutritionist nutritionist) throws DuplicatedUserException {
        Connection connection = DatabaseConnectionSingleton.getInstance().getConn();
        try(Statement statement = connection.createStatement()){
            RecipeQueries.saveIntoRecipe(statement,recipe,nutritionist );
        }catch(SQLIntegrityConstraintViolationException e){
            throw new DuplicatedUserException(e.getMessage());
        }catch(SQLException e){
            System.out.println("SQL Error");
        }
    }

     */
}
