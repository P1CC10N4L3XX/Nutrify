package com.dicii.ispw.project.secondview.nutritionist;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanFounded;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.firstview.utils.GUI;
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

public class NutritionalPlanDay {

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

    @FXML
    private Label patientSelected;

    private String email;



    private PatientBean patientBean;

    private ManageNutritionalController createNutritionalController;


    private static final String BACK="back";

    private static final String SET_DATA="set data .*";

    private static final String VIEW_PLAN="view plan";

    private static final String CREATE_PLAN="create plan";


    public NutritionalPlanDay(){
        createNutritionalController = new ManageNutritionalController();
    }



    public void viewPatientInfo(String emailPatient) throws DuplicatedUserException {

            email=emailPatient;
            patientSelected.setText(emailPatient);

            patientBean=createNutritionalController.displayUserInfo(emailPatient);

            this.nome.setText(patientBean.getName());
            this.surname.setText(patientBean.getSurname());
            this.birthday.setText(patientBean.getDateOfBirth());
            this.weight.setText(patientBean.getWeight());
            this.height.setText(patientBean.getHeight());
            this.ilneeses.setText(patientBean.getIlnessesBean().getName());

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
                viewNutritionalPlan.takeParameter(data.getText(),patientBean.getIlnessesBean().getName(),patientSelected.getText());


                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setResizable(false);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }catch (NutritionalPlanNotFoundException | DuplicatedUserException e){
                e.printStackTrace();
                System.exit(0);
            }


        } else if (commandText.matches(CREATE_PLAN)) {
            try{

                if(data.getText()==null){
                    warning.setText("Seleziona la data");
                }else{
                    createNutritionalController.checkNutritionalPlanDay(data.getText(),email);
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
                e.printStackTrace();
                System.exit(0);
            }


        }
        else if (commandText.matches(SET_DATA)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Formato Stringa non corretto Esempio:gennaio 16, 2024") ;
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

            GUI.switchPage(event,"/secondGui/nutritionist/NutritionalPlanDashboard.fxml");

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
