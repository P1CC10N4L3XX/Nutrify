package com.dicii.ispw.project.firstview.patient.obj;

import com.dicii.ispw.project.beans.NotificationBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RefusedCardController{
    @FXML
    private Label notificationSender;
    @FXML
    private Label notificationMessage;
    @FXML
    private Button okButton;

    public void initNotificationCard(NotificationBean notificationBean){
        notificationSender.setText(notificationBean.getSender());
        notificationMessage.setText(notificationBean.getMessage());
        okButton.setOnAction(event -> okButtonClick());
    }
    private void okButtonClick(){

        Pane cardBox = (Pane) okButton.getParent();
        VBox cardLayout = (VBox) cardBox.getParent();
        cardLayout.getChildren().remove(cardBox);
    }
}
