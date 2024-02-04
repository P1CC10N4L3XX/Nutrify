package com.dicii.ispw.project.secondView.nutritionist;

import com.dicii.ispw.project.applicationcontroller.RegisterApplicationController;
import com.dicii.ispw.project.beans.NutritionistBean;

import com.dicii.ispw.project.exceptions.InvalidUserExceptionInfo;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class NutritionistPersonalInfoRegistration {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField dateOfBirthDatePicker;
    @FXML
    private TextField ibanTextField;
    @FXML
    private TextField ivaTextField;
    @FXML
    private TextField costTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private Label notificationLabel;

    @FXML
    private TextField commandLine;

    private static final String SET_NAME="set name .*";

    private static final String SET_SURNAME="set surname .*";

    private static final String SET_BIRTHDAY="set birthday .*";

    private static final String SET_IBAN="set iban .*";

    private static final String SET_COST="set cost .*";

    private static final String DESCRIPTION="set description .*";

    private static final String SUBMIT="submit";

    private static final String BACK="back";

    private final RegisterApplicationController registerApplicationController;



    public NutritionistPersonalInfoRegistration(){
        registerApplicationController = new RegisterApplicationController();
    }


    @FXML
    public void onCommand(ActionEvent event) throws IOException, NotExistentUserException, InvalidUserExceptionInfo {
        String commandText = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");
        if (commandText.matches(SET_NAME)) {
            String name = commandText.replace("set name ", "") ;
            nameTextField.setText(name);

        }
        else if (commandText.matches(SET_SURNAME)) {
            String username = commandText.replace("set surname ", "") ;
            surnameTextField.setText(username);

        }
        else if (commandText.matches(SET_BIRTHDAY)) {
            String birthday = commandText.replace("set birthday ", "") ;
            if(checkData(birthday)){
                dateOfBirthDatePicker.setText(birthday);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Formato Stringa non corretto Esempio:31/01/2024") ;
                alert.showAndWait() ;
            }

        }
        else if (commandText.compareTo(SET_IBAN) == 0) {
            String iban = commandText.replace("set iban ", "") ;
            ibanTextField.setText(iban);

        }
        else if (commandText.compareTo(SET_COST) == 0) {
            String cost = commandText.replace("set cost ", "") ;
            costTextField.setText(cost);

        }
        else if (commandText.compareTo(DESCRIPTION) == 0) {
            String iva = commandText.replace("set iva ", "") ;
            ivaTextField.setText(iva);


        }
        else if (commandText.compareTo(BACK) == 0) {
            String description = commandText.replace("set description ", "") ;
            descriptionTextArea.setText(description);

        }
        else if (commandText.compareTo(SUBMIT) == 0) {
            try {
                NutritionistBean nutritionistBean = nutritionistInfo();
                registerApplicationController.completeRegistrationNutritionist(nutritionistBean);
            }catch(InvalidUserExceptionInfo e){
                notificationLabel.setText(e.getMessage());
            }
        }


    }

    public boolean checkData(String dataTime){

        boolean a;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dataTime, formatter);
        if (dataTime.equals(date.format(formatter))) {
            a=true;

        } else {
            a=false;
        }

        return a;
    }

    private NutritionistBean nutritionistInfo() throws InvalidUserExceptionInfo{
        if(nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || ibanTextField.getText().isEmpty() ||
                ivaTextField.getText().isEmpty() || costTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty() ||
                dateOfBirthDatePicker.getText().isEmpty()){
            throw new InvalidUserExceptionInfo("compile all fields!");
        }
        return new NutritionistBean(Session.getSessionInstance().getLoggedUser().getEmail(),nameTextField.getText(),surnameTextField.getText(),descriptionTextArea.getText(),dateOfBirthDatePicker.getText(),ivaTextField.getText(),ibanTextField.getText(),costTextField.getText());
    }
}
