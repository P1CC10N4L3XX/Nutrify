package com.dicii.ispw.project.database.query;

import com.dicii.ispw.project.models.Ilnesses;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Recipe;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RecipeQueries {



    public static boolean  saveIntoRecipe(Statement statement, Recipe recipe, Nutritionist nutritionist) throws SQLException {
        String query = String.format("INSERT INTO mydb.ricetta (Nome, Ingredienti,Descrizione,Nutrizionista) values('%s','%s','%s','%s')",recipe.getName(),recipe.getIngredients(),recipe.getDescription(),nutritionist.getEmail());
        return statement.execute(query);
    }

}
