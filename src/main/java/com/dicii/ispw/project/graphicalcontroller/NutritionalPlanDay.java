package com.dicii.ispw.project.graphicalcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class NutritionalPlanDay {
    private Stage stage;
    private Scene scene;

    private Parent root;

    private String week;

    public Label client;


    public void showInformation(String week){

        client.setText(week);


    }



    public void day(ActionEvent event) throws Exception{

        root = FXMLLoader.load(getClass().getResource("/firstGui/nutritionist/CreateNutritionalPlan.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }










}
