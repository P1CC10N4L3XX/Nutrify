package com.dicii.ispw.project.graphicalcontroller;

import com.dicii.ispw.project.applicationcontroller.CreateNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanDayBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanFounded;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.dicii.ispw.project.applicationcontroller.CreateNutritionalController;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

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



    private NutritionalPlanDayBean nutritionalPlanDayBean;
    private CreateNutritionalController createNutritionalController;



    public  ViewNutritionalPlan(){
        createNutritionalController = new CreateNutritionalController();
    }



    public void takeParameter(String dataSelected) throws NutritionalPlanNotFoundException, DuplicatedUserException {
        if (giorno == null) {
            day.setText(dataSelected);
            giorno = dataSelected;

            getDataForDisplaying();

        }
    }





    public void getDataForDisplaying() throws NutritionalPlanNotFoundException, DuplicatedUserException {

            nutritionalPlanDayBean = createNutritionalController.displayNutritionalPlanDay(giorno);
            displayNutritionalPlan(nutritionalPlanDayBean);

    }






    public void displayNutritionalPlan(NutritionalPlanDayBean nutritionalPlanDayBean){


                this.colazione.setText(nutritionalPlanDayBean.getColazione());
                this.pranzo.setText(nutritionalPlanDayBean.getPranzo());
                this.cena.setText(nutritionalPlanDayBean.getCena());
                this.quantitaColazione.setText(nutritionalPlanDayBean.getQuantitaColazione());
                this.quantitaPranzo.setText(nutritionalPlanDayBean.getQuantitaPranzo());
                this.quantitaCena.setText(nutritionalPlanDayBean.getQuantitaCena());


    }


}



