package com.dicii.ispw.project.secondview.patient;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.beans.UserCredentialsBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.firstview.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import com.dicii.ispw.project.secondview.ViewNutritionalPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class DashboardPlan implements Initializable {
    private Stage stage;
    private Scene scene;

    private Parent root;

    @FXML
    private Label nameText;
    @FXML
    private Label surnameText;

    @FXML
    private Label birthdayText;

    @FXML
    private Label weightText;

    @FXML
    private Label heightText;

    @FXML
    private Label ilneesesText;

    @FXML
    private TextField commandLine ;

    @FXML
    private Label warning;

    @FXML
    private TextField data;


    private PatientBean patientBean;

    private ManageNutritionalController createNutritionalController;


    private static final String BACKINPUT="back";

    private static final String SET_DATAINPUT="set data .*";

    private static final String VIEW_PLANINPUT="view plan";



    public DashboardPlan(){
        createNutritionalController = new ManageNutritionalController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewPatientInfo();
    }

    public void viewPatientInfo() {
            patientBean=createNutritionalController.displayUserInfo(Session.getSessionInstance().getLoggedUser().getEmail());
            this.nameText.setText(patientBean.getName());
            this.surnameText.setText(patientBean.getSurname());
            this.birthdayText.setText(patientBean.getDateOfBirth());
            this.weightText.setText(patientBean.getWeight());
            this.heightText.setText(patientBean.getHeight());
            this.ilneesesText.setText(patientBean.getIlnessesBean().getName());


    }

    public void onCommand(ActionEvent event) throws Exception {
        String commandText = commandLine.getText();
        commandLine.setStyle(null);
        commandLine.setText("");
        if (commandText.matches(VIEW_PLANINPUT)) {

            try{

                UserCredentialsBean userCredentialsBean = createNutritionalController.loadNutritionistSubscribed(Session.getSessionInstance().getLoggedUser().getEmail());

                FXMLLoader loader =new FXMLLoader(getClass().getResource("/secondGui/ViewNutritionalPlan.fxml"));
                root = loader.load();

                ViewNutritionalPlan viewNutritionalPlan = loader.getController();
                viewNutritionalPlan.takeParameter(data.getText(),patientBean.getIlnessesBean().getName(), userCredentialsBean.getEmail());


                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setResizable(false);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }catch (NutritionalPlanNotFoundException | DuplicatedUserException e){
                warning.setText(e.getMessage());
            }



        }
        else if (commandText.matches(SET_DATAINPUT)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Formato Stringa non corretto Esempio:31/01/2024") ;
            try{
                String dataValue = commandText.replace("set data ", "");

                if(checkData(dataValue)){
                    data.setText(dataValue);
                }else{

                    alert.showAndWait() ;
                }

            }catch (DateTimeParseException e){
                alert.showAndWait() ;
            }


        }
        else if (commandText.matches(BACKINPUT)) {

            GUI.switchPage(event,"/secondGui/patient/PatientDashboard.fxml");

        }


    }


    public boolean checkData(String dataTime){

        boolean a;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        LocalDate date = LocalDate.parse(dataTime, formatter);
        if (dataTime.equals(date.format(formatter))) {
            a=true;

        } else {
            a=false;

        }

        return a;
    }

}
