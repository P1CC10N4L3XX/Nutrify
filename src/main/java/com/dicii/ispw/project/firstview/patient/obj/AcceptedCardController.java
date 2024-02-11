package com.dicii.ispw.project.firstview.patient.obj;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.beans.NotificationBean;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.firstview.patient.PaymentController;
import com.dicii.ispw.project.firstview.patient.ViewNutritionistController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AcceptedCardController {
    @FXML
    private Label notificationSender;
    @FXML
    private Label notificationMessage;
    @FXML
    private Button paymentButton;

    public void initNotificationCard(NotificationBean notificationBean){
        SubscribeToNutritionistController subscribeToNutritionistController = new SubscribeToNutritionistController();
        notificationSender.setText(notificationBean.getSender());
        notificationMessage.setText(notificationBean.getReceiver());
        NutritionistBean nutritionistBean = new NutritionistBean(notificationBean.getSender());
        paymentButton.setOnAction(_ -> subscribeToNutritionistController.pay(nutritionistBean));
    }

}
