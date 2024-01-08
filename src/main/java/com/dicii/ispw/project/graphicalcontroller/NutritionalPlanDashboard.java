package com.dicii.ispw.project.graphicalcontroller;

import com.dicii.ispw.project.applicationcontroller.CreateNewRecipeController;
import com.dicii.ispw.project.applicationcontroller.CreateNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanBean;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.models.NutritionalPlanBase;
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

    private NutritionalPlanBean nutritionalPlanBean;

    private CreateNutritionalController createNutritionalController;

    public NutritionalPlanDashboard(){
        createNutritionalController = new CreateNutritionalController();
    }
    public void createNutritionalPlan(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource("/firstGui/nutritionist/NutritionalPlanDay.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        try {
            nutritionalPlanBean = new NutritionalPlanBean("Piano","12/34/67");

            createNutritionalController.createNutrutionalPlan(nutritionalPlanBean);


        }

        catch(Exception e){

            System.out.println(e.getMessage());
        }

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

    public void recipeDashBoard(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource("/firstGui/nutritionist/RecipeView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
