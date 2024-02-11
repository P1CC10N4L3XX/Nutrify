package com.dicii.ispw.project.secondview.patient;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.beans.NotificationBean;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.exceptions.NotExistentNotification;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NotificationController implements Initializable {
    @FXML
    ListView<String> acceptedListView;
    @FXML
    ListView<String> refusedListView;
    @FXML
    TextField commandLine;
    private List<NotificationBean> notificationAcceptedBeanList;
    private List<NotificationBean> notificationRefusedBeanList;

    private final SubscribeToNutritionistController subscribeToNutritionistController;

    public NotificationController(){
        subscribeToNutritionistController = new SubscribeToNutritionistController();
        notificationRefusedBeanList = new ArrayList<>();
        notificationAcceptedBeanList = new ArrayList<>();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            notificationAcceptedBeanList = subscribeToNutritionistController.getSubscriptionAcceptedNotifications();
            notificationRefusedBeanList = subscribeToNutritionistController.getSubscriptionRefusedNotifications();
        }catch (NotExistentNotification e){
            Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage());
            alert.showAndWait();
        }
        for(NotificationBean notificationAcceptedBean : notificationAcceptedBeanList){
            acceptedListView.getItems().addAll(notificationAcceptedBean.getSender());
        }
        for(NotificationBean notificationRefusedBean : notificationRefusedBeanList){
            refusedListView.getItems().addAll(notificationRefusedBean.getSender());
        }
    }
    public void onCommand(ActionEvent event) throws IOException {
        String commandText = commandLine.getText();
        commandLine.setText("");
        if(commandText.matches("pay .*")){
            String nutritionistEmail = commandText.replace("pay ","");
            subscribeToNutritionistController.pay(new NutritionistBean(nutritionistEmail));
        }else if(commandText.matches("home")){
            GUI.switchPage(event,"/secondGui/patient/PatientDashboard.fxml");
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING,"COMANDO NON RICONOSCIUTO");
            alert.showAndWait();
        }
    }
}
