package com.dicii.ispw.project.firstview.patient;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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
            } catch (NotBoundException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}