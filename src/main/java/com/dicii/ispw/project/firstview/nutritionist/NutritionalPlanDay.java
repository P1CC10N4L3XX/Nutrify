package com.dicii.ispw.project.firstview.nutritionist;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;

import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanFounded;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.firstview.ViewNutritionalPlan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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
    private DatePicker dataPicker;

    @FXML
    private Label selectionDate;

    @FXML
    private Label warning;

    @FXML
    private Label patientSelected;

    private String dataSelected;

    private PatientBean patientBean;

    private ManageNutritionalController createNutritionalController;

    private String email;


    public NutritionalPlanDay(){
        createNutritionalController = new ManageNutritionalController();
    }




    @FXML
    void dataPicker(ActionEvent e){
        LocalDate localDate=dataPicker.getValue();
        String pattern="MMMM dd, yyyy";
        String dataPattern= localDate.format(DateTimeFormatter.ofPattern(pattern));
        selectionDate.setText(dataPattern);
        dataSelected=selectionDate.getText();
    }



    public void viewPatientInfo(String emailPatient) {

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





    public void createNutritionalPlanDay(ActionEvent event) throws Exception {



        try{
            LocalDate localDate=dataPicker.getValue();
            if(localDate==null){
                warning.setText("Seleziona la data");
            }else{
                createNutritionalController.checkNutritionalPlanDay(dataSelected,email);
                FXMLLoader loader =new FXMLLoader(getClass().getResource("/firstGui/nutritionist/CreateNutritionalPlan.fxml"));
                root = loader.load();

                CreateNutritionalPlanControllerGui createNutritionalPlanControllerGui = loader.getController();
                createNutritionalPlanControllerGui.displayData(dataSelected,email);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setResizable(false);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }

        }
        catch(NutritionalPlanFounded e){
                  warning.setText(e.getMessage());
        }


    }



    public void viewNutritionalPlan(ActionEvent event) throws Exception{


        try{

            FXMLLoader loader =new FXMLLoader(getClass().getResource("/firstGui/nutritionist/ViewNutritionalPlan.fxml"));
            root = loader.load();

            ViewNutritionalPlan viewNutritionalPlan = loader.getController();
            viewNutritionalPlan.takeParameter(dataSelected,patientBean.getIlnessesBean().getName(),email);


            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (NutritionalPlanNotFoundException e){
            warning.setText(e.getMessage());
        }



    }



}
