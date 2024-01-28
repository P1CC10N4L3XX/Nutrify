package test;

import com.dicii.ispw.project.applicationcontroller.LoginApplicationController;
import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import org.junit.Test;


import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLoginController {


    /**Author of the test:  Luca Cupellaro
     *                  Matricola 0307070
     */

    private static final String EMAIL = "luca@gmail.com";
    private static final String PASSWORD = "123";

    /**
     * Nel database è stato precedentemente registrato il paziente
     *  con email 'luca@gmail.com' e password '123'.
     * Lo scopo del seguente test è di verificare se il login con tali credenziali
     *  vada effettivamente a buon fine e restituisca il paziente corretto.
     */
    @Test
    public void testLogin() {
        int flag = 1;
        LoginApplicationController loginApplicationController = new LoginApplicationController();
        try {
            UserBean userBean = new UserBean(EMAIL,PASSWORD,false);
            //CredentialsBean credentialsBean = CredentialsBean.ctorWithSyntaxCheck(EMAIL, PASSWORD);
            userBean = loginApplicationController.loginUser(userBean);
            if(!Objects.equals(userBean.getEmail(), userBean.getEmail()) || !Objects.equals(userBean.getPassword(), userBean.getPassword())){
                flag = 0;
            }
        } catch (NotExistentUserException e) {

            flag = -1;
            e.printStackTrace();
        }
        assertEquals(1, flag);
    }


}
