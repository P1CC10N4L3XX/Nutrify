package test;


import com.dicii.ispw.project.database.csv_classes.PatientCSV;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.models.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLoadNutritionistSubscribeCSV {
    /*Author of the test : Alessandro Piccione
                    Matricola: 0308516
    * */
    //Il seguente test verifica se viene prelevata correttamente l'email di un nutrizionista a cui è iscritto un paziente
    @Test
    public void loadNutritionistSubscribedTest() throws NotExistentUserException {
        PatientCSV patientCSV = new PatientCSV();
        User user = patientCSV.loadNutritionistSubscribed("piccionalex@gmail.com");
        assertEquals("p@gmail.com",user.getEmail());
    }
}
