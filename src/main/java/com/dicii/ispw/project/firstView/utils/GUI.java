package com.dicii.ispw.project.firstView.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GUI {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    public static void switchPage(ActionEvent event,String viewPath) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(GUI.class.getResource(viewPath)));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
