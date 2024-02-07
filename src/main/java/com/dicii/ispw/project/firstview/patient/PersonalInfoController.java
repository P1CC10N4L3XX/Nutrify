package com.dicii.ispw.project.firstview.patient;

import com.dicii.ispw.project.applicationcontroller.RegisterApplicationController;
import com.dicii.ispw.project.beans.IlnessesBean;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.InvalidUserExceptionInfo;
import com.dicii.ispw.project.firstview.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class PersonalInfoController implements Initializable {
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
    private Label notificationLabel;

    private List<IlnessesBean> list;

    private String illnes;

    private IlnessesBean ilnessesBean;

    @FXML
    private ChoiceBox<String> illnessList;
    private final RegisterApplicationController registerApplicationController;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            list = registerApplicationController.displayIlnesses();
        } catch (DuplicatedUserException e) {
            e.printStackTrace();
            System.exit(0);
        }

        for (IlnessesBean ilnesses : list) {
            illnessList.getItems().addAll(String.valueOf(ilnesses.getName()));

        }


        illnessList.setOnAction(this::getIlnesses);

    }



    public void getIlnesses(ActionEvent event){
        illnes= illnessList.getValue();
        ilnessesBean = new IlnessesBean();
        ilnessesBean.setName(illnes);

    }



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
        if(nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || heightTextField.getText().isEmpty() || weightTextField.getText().isEmpty() || dateOfBirthDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).isEmpty()){
            throw new InvalidUserExceptionInfo("compile all fields!");
        }
        return new PatientBean(Session.getSessionInstance().getLoggedUser().getEmail(),nameTextField.getText(),surnameTextField.getText(),dateOfBirthDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),weightTextField.getText(),heightTextField.getText(),ilnessesBean);
    }
}
