package com.dicii.ispw.project.firstview.nutritionist.dashboard;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.beans.NotificationBean;
import com.dicii.ispw.project.exceptions.NotExistentNotification;
import com.dicii.ispw.project.firstview.nutritionist.obj.SubscriptionCardController;
import com.dicii.ispw.project.firstview.nutritionist.DashboardController;
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
    private VBox subscriptionRequestCardLayout;
    @FXML
    private Label errorLabel;
    private final SubscribeToNutritionistController subscribeToNutritionistController;
    public NotificationCenterController(){
        subscribeToNutritionistController = new SubscribeToNutritionistController();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            List<NotificationBean> subscriptionRequestNotificationBeanList = new ArrayList<>(subscribeToNutritionistController.getSubscriptionRequestNotifications());
            showSubscriptionRequestNotificationList(subscriptionRequestNotificationBeanList);
        }catch(NotExistentNotification e){
            errorLabel.setText(e.getMessage());
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }
    }
    private void showSubscriptionRequestNotificationList(List<NotificationBean> notificationBeanList) throws IOException{
        for(NotificationBean notificationBean : notificationBeanList){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/firstGui/notification/notificationCard/SubscriptionRequest.fxml"));
            Pane cardBox = loader.load();
            SubscriptionCardController subscriptionCardController = loader.getController();
            subscriptionCardController.initNotificationCard(notificationBean);
            subscriptionRequestCardLayout.getChildren().add(cardBox);

        }
    }
}
