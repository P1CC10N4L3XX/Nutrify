package com.dicii.ispw.project.database.dao_classes;

import com.dicii.ispw.project.models.Recipe;


import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;


public class RecipeFileSaver {
    private static final String RECIPE_FILE_PATH = "src/main/java/com/dicii/ispw/project/database/ALLRECIPES";
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String ENCRYPTION_SECRET_KEY = "YourSecretKey";

    public void saveRecipeInFile(Recipe recipe) {
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            Key secretKey = generateKey();
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            try (FileOutputStream fileOutputStream = new FileOutputStream(RECIPE_FILE_PATH);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(new CipherOutputStream(fileOutputStream, cipher))) {
                objectOutputStream.writeObject(recipe);
            }
        } catch (Exception e) {
            // Gestire l'eccezione in modo appropriato
            e.printStackTrace();
        }
    }

    public Recipe loadRecipeFromFile() {
        Recipe recipe = null;
        try (FileInputStream fileInputStream = new FileInputStream(RECIPE_FILE_PATH);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            recipe = (Recipe) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            // Gestire l'eccezione in modo appropriato
            exception.printStackTrace();
        }
        return recipe;
    }

    private Key generateKey() {
        return new SecretKeySpec(ENCRYPTION_SECRET_KEY.getBytes(), ENCRYPTION_ALGORITHM);
    }
}

