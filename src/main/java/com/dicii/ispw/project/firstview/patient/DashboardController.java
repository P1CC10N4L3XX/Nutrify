package com.dicii.ispw.project.firstview.patient;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;


public class DashboardController {


    @FXML
    public void switchHome(ActionEvent event) throws IOException {
        GUI.switchPage(event,"/firstGui/patient/dashboard/DashboardHome.fxml");
    }
    public void switchPlan(ActionEvent event) throws IOException {
        GUI.switchPage(event,"/firstGui/patient/dashboard/DashboardPlan.fxml");
    }
    public void switchNotificationArea(ActionEvent event) throws IOException{
        GUI.switchPage(event,"/firstGui/patient/dashboard/NotificationArea.fxml");
    }

}