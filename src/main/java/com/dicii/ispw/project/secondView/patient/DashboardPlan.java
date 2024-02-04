package com.dicii.ispw.project.secondView.patient;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.firstView.utils.GUI;
import com.dicii.ispw.project.secondView.ViewNutritionalPlan;
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
    private Label nome;
    @FXML
    private Label surname;

    @FXML
    private Label birthday;

    @FXML
    private Label weight;

    @FXML
    private Label height;

    @FXML
    private Label ilneeses;

    @FXML
    private TextField commandLine ;

    @FXML
    private Label warning;

    @FXML
    private TextField data;


    private PatientBean patientBean;

    private ManageNutritionalController createNutritionalController;


    private static final String BACK="back";

    private static final String SET_DATA="set data .*";

    private static final String VIEW_PLAN="view plan";



    public DashboardPlan(){
        createNutritionalController = new ManageNutritionalController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            viewPatientInfo();
        } catch (DuplicatedUserException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewPatientInfo() throws DuplicatedUserException {

        try {

            patientBean=createNutritionalController.displayUserInfo();

            this.nome.setText(patientBean.getName());
            this.surname.setText(patientBean.getSurname());
            this.birthday.setText(patientBean.getDateOfBirth());
            this.weight.setText(patientBean.getWeight());
            this.height.setText(patientBean.getHeight());
            this.ilneeses.setText(patientBean.getIlnessesBean().getName());



        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }


    }

    public void onCommand(ActionEvent event) throws Exception {
        String commandText = commandLine.getText();
        commandLine.setStyle(null);
        commandLine.setText("");
        if (commandText.matches(VIEW_PLAN)) {

            try{

                FXMLLoader loader =new FXMLLoader(getClass().getResource("/secondGui/ViewNutritionalPlan.fxml"));
                root = loader.load();

                ViewNutritionalPlan viewNutritionalPlan = loader.getController();
                viewNutritionalPlan.takeParameter(data.getText(),patientBean.getIlnessesBean().getName());


                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setResizable(false);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }catch (NutritionalPlanNotFoundException | DuplicatedUserException e){
                warning.setText(e.getMessage());
            }



        }
        else if (commandText.matches(SET_DATA)) {
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
        else if (commandText.matches(BACK)) {

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
