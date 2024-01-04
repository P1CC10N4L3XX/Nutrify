package com.dicii.ispw.project.graphicalcontroller.patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardController {



    private Stage stage;
    private Scene scene;
    private Parent root;




    @FXML
    public void switchHome(ActionEvent event) throws Exception {


        root = FXMLLoader.load(getClass().getResource("/firstGui/patient/dashboard/DashboardHome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchRecipes(ActionEvent event) throws Exception {


        root = FXMLLoader.load(getClass().getResource("/firstGui/patient/dashboard/DashboardRecipes.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void nutritionalPlanDashboard(ActionEvent event) throws Exception {


        root = FXMLLoader.load(getClass().getResource("/firstGui/nutritionist/NutritionalPlanDashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void switchPlan(ActionEvent event) throws Exception {


        root = FXMLLoader.load(getClass().getResource("/firstGui/patient/dashboard/DashboardPlan.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void viewDetails(ActionEvent event) throws Exception{
        root = FXMLLoader.load(getClass().getResource("/firstGui/patient/ViewNutrizionista.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}