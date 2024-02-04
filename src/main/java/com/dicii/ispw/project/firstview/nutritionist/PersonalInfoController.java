package com.dicii.ispw.project.firstview.nutritionist;

import com.dicii.ispw.project.applicationcontroller.RegisterApplicationController;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.exceptions.InvalidUserExceptionInfo;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class PersonalInfoController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private DatePicker dateOfBirthDatePicker;
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

    private final RegisterApplicationController registerApplicationController;

    public PersonalInfoController(){
        registerApplicationController = new RegisterApplicationController();
    }
    @FXML
    public void completeRegistration(){
        try {
            NutritionistBean nutritionistBean = nutritionistInfo();
            registerApplicationController.completeRegistrationNutritionist(nutritionistBean);
        }catch(InvalidUserExceptionInfo e){
            notificationLabel.setText(e.getMessage());
        }
    }
    private NutritionistBean nutritionistInfo() throws InvalidUserExceptionInfo{
        if(nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || ibanTextField.getText().isEmpty() || ivaTextField.getText().isEmpty() || costTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty() || dateOfBirthDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).isEmpty()){
            throw new InvalidUserExceptionInfo("compile all fields!");
        }
        return new NutritionistBean(Session.getSessionInstance().getLoggedUser().getEmail(),nameTextField.getText(),surnameTextField.getText(),descriptionTextArea.getText(),dateOfBirthDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),ivaTextField.getText(),ibanTextField.getText(),costTextField.getText());
    }
}
