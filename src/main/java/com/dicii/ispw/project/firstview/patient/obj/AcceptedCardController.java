package com.dicii.ispw.project.firstview.patient.obj;

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
        notificationSender.setText(notificationBean.getSender());
        notificationMessage.setText(notificationBean.getReceiver());
        NutritionistBean nutritionistBean = new NutritionistBean(notificationBean.getSender());
        paymentButton.setOnAction(event -> paymentButtonClick(nutritionistBean,event));
    }
    private void paymentButtonClick(NutritionistBean nutritionistBean, ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/firstgui/patient/Payment.fxml"));
            Parent root = loader.load();
            PaymentController paymentController = loader.getController();
            paymentController.initPaymentArea(nutritionistBean);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}
