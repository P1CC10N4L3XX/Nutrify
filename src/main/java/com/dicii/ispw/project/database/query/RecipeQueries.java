package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecipeQueries {



    public static boolean  saveIntoRecipe(Statement statement, Recipe recipe, String emailNutritionist) throws SQLException {
        String query = String.format("INSERT INTO ricetta (Nome,Ingredienti,Descrizione,Nutrizionista) values('%s','%s','%s','%s')",recipe.getName(),recipe.getIngredients(),recipe.getDescription(), emailNutritionist);
        return statement.execute(query);
    }

    public static ResultSet displayRecipe(Statement statement) throws SQLException {
        String query = String.format("SELECT * FROM ricetta ");
        return statement.executeQuery(query);
    }

    public static void deleteRecipe(Statement statement, String recipe) throws SQLException {
        String delete = String.format("DELETE FROM ricetta where Nome = '%s' ;",recipe);
        statement.execute(delete);
    }




    /*
    public static boolean  saveIntoRecipe(Statement statement, Recipe recipe, Nutritionist nutritionist) throws SQLException {
        String query = String.format("INSERT INTO mydb.ricetta (Nome, Ingredienti,Descrizione,Nutrizionista) values('%s','%s','%s','%s')",recipe.getName(),recipe.getIngredients(),recipe.getDescription(),nutritionist.getEmail());
        return statement.execute(query);
    }

     */

}
