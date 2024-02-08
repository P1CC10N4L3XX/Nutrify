package test;

import com.dicii.ispw.project.database.csv_classes.PatientCSV;
import com.dicii.ispw.project.models.Ilnesses;
import com.dicii.ispw.project.models.Patient;
import org.junit.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestGetPatientInfoCSV {
    /**Author of the test: Alessandro Piccione
     *               Matricola: 0308516;
     **/
    //Il seguente test verifica il corretto funzionamento del metodo getPatientInfo di PatientCSV che restituisce un patient in seguito all'interrogazione del filePatient

    @Test
    public void getPatientInfoTest(){
        PatientCSV patientCSV = new PatientCSV();
        Patient patientTest = new Patient("piccionalex@gmail.com","Alessandro","Piccione","23-05-2002","80","1.80",new Ilnesses("diabete"));
        Patient patientResult = patientCSV.selectInfoPatient("piccionalex@gmail.com");

        assertEquals(patientTest.getName(),patientResult.getName());
    }
}
