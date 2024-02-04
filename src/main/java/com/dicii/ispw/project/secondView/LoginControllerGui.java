package com.dicii.ispw.project.secondView;

import com.dicii.ispw.project.applicationcontroller.LoginApplicationController;
import com.dicii.ispw.project.beans.UserBean;

import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.firstView.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class LoginControllerGui {

    @FXML
    private TextField commandLine ;
    @FXML
    private TextField emailField ;
    @FXML
    private TextField typeField ;
    @FXML
    private TextField passwordField ;
    @FXML
    private Button enterButton ;

    private Boolean type;



    private LoginApplicationController loginController;

    public LoginControllerGui(){loginController = new LoginApplicationController();
    }

    @FXML
    public void onCommand(ActionEvent event) throws IOException {
        String commandText = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");
        if (commandText.matches("set email .*")) {
            String username = commandText.replace("set email ", "") ;
            emailField.setText(username);

        }
        else if (commandText.matches("set password .*")) {
            String password = commandText.replace("set password ", "") ;
            passwordField.setText(password);

        }
        else if (commandText.matches("type .*")) {
            String type = commandText.replace("type ", "") ;
            typeField.setText(type);

        }
        else if (commandText.compareTo("subscribe") == 0) {
            GUI.switchPage(event,"/SecondGui/RegistrationController.fxml");

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
                try{
                    UserBean loggedUser = new UserBean(userEmail, userPassword,type);
                    Session.getSessionInstance().setLoggedUser(loginController.loginUser(loggedUser));

                    if(Objects.equals(typecamp, "N")){

                        GUI.switchPage(event,"/secondGui/nutritionist/NutritionalPlanDashboard.fxml");
                    }
                    if(Objects.equals(typecamp, "P")){
                        GUI.switchPage(event,"/secondGui/patient/PatientDashBoard.fxml");
                    }
                }catch (NotExistentUserException e){
                    Alert completeAlert = new Alert(Alert.AlertType.WARNING, "user does not exist") ;
                    completeAlert.showAndWait() ;

                }


            }

        }


    }



}
