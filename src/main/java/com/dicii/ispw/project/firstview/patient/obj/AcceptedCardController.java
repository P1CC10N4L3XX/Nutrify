package com.dicii.ispw.project.firstview.patient.obj;

import com.dicii.ispw.project.beans.NotificationBean;
import com.dicii.ispw.project.beans.NutritionistBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AcceptedCardController {
    @FXML
    private Label notificationSender;
    @FXML
    private Label notificationMessage;
    @FXML
    private Button paymentButton;

    public void initNotificationCard(NotificationBean notificationBean){
        notificationSender.setText(notificationBean.getSender());
        notificationMessage.setText(notificationBean.getReceiver());
        NutritionistBean nutritionistBean = new NutritionistBean(notificationBean.getSender());
        paymentButton.setOnAction(event -> paymentButtonClick(nutritionistBean));
    }
    public void paymentButtonClick(NutritionistBean nutritionistBean){
        System.out.println(nutritionistBean.getEmail());
    }
}
