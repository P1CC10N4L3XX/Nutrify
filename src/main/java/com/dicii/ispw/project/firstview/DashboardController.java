package com.dicii.ispw.project.firstview;

import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {
    private Stage stage;
    private Scene scene;

    private Parent root;
    public void switchLoginFirst(ActionEvent event) throws IOException {

        GUI.switchPage(event,"/firstGui/Login.fxml");


    }
    public void switchLoginSecond(ActionEvent event) throws IOException {

        GUI.switchPage(event,"/secondGui/Registration.fxml");

    }



}
