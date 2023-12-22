package com.dicii.ispw.project.graphicalcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class FirstViewController {
    private Stage stage;
    private Scene scene;

    private Parent root;
    public void switchLoginFirst(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource("/firstGui/Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchLoginSecond(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource("/secondGui/Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



}
