package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.models.Recipe;

import java.io.*;

public class RecipeFileSaver {

    private final String recipeFileName ;



    public RecipeFileSaver() {
        recipeFileName =  "src/main/java/com/dicii/ispw/project/database/ALLRECIPES" ;
    }

    public void saveRecipeInFile(Recipe recipe) {
        try(
                FileOutputStream fileOutputStream = new FileOutputStream(recipeFileName);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(recipe);
        } catch (IOException ignored) {
            //If the file isn't found, then the previous Cart isn't saved
        }
    }

    public Recipe loadRecipeFromFile() {
        Recipe recipe = new Recipe() ;
        try(
                FileInputStream fileInputStream = new FileInputStream(recipeFileName);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ) {
            recipe = (Recipe) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            //If the file doesn't contain a previous cart, then is given back a new Cart instance
        }
        return recipe ;
    }

}
