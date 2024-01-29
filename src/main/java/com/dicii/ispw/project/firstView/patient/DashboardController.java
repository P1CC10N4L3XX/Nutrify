package com.dicii.ispw.project.firstView.patient;
import com.dicii.ispw.project.firstView.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


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