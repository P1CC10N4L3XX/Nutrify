package com.dicii.ispw.project.secondview;


import com.dicii.ispw.project.applicationcontroller.RegisterApplicationController;
import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.InvalidUserExceptionInfo;
import com.dicii.ispw.project.firstview.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistrationController {
    @FXML
    private TextField commandLine;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField confermaPassword;
    @FXML
    private TextField userTypeField ;

    private final RegisterApplicationController registerApplicationController;

    public RegistrationController(){
        registerApplicationController = new RegisterApplicationController();
    }
    public void onCommand(ActionEvent event) throws IOException {
        String commandText = commandLine.getText();
        commandLine.setText("");
        if(commandText.matches("set email .*")){
            email.setText(commandText.replace("set email ",""));
        }else if(commandText.matches("set password .*")){
            password.setText(commandText.replace("set password ",""));
        }else if(commandText.matches("set conferma password .*")){
            confermaPassword.setText(commandText.replace("set conferma password ",""));
        }else if(commandText.matches("type .*")){
            userTypeField.setText(commandText.replace("type ",""));
        }else if(commandText.compareTo("login") == 0){
            GUI.switchPage(event,"/secondGui/login.fxml");
        }else if(commandText.compareTo("register") == 0){
            makeRegistration(event);
        }else{
            Alert commandAlert = new Alert(Alert.AlertType.WARNING, "COMANDO NON RICONOSCIUTO");
            commandAlert.showAndWait();
        }
    }

    private void makeRegistration(ActionEvent event) throws IOException{
        try {
            UserBean userBean = userInfo();
            registerApplicationController.registerUser(userBean);
            Session.getSessionInstance().setLoggedUser(userBean);
            GUI.switchPage(event,(userBean.getType()) ? ("/secondGui/nutritionist/NutritionistPersonalInfoRegistration.fxml") : ("/secondGui/patient/PatientPersonalInfoRegistration.fxml"));
        }catch(InvalidUserExceptionInfo | DuplicatedUserException e){
            Alert completeAlert = new Alert(Alert.AlertType.WARNING, e.getMessage());
            completeAlert.showAndWait();
        }
    }

    private UserBean userInfo() throws InvalidUserExceptionInfo{
        String email = this.email.getText();
        String password = this.password.getText();
        String confermaPassword = this.confermaPassword.getText();
        String typeText = userTypeField.getText();
        boolean type = (typeText.equals("N"));
        if(email.isEmpty() || password.isEmpty() || confermaPassword.isEmpty() ||  (!typeText.equals("N") && !typeText.equals("P"))) throw new InvalidUserExceptionInfo("compile all fields");
        if(!password.equals(confermaPassword)) throw new InvalidUserExceptionInfo("Passwords don't match");
        if(!email.contains("@")) throw new InvalidUserExceptionInfo("The email isn't a valid format");
        return new UserBean(email,password,type);
    }
}



