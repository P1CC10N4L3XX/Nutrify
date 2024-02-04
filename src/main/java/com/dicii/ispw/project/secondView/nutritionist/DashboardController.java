package com.dicii.ispw.project.secondView.nutritionist;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanBean;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DashboardController {
    @FXML
    private TextField commandLine;

    private NutritionalPlanBean nutritionalPlanBean;
    private ManageNutritionalController createNutritionalController;


    private static final String CREATE="Create Nutritional Plan";

    private static final String RECIPE="Add Recipe";

    public DashboardController(){
        createNutritionalController = new ManageNutritionalController();
    }

    @FXML
    public void onCommand(ActionEvent event) throws Exception {

        String commandText = commandLine.getText() ;

        commandLine.setStyle(null);
        commandLine.setText("");
        if (commandText.matches(CREATE)) {
            GUI.switchPage(event,"/SecondGui/nutritionist/NutritionalPlanDay.fxml");
            try {
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDateString = currentDate.format(formatter);
                nutritionalPlanBean = new NutritionalPlanBean(formattedDateString);

                createNutritionalController.createNutrutionalPlan(nutritionalPlanBean);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        else if (commandText.matches(RECIPE)) {
            GUI.switchPage(event,"/SecondGui/nutritionist/RecipeView.fxml");

        }


    }
}
