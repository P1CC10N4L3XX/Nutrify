package com.dicii.ispw.project.firstview.nutritionist;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanBean;
import com.dicii.ispw.project.firstview.utils.GUI;
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

    private ManageNutritionalController createNutritionalController;

    public NutritionalPlanDashboard(){
        createNutritionalController = new ManageNutritionalController();
    }

    public void createNutritionalPlan(ActionEvent event) throws Exception {

        GUI.switchPage(event,"/firstGui/nutritionist/NutritionalPlanDay.fxml");

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDateString = currentDate.format(formatter);
        nutritionalPlanBean = new NutritionalPlanBean(formattedDateString);
        createNutritionalController.createNutrutionalPlan(nutritionalPlanBean);

    }

    public void recipeDashBoard(ActionEvent event) throws Exception {

        GUI.switchPage(event,"/firstGui/nutritionist/RecipeView.fxml");

    }

}
