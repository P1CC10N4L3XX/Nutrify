package com.dicii.ispw.project.firstview;

import com.dicii.ispw.project.applicationcontroller.LoginApplicationController;
import com.dicii.ispw.project.beans.UserCredentialsBean;
import com.dicii.ispw.project.database.TypesOfPersistenceLayer;
import com.dicii.ispw.project.exceptions.InvalidUserExceptionInfo;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.firstview.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController{

    @FXML
    private RadioButton nutritionistRadioButton;
    @FXML
    private RadioButton patientRadioButton;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label notificationLabel;
    private final LoginApplicationController loginApplicationController;

    public LoginController(){
        loginApplicationController = new LoginApplicationController(TypesOfPersistenceLayer.JDBC);
    }


    @FXML
    protected void switchRegistration(ActionEvent event) throws IOException {
        GUI.switchPage(event,"/firstGui/Registration.fxml");
    }


    public void loginButton(ActionEvent event) throws IOException {
        try{
            UserCredentialsBean loginUserCredentialsBean = loginInfo();
            Session.getSessionInstance().setLoggedUser(loginApplicationController.loginUser(loginUserCredentialsBean));
            Session.getSessionInstance().initNotificatorSystem();
            if(patientRadioButton.isSelected()){
                GUI.switchPage(event,"/firstGui/patient/dashboard/DashboardHome.fxml");
            }
            if(nutritionistRadioButton.isSelected()){
                GUI.switchPage(event,"/firstGui/nutritionist/dashboard/DashboardHome.fxml");
            }
        }catch(InvalidUserExceptionInfo | NotExistentUserException e){
            notificationLabel.setText(e.getMessage());
        }
    }
    private UserCredentialsBean loginInfo() throws InvalidUserExceptionInfo{
        String email=emailField.getText();
        String password=passwordField.getText();
        boolean nutritionist = nutritionistRadioButton.isSelected();
        boolean patient = patientRadioButton.isSelected();
        if(email.isEmpty() || password.isEmpty() || (!patient && !nutritionist)) throw new InvalidUserExceptionInfo("Compile all fields");
        if(!email.contains("@")) throw new InvalidUserExceptionInfo("The email isn't a valid format");
        return new UserCredentialsBean(email,password,nutritionist);

    }


}
