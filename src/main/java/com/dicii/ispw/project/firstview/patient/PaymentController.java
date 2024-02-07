package com.dicii.ispw.project.firstview.patient;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.exceptions.NotOnlineUserException;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

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
                subscribeToNutritionistController.subscribePatientToNutritionist(nutritionistBean);
                GUI.switchPage(event,"/firstGui/patient/dashboard/DashboardHome.fxml");
            } catch (NotOnlineUserException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage());
                alert.showAndWait();
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }
}
