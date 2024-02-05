package com.dicii.ispw.project.firstview.obj;

import com.dicii.ispw.project.beans.NotificationBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class NotificationPopUpController {
    @FXML
    private Label notificationSource;
    @FXML
    private Label notificationMessage;
    @FXML
    private Label notificationDateTime;
    @FXML
    private Button buttonCloseNotification;
    public void initNotification(NotificationBean notificationBean,Stage stage) throws IOException {
        notificationSource.setText(notificationBean.getSender());
        notificationMessage.setText(notificationBean.getMessage());
        notificationDateTime.setText(notificationBean.getDateTime());
        buttonCloseNotification.setOnAction(event -> stage.close());
    }
}
