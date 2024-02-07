package com.dicii.ispw.project.secondview.patient;

import com.dicii.ispw.project.applicationcontroller.RegisterApplicationController;
import com.dicii.ispw.project.beans.IlnessesBean;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.InvalidUserExceptionInfo;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.firstview.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class PatientPersonalInfoRegistration implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField dateOfBirthDatePicker;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private TextField illnessesField;
    @FXML
    private ListView<String> ilnessesTextView;
    @FXML
    private Label notificationLabel;
    @FXML
    private TextField commandLine;
    @FXML
    private Alert completeAlert;

    @FXML
    private TextArea descriptionTextArea;


    private static final String SET_NAME="set name .*";

    private static final String SET_SURNAME="set surname .*";

    private static final String SET_BIRTHDAY="set birthday .*";

    private static final String SET_WEIGHT="set weight .*";

    private static final String SET_HEIGHT="set height .*";

    private static final String ILLNESSES="set illnesses .*";

    private static final String SUBMIT="submit";

    private static final String BACK="back";

    private List<IlnessesBean> list;

    private final RegisterApplicationController registerApplicationController;



    public PatientPersonalInfoRegistration(){
        registerApplicationController = new RegisterApplicationController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            list = registerApplicationController.displayIlnesses();
        } catch (DuplicatedUserException e) {
            throw new RuntimeException(e);
        }

        for (IlnessesBean ilnesesBean : list) {
            ilnessesTextView.getItems().addAll(String.valueOf(ilnesesBean.getName()));

        }

    }


    @FXML
    public void onCommand(ActionEvent event) throws IOException {
        String commandText = commandLine.getText() ;
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
        else if (commandText.matches(SET_WEIGHT)) {
            String weight = commandText.replace("set weight ", "") ;
            weightTextField.setText(weight);
        }
        else if (commandText.matches(SET_HEIGHT)) {
            String height = commandText.replace("set height ", "") ;
            heightTextField.setText(height);
        }
        else if (commandText.matches(ILLNESSES)) {
            String illnesses = commandText.replace("set illnesses ", "") ;
            if(checkIllnesses(illnesses)){
                illnessesField.setText(illnesses);
            }else{
                completeAlert.showAndWait() ;
            }
            illnessesField.setText(illnesses);
        }
        else if (commandText.matches(BACK)) {
            GUI.switchPage(event,"/secondGui/user/Registration.fxml");
        }
        else if (commandText.compareTo(SUBMIT) == 0) {
            try {
                PatientBean patientBean = patientInfo();
                registerApplicationController.completeRegistrationPatient(patientBean);
                GUI.switchPage(event,"/secondGui/patient/patientDashboard.fxml");
            }catch(InvalidUserExceptionInfo e){
                notificationLabel.setText(e.getMessage());
            }
        }


    }

    public boolean checkData(String dataTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dataTime, formatter);
        return dataTime.equals(date.format(formatter));
    }

    public boolean checkIllnesses(String recipeName){
        for (IlnessesBean ilnessesBean : list) {
            if(recipeName.matches(ilnessesBean.getName())){
                return true;
            }
        }
        return false;
    }


    private PatientBean patientInfo() throws InvalidUserExceptionInfo{
        if(nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || heightTextField.getText().isEmpty() || weightTextField.getText().isEmpty()  || dateOfBirthDatePicker.getText().isEmpty() || illnessesField.getText().isEmpty()){
            throw new InvalidUserExceptionInfo("compile all fields!");
        }
        return new PatientBean(Session.getSessionInstance().getLoggedUser().getEmail(),nameTextField.getText(),surnameTextField.getText(),descriptionTextArea.getText(),dateOfBirthDatePicker.getText(),weightTextField.getText(),heightTextField.getText(),new IlnessesBean(illnessesField.getText()));
    }


}
