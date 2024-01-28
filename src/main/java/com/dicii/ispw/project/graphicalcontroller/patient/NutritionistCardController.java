package com.dicii.ispw.project.graphicalcontroller.patient;

import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.graphicalcontroller.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NutritionistCardController {
    @FXML
    private Label nutritionistName;
    @FXML
    private Label nutritionistPrice;
    @FXML
    private Button buttonDetails;

    public void initCard(NutritionistBean nutritionist){
        nutritionistName.setText(nutritionist.getName()+" "+nutritionist.getSurname());
        nutritionistPrice.setText(nutritionist.getCost());
        buttonDetails.setOnAction(event -> viewNutritionistDetails(nutritionist,event));
    }
    public void viewNutritionistDetails(NutritionistBean nutritionist, ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/firstgui/patient/nutritionistdetails/ViewNutrizionista.fxml"));
            Parent root = loader.load();
            ViewNutritionistController viewNutritionistController = loader.getController();
            viewNutritionistController.initDetails(nutritionist);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
