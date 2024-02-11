package com.dicii.ispw.project.secondview;
import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanDayBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.firstview.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class ViewNutritionalPlan {

    @FXML
    private Label colazioneField;

    @FXML
    private Label pranzoField;

    @FXML
    private Label cenaField;

    @FXML
    private Label quantitaColazioneField;

    @FXML
    private Label quantitaPranzoField;

    @FXML
    private Label quantitaCenaField;

    @FXML
    private Label day;
    @FXML
    private Label patientSelected;
    private String giorno;

    private String ilnesses;

    private NutritionalPlanDayBean nutritionalPlanDayBean;
    private ManageNutritionalController createNutritionalController;



    @FXML
    public TextField commandLine ;
    private static final String BACK="back";

    public ViewNutritionalPlan(){
        createNutritionalController = new ManageNutritionalController();
    }

    public void takeParameter(String dataSelected,String ilnesses,String email) throws NutritionalPlanNotFoundException, DuplicatedUserException {

        day.setText(dataSelected);
        patientSelected.setText(dataSelected);
        giorno = dataSelected;
        this.ilnesses=ilnesses;



        nutritionalPlanDayBean = createNutritionalController.displayNutritionalPlanDay(giorno,ilnesses,email);

        this.colazioneField.setText(nutritionalPlanDayBean.getColazione().getName());
        this.pranzoField.setText(nutritionalPlanDayBean.getPranzo().getName());
        this.cenaField.setText(nutritionalPlanDayBean.getCena().getName());
        this.quantitaColazioneField.setText(nutritionalPlanDayBean.getQuantitaColazione());
        this.quantitaPranzoField.setText(nutritionalPlanDayBean.getQuantitaPranzo());
        this.quantitaCenaField.setText(nutritionalPlanDayBean.getQuantitaCena());


    }


    @FXML
    public void onCommand(ActionEvent event) throws IOException, NotExistentUserException {
        String commandText = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");
        if(commandText.matches(BACK)){
            boolean type=Session.getSessionInstance().getLoggedUser().getType();
            if(type){
                GUI.switchPage(event,"/secondGui/nutritionist/NutritionalPlanDashboard.fxml");
            }else{
                GUI.switchPage(event,"/secondGui/patient/DashboardPlan.fxml");
            }
        }




    }

}
