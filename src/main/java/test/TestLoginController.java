package test;

import com.dicii.ispw.project.applicationcontroller.LoginApplicationController;
import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import org.junit.Test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLoginController {


    /**Author of the test:  Luca Cupellaro
     *                  Matricola 0307070
     */


    /**
     * Nel database è stato precedentemente registrato il paziente
     *  con email 'luca@gmail.com' e password '123'.
     * Lo scopo del seguente test è di verificare se il login con tali credenziali
     *  vada effettivamente a buon fine e restituisca il paziente corretto.
     */
    @Test
    public void testLogin() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("src/main/java/test/test.properties");
        Properties prop = new Properties();
        prop.load(fileInputStream);
        String password = prop.getProperty("PASSWORD");
        String username = prop.getProperty("EMAIL");
        int flag = 1;
        LoginApplicationController loginApplicationController = new LoginApplicationController();
        try {
            UserBean userBean = new UserBean(username,password,false);
            UserBean userReturned = loginApplicationController.loginUser(userBean);
            if(!Objects.equals(userBean.getEmail(), userReturned.getEmail()) || !Objects.equals(userBean.getPassword(), userReturned.getPassword())){
                flag = 0;
            }
        } catch (NotExistentUserException e) {

            flag = -1;
            e.printStackTrace();
        }
        assertEquals(1, flag);
    }


}
