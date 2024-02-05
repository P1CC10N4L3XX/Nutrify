package com.dicii.ispw.project.firstview.nutritionist;

import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.event.ActionEvent;

import java.io.IOException;

public class DashboardController {

    public void switchHome(ActionEvent event) throws IOException {

        GUI.switchPage(event,"/firstGui/nutritionist/dashboard/DashboardHome.fxml");

    }

    public void switchRecipes(ActionEvent event) throws IOException {

        GUI.switchPage(event,"/firstGui/nutritionist/dashboard/DashboardRecipes.fxml");

    }
    public void switchPlan(ActionEvent event) throws IOException {
        GUI.switchPage(event,"/firstGui/nutritionist/dashboard/DashboardPlan.fxml");
    }

    public void switchNotificationArea(ActionEvent event) throws IOException {
        GUI.switchPage(event, "/firstGui/nutritionist/dashboard/NotificationArea.fxml");
    }
}
