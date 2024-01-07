package com.dicii.ispw.project.graphicalcontroller.patient;

import com.dicii.ispw.project.applicationcontroller.RegisterApplicationController;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.exceptions.InvalidUserExceptionInfo;
import com.dicii.ispw.project.graphicalcontroller.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class PersonalInfoController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private DatePicker dateOfBirthDatePicker;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private Label notificationLabel;
    private final RegisterApplicationController registerApplicationController;

    public PersonalInfoController(){
        registerApplicationController = new RegisterApplicationController();
    }
    @FXML
    public void completeRegistration(ActionEvent event) throws IOException {
        try {
            PatientBean patientBean = patientInfo();
            registerApplicationController.completeRegistrationPatient(patientBean);
            GUI.switchPage(event,"/firstGui/patient/dashboard/DashboardHome.fxml");
        }catch(InvalidUserExceptionInfo e){
            notificationLabel.setText(e.getMessage());
        }

    }
    private PatientBean patientInfo() throws InvalidUserExceptionInfo{
        if(nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || heightTextField.getText().isEmpty() || weightTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty() || dateOfBirthDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).isEmpty()){
            throw new InvalidUserExceptionInfo("compile all fields!");
        }
        return new PatientBean(Session.getSessionInstance().getLoggedUser().getEmail(),nameTextField.getText(),surnameTextField.getText(),descriptionTextArea.getText(),dateOfBirthDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),weightTextField.getText(),heightTextField.getText());
    }
}
