package com.dicii.ispw.project.graphicalcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NutritionalPlanDashboard {
    private Stage stage;
    private Scene scene;

    private Parent root;
    public void createNutritionalPlan(ActionEvent event) throws Exception {


        root = FXMLLoader.load(getClass().getResource("nutritionist/NutritionalListUser.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // NutritionalPlanWeek day = new NutritionalPlanWeek();


    }

    public void deleteNutritionalPlan(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource(""));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void modifyNutritionalPlan(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource(""));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
