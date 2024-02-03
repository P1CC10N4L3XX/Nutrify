package com.dicii.ispw.project.firstView;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanDayBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.firstView.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class ViewNutritionalPlan  {

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
    private ManageNutritionalController createNutritionalController;



    public ViewNutritionalPlan(){
        createNutritionalController = new ManageNutritionalController();
    }

    public void back(ActionEvent event) throws IOException {
        GUI.switchPage(event,"/firstGui/nutritionist/NutritionalPlanDay.fxml");
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



}





