package com.dicii.ispw.project.secondView;


import com.dicii.ispw.project.applicationcontroller.RegisterApplicationController;
import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.firstView.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Map;

public class RegistrationController {


    @FXML
    private TextField subscribeCommandLine;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField confermapassword;
    @FXML
    private TextField userTypeField ;

    private Map<String, TextField> textFieldMap ;

    private Boolean type=false;

    private void initMap() {
        textFieldMap = Map.of(
                "set password", password,
                "set email", email,
                "set conferma password", confermapassword) ;
    }


    public void onCommand(ActionEvent event) throws IOException{

        String commandText = subscribeCommandLine.getText();
        subscribeCommandLine.setStyle(null);
        subscribeCommandLine.setText("");

        if (commandText.startsWith("set") && setCommand(commandText)) {

        } else if (commandText.startsWith("type") && typeCommand(commandText)) {

        }
        else if (commandText.startsWith("register") && registerCommand(commandText)) {


            if (userTypeField.getText().compareTo("N") == 0) type=true;
            else if(userTypeField.getText().compareTo("P")==0) type =false;

            UserBean userBean;
            try {
                userBean = retrieveInfo();
                RegisterApplicationController registerController = new RegisterApplicationController();
                registerController.registerUser(userBean);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Correctly Subscribed!!") ;
                alert.showAndWait() ;

                if(type){
                    GUI.switchPage(event,"/secondGui/nutritionist/NutritionistPersonalInfoRegistration.fxml");
                }else{
                    GUI.switchPage(event,"/secondGui/patient/PatientPersonalInfoRegistration.fxml");
                }


            }catch (DuplicatedUserException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }



        }  else if (commandText.compareTo("login") == 0) {
            GUI.switchPage(event,"/SecondGui/Login.fxml");

        }

    }


    private boolean setCommand(String insertedCommand) {
        initMap();
        for (Map.Entry<String, TextField> entry : textFieldMap.entrySet()) {
            String command = entry.getKey();
            if (insertedCommand.startsWith(command)) {
                String input = insertedCommand.replace(command + " ", "");
                if (input.compareTo(command) != 0) {
                    entry.getValue().setText(input);
                    return true;
                }
            }
        }
        return false ;
    }

    private boolean registerCommand(String command) {
        String registerString = "register" ;
        boolean handled = false ;
        if ((command.compareTo(registerString) == 0) || (command.compareTo(registerString + " google") == 0 || (command.compareTo(registerString + " facebook") == 0)) ) {
            handled = true ;
        }
        return handled;
    }

    private boolean typeCommand(String command) {
        String input = command.replace("type ", "") ;
        if (input.compareTo("N") == 0 || input.compareTo("P") == 0) {
            userTypeField.setText(input);
            type=true;
            return type;
        }
        return type ;
    }

    private UserBean retrieveInfo()  {
        UserBean userBeanInfo = new UserBean();
        userBeanInfo.setType(type);
        userBeanInfo.setEmail(email.getText());
        userBeanInfo.setPassword(password.getText());

        if(email.getText().isEmpty() || password.getText().isEmpty() ) {
            System.out.println("Set Valid Info!!");

        }
        return userBeanInfo;
    }











}



