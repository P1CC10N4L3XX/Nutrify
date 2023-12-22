package com.dicii.ispw.project.graphicalcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NutritionalPlanListController {

    private Stage stage;
    private Scene scene;

    private Parent root;

    public Button week1;


    public void week1(ActionEvent event) throws Exception {

        String wk = week1.getId();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/firstGui/Nutritionist/NutritionalPlanWeek.fxml"));

        root = loader.load();

        //scena 2
        NutritionalPlanWeek week = loader.getController();
        //inizializzo l'informazione
        week.showInformation(wk);

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        // stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }
}

