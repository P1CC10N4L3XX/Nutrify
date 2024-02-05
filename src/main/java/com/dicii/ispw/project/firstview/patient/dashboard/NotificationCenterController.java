package com.dicii.ispw.project.firstview.patient.dashboard;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.exceptions.NotExistentNotification;
import com.dicii.ispw.project.beans.NotificationBean;
import com.dicii.ispw.project.firstview.patient.DashboardController;
import com.dicii.ispw.project.firstview.patient.obj.AcceptedCardController;
import com.dicii.ispw.project.firstview.patient.obj.RefusedCardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NotificationCenterController extends DashboardController implements Initializable {
    @FXML
    private VBox subscriptionAcceptedCardLayout;
    @FXML
    private VBox subscriptionRefusedCardLayout;
    @FXML
    private Label errorLabel;
    private final SubscribeToNutritionistController subscribeToNutritionistController;

    public NotificationCenterController(){
        subscribeToNutritionistController = new SubscribeToNutritionistController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            List<NotificationBean> subscriptionAcceptedNotificationBeanList = new ArrayList<>(subscribeToNutritionistController.getSubscriptionAcceptedNotifications());
            showSubscriptionAcceptedNotificationList(subscriptionAcceptedNotificationBeanList);
            List<NotificationBean> subscriptionRefusedNotificationBeanList = new ArrayList<>(subscribeToNutritionistController.getSubscriptionRefusedNotifications());
            showSubscriptionRefusedNotificationList(subscriptionRefusedNotificationBeanList);
        }catch(NotExistentNotification e){
            errorLabel.setText(e.getMessage());
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private void showSubscriptionAcceptedNotificationList(List<NotificationBean> notificationBeanList) throws IOException{
        for(NotificationBean notificationBean : notificationBeanList){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/firstGui/notification/notificationCard/AcceptedRequest.fxml"));
            Pane cardBox = loader.load();
            AcceptedCardController acceptedCardController = loader.getController();
            acceptedCardController.initNotificationCard(notificationBean);
            subscriptionAcceptedCardLayout.getChildren().add(cardBox);
        }
    }

    private void showSubscriptionRefusedNotificationList(List<NotificationBean> notificationBeanList) throws IOException{
        for(NotificationBean notificationBean : notificationBeanList){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/firstGui/notification/notificationCard/RefusedRequest.fxml"));
            Pane cardBox = loader.load();
            RefusedCardController refusedCardController = loader.getController();
            refusedCardController.initNotificationCard(notificationBean);
            subscriptionRefusedCardLayout.getChildren().add(cardBox);
        }
    }
}
