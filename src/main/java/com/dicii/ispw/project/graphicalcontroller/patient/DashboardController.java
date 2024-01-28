package com.dicii.ispw.project.graphicalcontroller.patient;

import com.dicii.ispw.project.applicationcontroller.CreateNutritionalController;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.graphicalcontroller.ViewNutritionalPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {



    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label name;
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
    private DatePicker dataPicker;

    @FXML
    private Label selectionDate;

    @FXML
    private Label warning;

    String dataSelected;

    private PatientBean patientBean;

    private CreateNutritionalController createNutritionalController;



    public DashboardController(){
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
    @FXML
    void dataPicker(ActionEvent e){
        LocalDate localDate=dataPicker.getValue();
        String pattern="MMMM dd, yyyy";
        String dataPattern= localDate.format(DateTimeFormatter.ofPattern(pattern));
        System.out.print(selectionDate);
        selectionDate.setText(dataPattern);
        dataSelected=selectionDate.getText();


    }

    public void viewNutritionalPlan(ActionEvent event) throws Exception{


        try{

            FXMLLoader loader =new FXMLLoader(getClass().getResource("/firstGui/nutritionist/ViewNutritionalPlan.fxml"));
            root = loader.load();

            ViewNutritionalPlan viewNutritionalPlan = loader.getController();
            viewNutritionalPlan.takeParameter(dataSelected,patientBean.getIlnessesBean());


            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (NutritionalPlanNotFoundException e){
            warning.setText(e.getMessage());
        }


    }

    public void viewPatientInfo() throws DuplicatedUserException {

        try {


            patientBean=createNutritionalController.displayUserInfo();
            System.out.println(patientBean.getName());

            this.name.setText(patientBean.getName());
            this.surname.setText(patientBean.getSurname());
            this.birthday.setText(patientBean.getDateOfBirth());
            this.weight.setText(patientBean.getWeight());
            this.height.setText(patientBean.getHeight());
            this.ilneeses.setText(patientBean.getIlnessesBean());



        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }


    }








    @FXML
    public void switchHome(ActionEvent event) throws Exception {


        root = FXMLLoader.load(getClass().getResource("/firstGui/patient/dashboard/DashboardHome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchRecipes(ActionEvent event) throws Exception {


        root = FXMLLoader.load(getClass().getResource("/firstGui/patient/dashboard/DashboardRecipes.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void nutritionalPlanDashboard(ActionEvent event) throws Exception {


        root = FXMLLoader.load(getClass().getResource("/firstGui/nutritionist/NutritionalPlanDashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void switchPlan(ActionEvent event) throws Exception {


        root = FXMLLoader.load(getClass().getResource("/firstGui/patient/dashboard/DashboardPlan.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void viewDetails(ActionEvent event) throws Exception{
        root = FXMLLoader.load(getClass().getResource("/firstGui/patient/nutritionistdetails/ViewNutrizionista.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}