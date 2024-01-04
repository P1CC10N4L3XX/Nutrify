package com.dicii.ispw.project.graphicalcontroller;

import com.dicii.ispw.project.applicationcontroller.RegisterApplicationController;
import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController{



    private Stage stage;
    private Scene scene;
    private Parent root;

    private UserBean userBean;

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    private RegisterApplicationController registerApplicationController;
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

        root = FXMLLoader.load(getClass().getResource("/firstGui/Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    protected void registerButton(ActionEvent event) throws IOException {
        if(!registerApplicationController.verifyEmailField(emailField.getText())){
            notificationLabel.setText("The email is invalid");
        }else if(!registerApplicationController.verifyPasswordField(passwordField.getText(),confirmPasswordField.getText())){
            notificationLabel.setText("The passwords don't match");
        }else{
            if(nutritionistRadioButton.isSelected()){
                userBean = new UserBean(emailField.getText(),passwordField.getText(),true);
                try {
                    registerApplicationController.registerUser(userBean);
                }catch(DuplicatedUserException e){
                    notificationLabel.setText("That email is used");
                }
                root = FXMLLoader.load(getClass().getResource("/firstGui/nutritionist/NutritionistPersonalInfo.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setResizable(false);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else if(patientRadioButton.isSelected()){
                userBean = new UserBean(emailField.getText(),passwordField.getText(),false);
                try{
                    registerApplicationController.registerUser(userBean);
                }catch(DuplicatedUserException e){
                    notificationLabel.setText("that email is used");
                }

            }
        }


    }











}