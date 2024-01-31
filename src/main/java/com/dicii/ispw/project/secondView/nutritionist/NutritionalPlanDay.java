package com.dicii.ispw.project.secondView.nutritionist;

import com.dicii.ispw.project.applicationcontroller.CreateNutritionalController;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanFounded;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.firstView.utils.GUI;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class NutritionalPlanDay implements Initializable {

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

    private CreateNutritionalController createNutritionalController;


    private static final String BACK="back";

    private static final String SET_DATA="set data .*";

    private static final String VIEW_PLAN="view plan";

    private static final String CREATE_PLAN="create plan";


    public NutritionalPlanDay(){
        createNutritionalController = new CreateNutritionalController();
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

    public void onCommand(ActionEvent event) throws IOException, NotExistentUserException {
        String commandText = commandLine.getText();
        commandLine.setStyle(null);
        commandLine.setText("");
        if (commandText.matches(VIEW_PLAN)) {

            try{

                FXMLLoader loader =new FXMLLoader(getClass().getResource("/secondGui/nutritionist/ViewNutritionalPlan.fxml"));
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

            return;
        } else if (commandText.matches(CREATE_PLAN)) {
            try{

                if(data.getText()==null){
                    warning.setText("Seleziona la data");
                }else{
                    createNutritionalController.checkNutritionalPlanDay(data.getText());
                    FXMLLoader loader =new FXMLLoader(getClass().getResource("/secondGui/nutritionist/CreateNutritionalPlanDay.fxml"));
                    root = loader.load();

                    CreateNutritionalPlanDay createNutritionalPlanDay = loader.getController();
                    createNutritionalPlanDay.displayData(data.getText());

                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setResizable(false);
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                }

            }
            catch(NutritionalPlanFounded | DuplicatedUserException e){
                warning.setText(e.getMessage());
            }


        }
        else if (commandText.matches(SET_DATA)) {
            String dataValue = commandText.replace("set data ", "");

            if(checkData(dataValue)){
                data.setText(dataValue);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Formato Stringa non corretto Esempio:31/01/2024") ;
                alert.showAndWait() ;
            }

            return;
        }
        else if (commandText.matches(BACK)) {

            GUI.switchPage(event,"/secondGui/nutritionist/NutritionalPlanDashboard.fxml");

        }


    }


    public boolean checkData(String dataTime){

        boolean a;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        LocalDate date = LocalDate.parse(dataTime, formatter);
        if (dataTime.equals(date.format(formatter))) {
            a=true;
            System.out.println("La stringa ha il formato corretto.");
        } else {
            a=false;
            System.out.println("La stringa non ha il formato corretto.");
        }

        return a;
    }


}
