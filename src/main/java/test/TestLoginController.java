package test;

import com.dicii.ispw.project.applicationcontroller.LoginApplicationController;
import com.dicii.ispw.project.beans.UserCredentialsBean;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import org.junit.Test;


import java.io.FileInputStream;
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

        String password;
        String username;

        try(FileInputStream fileInputStream = new FileInputStream("src/main/java/test/test.properties");){
            Properties prop = new Properties();
            prop.load(fileInputStream);
            password = prop.getProperty("PASSWORD");
            username = prop.getProperty("EMAIL");
        }

        int flag = 1;
        LoginApplicationController loginApplicationController = new LoginApplicationController();
        try {
            UserCredentialsBean userCredentialsBean = new UserCredentialsBean(username,password,false);
            UserCredentialsBean userReturned = loginApplicationController.loginUser(userCredentialsBean);
            if(!Objects.equals(userCredentialsBean.getEmail(), userReturned.getEmail()) || !Objects.equals(userCredentialsBean.getPassword(), userReturned.getPassword())){
                flag = 0;
            }
        } catch (NotExistentUserException e) {

            flag = -1;

        }
        assertEquals(1, flag);
    }


}
