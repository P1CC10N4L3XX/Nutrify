package com.dicii.ispw.project.firstview.nutritionist.obj;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.beans.NotificationBean;
import com.dicii.ispw.project.exceptions.DuplicatedNotificationException;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotOnlineUserException;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SubscriptionCardController {
    @FXML
    private Label notificationSender;
    @FXML
    private Label notificationMessage;
    @FXML
    private Button acceptButton;
    @FXML
    private Button refuseButton;

    private final SubscribeToNutritionistController subscribeToNutritionistController;

    public SubscriptionCardController(){
        subscribeToNutritionistController = new SubscribeToNutritionistController();
    }

    public void initNotificationCard(NotificationBean notificationBean){
        notificationSender.setText(notificationBean.getSender());
        notificationMessage.setText(notificationBean.getMessage());
        String destination = notificationBean.getSender();
        acceptButton.setOnAction(_ -> acceptButtonClick(destination));
        refuseButton.setOnAction(_ -> refuseButtonClick(destination));
    }
    public void acceptButtonClick(String patientEmail){
        String sender = Session.getSessionInstance().getLoggedUser().getEmail();
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        NotificationBean notificationBean = new NotificationBean(sender,patientEmail,dateTime,"Subscription request accepted");
        try {
            subscribeToNutritionistController.acceptSubscriptionRequest(notificationBean);
        }catch(NotOnlineUserException | DuplicatedNotificationException e){
            Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage());
            alert.showAndWait();
        }
        removeCardBox();
    }

    public void refuseButtonClick(String patientEmail){
        String sender = Session.getSessionInstance().getLoggedUser().getEmail();
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        NotificationBean notificationBean = new NotificationBean(sender, patientEmail, dateTime,"Subscription request refused");
        try{
            subscribeToNutritionistController.refuseSubscriptionRequest(notificationBean);
        }catch(NotOnlineUserException | DuplicatedNotificationException e){
            Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage());
            alert.showAndWait();
        }
        removeCardBox();
    }

    private void removeCardBox(){
        Pane cardBox = (Pane) acceptButton.getParent();
        VBox cardLayout = (VBox) cardBox.getParent();
        cardLayout.getChildren().remove(cardBox);
    }


}
