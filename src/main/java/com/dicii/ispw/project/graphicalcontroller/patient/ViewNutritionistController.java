package com.dicii.ispw.project.graphicalcontroller.patient;

import com.dicii.ispw.project.beans.NutritionistBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewNutritionistController extends DashboardController {
    @FXML
    private Label nameLabel;
    public void initDetails(NutritionistBean nutritionistBean){
        nameLabel.setText(nutritionistBean.getName()+" "+nutritionistBean.getSurname());
    }
}
