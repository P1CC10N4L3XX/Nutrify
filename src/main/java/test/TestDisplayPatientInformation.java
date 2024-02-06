package test;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.IlnessesBean;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import org.junit.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDisplayPatientInformation {
    /**Author of the test:  Luca Cupellaro
     *                  Matricola 0307070
     */

    /**
     Nel database è stato precedentemente registrato il paziente
     *  con email 'luca@gmail.com' Nome luca, Cognome Cupellaro ... .
     * Lo scopo del seguente test è di verificare se vengano restituite
     * le giuste informazioni del paziente
     */


    IlnessesBean ilnessesBean= new IlnessesBean("Diabete");

    PatientBean patientBenExpected = new PatientBean("luca@gmail.com","luca","cupellaro","10/08","70.00","170","hgv",ilnessesBean);


    @Test
    public void testInformation() {
        int flag = 1;
        ManageNutritionalController createNutritionalController = new ManageNutritionalController();
        try {

            PatientBean patientBean;
            patientBean= createNutritionalController.displayUserInfo("luca@gmail.com");
            if(!Objects.equals(patientBenExpected.getName(), patientBean.getName()) ){
                flag = 0;
            }
        } catch (DuplicatedUserException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, flag);
    }




}
