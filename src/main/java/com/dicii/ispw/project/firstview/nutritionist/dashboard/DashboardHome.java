package com.dicii.ispw.project.firstview.nutritionist.dashboard;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanBean;
import com.dicii.ispw.project.firstview.nutritionist.DashboardController;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.event.ActionEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DashboardHome extends DashboardController {
    private NutritionalPlanBean nutritionalPlanBean;

    private ManageNutritionalController createNutritionalController;

    public DashboardHome(){
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
