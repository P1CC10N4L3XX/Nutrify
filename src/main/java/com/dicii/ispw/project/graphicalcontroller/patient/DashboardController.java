package com.dicii.ispw.project.graphicalcontroller.patient;

import com.dicii.ispw.project.applicationcontroller.CreateNutritionalController;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.graphicalcontroller.ViewNutritionalPlan;
import com.dicii.ispw.project.graphicalcontroller.utils.GUI;
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

public class DashboardController {


    @FXML
    public void switchHome(ActionEvent event) throws Exception {
        GUI.switchPage(event,"/firstGui/patient/dashboard/DashboardHome.fxml");
    }

    public void switchRecipes(ActionEvent event) throws Exception {

        GUI.switchPage(event,"/firstGui/patient/dashboard/DashboardRecipes.fxml");

    }
    public void switchPlan(ActionEvent event) throws Exception {
        GUI.switchPage(event,"/firstGui/patient/dashboard/DashboardPlan.fxml");
    }


}