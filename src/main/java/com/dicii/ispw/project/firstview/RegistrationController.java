package com.dicii.ispw.project.firstview;

import com.dicii.ispw.project.applicationcontroller.RegisterApplicationController;
import com.dicii.ispw.project.beans.UserCredentialsBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.InvalidUserExceptionInfo;
import com.dicii.ispw.project.firstview.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistrationController{
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    private final RegisterApplicationController registerApplicationController;
    @FXML
    private Label notificationLabel;
    @FXML
    private RadioButton nutritionistRadioButton;
    @FXML
    private RadioButton patientRadioButton;




    public RegistrationController(){
        registerApplicationController= new RegisterApplicationController();
    }

    @FXML
    protected void switchLogin(ActionEvent event) throws Exception {
        GUI.switchPage(event,"/firstGui/Login.fxml");
    }

    @FXML
    protected void registerButton(ActionEvent event) throws IOException {
            try{
                UserCredentialsBean userCredentialsBean = userCredentials();
                registerApplicationController.registerUser(userCredentialsBean);
                Session.getSessionInstance().setLoggedUser(userCredentialsBean);
                GUI.switchPage(event,(nutritionistRadioButton.isSelected()) ? ("/firstGui/nutritionist/NutritionistPersonalInfoRegistration.fxml") : ("/firstGui/patient/PatientPersonalInfoRegistration.fxml"));
            }catch(InvalidUserExceptionInfo e){
                notificationLabel.setText(e.getMessage());
            }catch(DuplicatedUserException e){
                notificationLabel.setText("That email is used");
            }
        }
        private UserCredentialsBean userCredentials() throws InvalidUserExceptionInfo{
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            boolean selectedNutritionist = nutritionistRadioButton.isSelected();
            boolean selectedPatient = patientRadioButton.isSelected();
            if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || (!selectedPatient && !selectedNutritionist)){
                throw new InvalidUserExceptionInfo("Compile all fields");
            }else if(!email.contains("@")){
                throw new InvalidUserExceptionInfo("Invalid email format");
            }else if(!password.equals(confirmPassword)){
                throw new InvalidUserExceptionInfo("Passwords don't match");
            }
            return new UserCredentialsBean(email,password,selectedNutritionist);

        }

}