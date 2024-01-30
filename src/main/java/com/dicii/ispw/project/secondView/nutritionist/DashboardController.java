package com.dicii.ispw.project.secondView.nutritionist;

import com.dicii.ispw.project.applicationcontroller.CreateNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanBean;
import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.firstView.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DashboardController {
    @FXML
    public TextField commandLine;

    private NutritionalPlanBean nutritionalPlanBean;
    private CreateNutritionalController createNutritionalController;


    private static final String CREATE="Create Nutritional Plan";

    private static final String RECIPE="Add Recipe";

    public DashboardController(){
        createNutritionalController = new CreateNutritionalController();
    }

    @FXML
    public void onCommand(ActionEvent event) throws IOException, NotExistentUserException {

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

            } catch (DuplicatedUserException e) {
                throw new RuntimeException(e);
            }
            return ;
        }
        else if (commandText.matches(RECIPE)) {
            GUI.switchPage(event,"/SecondGui/nutritionist/RecipeView.fxml");
            return ;
        }


    }
}
