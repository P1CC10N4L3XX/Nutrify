package com.dicii.ispw.project.secondView;

import com.dicii.ispw.project.applicationcontroller.LoginApplicationController;
import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.firstView.LoginController;
import com.dicii.ispw.project.firstView.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class LoginControllerGui {

    @FXML
    public TextField commandLine ;
    @FXML public TextField emailField ;

    @FXML public TextField typeField ;
    @FXML public TextField passwordField ;

    private Boolean type;

    @FXML public Button enterButton ;

    private LoginApplicationController loginController;

    public LoginControllerGui(){loginController = new LoginApplicationController();
    }

    @FXML
    public void onCommand(ActionEvent event) throws IOException, NotExistentUserException {
        String commandText = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");
        if (commandText.matches("set email .*")) {
            String username = commandText.replace("set email ", "") ;
            emailField.setText(username);
            return ;
        }
        else if (commandText.matches("set password .*")) {
            String password = commandText.replace("set password ", "") ;
            passwordField.setText(password);
            return ;
        }
        else if (commandText.matches("type .*")) {
            String type = commandText.replace("type ", "") ;
            typeField.setText(type);
            return ;
        }
        else if (commandText.compareTo("subscribe") == 0) {
            GUI.switchPage(event,"/SecondGui/RegistrationController.fxml");
            return ;
        }
        else if (commandText.compareTo("submit") == 0) {
            String userEmail = emailField.getText() ;
            String userPassword = passwordField.getText() ;
            String typecamp = typeField.getText() ;
            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Alert completeAlert = new Alert(Alert.AlertType.WARNING, "COMPLETARE TUTTI I CAMPI") ;
                completeAlert.showAndWait() ;
            }
            else {
                if(Objects.equals(typecamp, "N")){
                    type=true;

                }
                if (Objects.equals(typecamp, "P")){
                    type=false;
                }


                UserBean loggedUser = new UserBean(userEmail, userPassword,type);
                Session.getSessionInstance().setLoggedUser(loginController.loginUser(loggedUser));

                if(Objects.equals(typecamp, "N")){

                    GUI.switchPage(event,"/secondGui/nutritionist/NutritionalPlanDashboard.fxml");
                }
                if(Objects.equals(typecamp, "P")){
                    //GUI.switchPage(event,"/firstGui/nutritionist/NutritionalPlanDashboard.fxml");
                }
            }
            return ;
        }


    }



}
