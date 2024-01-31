package com.dicii.ispw.project.secondView.nutritionist;
import com.dicii.ispw.project.applicationcontroller.CreateNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanDayBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.firstView.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class ViewNutritionalPlan {

    @FXML
    private Label colazione;

    @FXML
    private Label pranzo;

    @FXML
    private Label cena;

    @FXML
    private Label quantitaColazione;

    @FXML
    private Label quantitaPranzo;

    @FXML
    private Label quantitaCena;

    @FXML
    Label day;
    String giorno;

    String ilnesses;

    private NutritionalPlanDayBean nutritionalPlanDayBean;
    private CreateNutritionalController createNutritionalController;



    @FXML
    public TextField commandLine ;
    private static final String BACK="back";

    public ViewNutritionalPlan(){
        createNutritionalController = new CreateNutritionalController();
    }

    public void takeParameter(String dataSelected,String ilnesses) throws NutritionalPlanNotFoundException, DuplicatedUserException {

        day.setText(dataSelected);
        giorno = dataSelected;
        this.ilnesses=ilnesses;

        nutritionalPlanDayBean = createNutritionalController.displayNutritionalPlanDay(giorno,ilnesses);
        this.colazione.setText(nutritionalPlanDayBean.getColazione().getName());
        this.pranzo.setText(nutritionalPlanDayBean.getPranzo().getName());
        this.cena.setText(nutritionalPlanDayBean.getCena().getName());
        this.quantitaColazione.setText(nutritionalPlanDayBean.getQuantitaColazione());
        this.quantitaPranzo.setText(nutritionalPlanDayBean.getQuantitaPranzo());
        this.quantitaCena.setText(nutritionalPlanDayBean.getQuantitaCena());


    }


    @FXML
    public void onCommand(ActionEvent event) throws IOException, NotExistentUserException {
        String commandText = commandLine.getText() ;
        commandLine.setStyle(null);
        commandLine.setText("");
        if (commandText.matches(BACK)) {
            GUI.switchPage(event,"/secondGui/nutritionist/NutritionalPlanDay.fxml");
            return ;
        }


    }

}
