package com.dicii.ispw.project.secondview;

import com.dicii.ispw.project.applicationcontroller.LoginApplicationController;
import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.exceptions.InvalidUserExceptionInfo;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.firstview.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import com.dicii.ispw.project.beans.UserCredentialsBean;
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

    private LoginApplicationController loginController;

    public LoginControllerGui(){
        loginController = new LoginApplicationController();
    }

    @FXML
    public void onCommand(ActionEvent event) throws IOException{
        String commandText = commandLine.getText();
        commandLine.setText("");
        if(commandText.matches("set email .*")){
            emailField.setText(commandText.replace("set email ", ""));
        }else if(commandText.matches("set password .*")){
            passwordField.setText(commandText.replace("set password ",""));
        }else if(commandText.matches("type .*")){
            typeField.setText(commandText.replace("type ", ""));
        }else if(commandText.compareTo("registration") == 0){
            GUI.switchPage(event, "/secondGui/Registration.fxml");
        }else if(commandText.compareTo("submit") == 0){
            makeLogin(event);
        }else{
            Alert commandAlert = new Alert(Alert.AlertType.WARNING, "COMANDO NON RICONOSCIUTO");
            commandAlert.showAndWait();
        }
    }

    private void makeLogin(ActionEvent event) throws IOException{
        try{
            UserCredentialsBean loginUserBean = loginInfo();
            Session.getSessionInstance().setLoggedUser(loginController.loginUser(loginUserBean));
            Session.getSessionInstance().initNotificatorSystem();
            boolean type=loginUserBean.getType();
            if(type){
                GUI.switchPage(event,"/secondGui/nutritionist/NutritionalPlanDashboard.fxml");
            }else{
                GUI.switchPage(event,"/secondGui/patient/PatientDashboard.fxml");
            }
        }catch(InvalidUserExceptionInfo | NotExistentUserException e){
            Alert completeAlert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            completeAlert.showAndWait();
        }
    }

    private UserCredentialsBean loginInfo() throws InvalidUserExceptionInfo {
        String email = emailField.getText();
        String password = passwordField.getText();
        String typeText = typeField.getText();
        if(email.isEmpty() || password.isEmpty() || typeText.isEmpty()) throw new InvalidUserExceptionInfo("compile all fields");
        if(!email.contains("@")) throw new InvalidUserExceptionInfo("The email isn't a valid format");
        if(typeText.equals("N")) {
            return new UserCredentialsBean(email, password, true);
        }else if(typeText.equals("P")){
            return new UserCredentialsBean(email,password,false);
        }else{
            throw new InvalidUserExceptionInfo("compile the type as N or P");
        }
    }
}
