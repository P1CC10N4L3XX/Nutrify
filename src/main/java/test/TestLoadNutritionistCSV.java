package test;


import com.dicii.ispw.project.database.csv_classes.NutritionistCSV;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.models.UserCredentials;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLoadNutritionistCSV {
    /*Author of the test : Alessandro Piccione
                    Matricola: 0308516
    * */
    //Il seguente test verifica il metodo loadNutritionistByCredentials della classe NutritionistCSV

    @Test
    public void testLoadNutritionistByCredentials() throws NotExistentUserException {
        int a=1;
        NutritionistCSV nutritionistCSV = new NutritionistCSV();
        UserCredentials expectedUserCredentials = new UserCredentials("p@gmail.com","ciao");
        UserCredentials resultUserCredentials = nutritionistCSV.loadNutritionistByCredentials(expectedUserCredentials);
        if(!resultUserCredentials.getEmail().equals(expectedUserCredentials.getEmail()) || !resultUserCredentials.getPassword().equals(expectedUserCredentials.getPassword())){
            a=0;
        }
        assertEquals(1,a);
    }
}
