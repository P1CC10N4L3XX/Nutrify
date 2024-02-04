package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.models.Recipe;


import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;


public class RecipeFileSaver {

    private final String recipeFileName ;



    public RecipeFileSaver() {
        recipeFileName =  "src/main/java/com/dicii/ispw/project/database/ALLRECIPES" ;
    }


    private static final String SECRET_KEY = "YourSecretKey"; // Chiave segreta per la crittografia

    public void saveRecipeInFile(Recipe recipe) {
        try {

            Cipher cipher = Cipher.getInstance("AES");
            Key secretKey = generateKey();
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);


            try (FileOutputStream fileOutputStream = new FileOutputStream(recipeFileName);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(new CipherOutputStream(fileOutputStream, cipher))) {

                objectOutputStream.writeObject(recipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Key generateKey() {
        return new SecretKeySpec(RecipeFileSaver.SECRET_KEY.getBytes(), "AES");
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
