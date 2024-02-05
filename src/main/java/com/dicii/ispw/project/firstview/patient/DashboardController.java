package com.dicii.ispw.project.firstview.patient;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;


public class DashboardController {


    @FXML
    public void switchHome(ActionEvent event) throws IOException {
        try{
            GUI.switchPage(event,"/firstGui/patient/dashboard/DashboardHome.fxml");
        }catch (Exception e){
            throw e;
        }

    }

    public void switchRecipes(ActionEvent event) throws Exception {

        GUI.switchPage(event,"/firstGui/patient/dashboard/DashboardRecipes.fxml");

    }
    public void switchPlan(ActionEvent event) throws Exception {
        GUI.switchPage(event,"/firstGui/patient/dashboard/DashboardPlan.fxml");
    }

    public void switchNotificationArea(ActionEvent event) throws Exception{
        GUI.switchPage(event,"/firstGui/patient/dashboard/NotificationArea.fxml");
    }

}