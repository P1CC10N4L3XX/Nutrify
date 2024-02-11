package com.dicii.ispw.project.firstview.patient;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.exceptions.NotOnlineUserException;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class PaymentController{
    @FXML
    private Button payButton;

    private final SubscribeToNutritionistController subscribeToNutritionistController;

    public PaymentController(){
        subscribeToNutritionistController = new SubscribeToNutritionistController();
    }
    public void initPaymentArea(NutritionistBean nutritionistBean) {
        payButton.setOnAction(event -> {
            try {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                subscribeToNutritionistController.subscribePatientToNutritionist(nutritionistBean);
            } catch (NotOnlineUserException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage());
                alert.showAndWait();
            }
        });
    }
}
