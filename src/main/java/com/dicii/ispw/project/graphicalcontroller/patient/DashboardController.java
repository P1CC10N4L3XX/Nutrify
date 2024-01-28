package com.dicii.ispw.project.graphicalcontroller.patient;

import com.dicii.ispw.project.graphicalcontroller.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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