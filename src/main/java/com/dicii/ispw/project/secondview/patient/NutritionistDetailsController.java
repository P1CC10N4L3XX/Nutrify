package com.dicii.ispw.project.secondview.patient;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.beans.SubscriptionRequestBean;
import com.dicii.ispw.project.exceptions.DuplicatedNotificationException;
import com.dicii.ispw.project.exceptions.NotOnlineUserException;
import com.dicii.ispw.project.firstview.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NutritionistDetailsController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label costLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private TextField commandLine;

    SubscribeToNutritionistController subscribeToNutritionistController;

    public NutritionistDetailsController(){
        subscribeToNutritionistController = new SubscribeToNutritionistController();
    }
    public void showNutritionistDetails(NutritionistBean nutritionistBean){
        nameLabel.setText(nutritionistBean.getName()+" "+nutritionistBean.getSurname());
        costLabel.setText(nutritionistBean.getCost());
        descriptionLabel.setText(nutritionistBean.getDescription());
        commandLine.setOnAction(event-> {
            try {
                onCommand(nutritionistBean,event);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        });
    }
    private void onCommand(NutritionistBean nutritionistBean, ActionEvent event) throws IOException {
        String subscriber = Session.getSessionInstance().getLoggedUser().getEmail();
        String nutritionist = nutritionistBean.getEmail();
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if(commandLine.getText().equals("subscribeToNutritionist")){
            try {
                subscribeToNutritionistController.sendSubscriptionRequest(new SubscriptionRequestBean(subscriber,nutritionist,dateTime));
            } catch (NotOnlineUserException | DuplicatedNotificationException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING,e.getMessage());
                alert.showAndWait();
            }
        }else if(commandLine.getText().equals("home")){
            GUI.switchPage(event,"/secondGui/patient/PatientDashboard.fxml");
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING,"COMANDO NON RICONOSCIUTO");
            alert.showAndWait();
        }
    }
}
