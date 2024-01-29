package com.dicii.ispw.project.graphicalcontroller;

import com.dicii.ispw.project.applicationcontroller.CreateNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDateString = currentDate.format(formatter);
            nutritionalPlanBean = new NutritionalPlanBean(formattedDateString);

            createNutritionalController.createNutrutionalPlan(nutritionalPlanBean);

        }

        catch(Exception e){

            System.out.println(e.getMessage());
        }

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
