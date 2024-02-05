package com.dicii.ispw.project.firstview.patient;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.beans.SubscriptionRequestBean;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ViewNutritionistController extends DashboardController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label nutritionistPrice;
    @FXML
    private Label nutritionistDetails;
    @FXML
    private Button subscribeButton;


    private final SubscribeToNutritionistController subscribeToNutritionistController;

    public ViewNutritionistController(){
        subscribeToNutritionistController = new SubscribeToNutritionistController();
    }
    public void initDetails(NutritionistBean nutritionistBean){
        nameLabel.setText(nutritionistBean.getName()+" "+nutritionistBean.getSurname());
        nutritionistPrice.setText(nutritionistBean.getCost());
        nutritionistDetails.setText(nutritionistBean.getDescription());
        subscribeButton.setOnAction(_ -> subscribeButtonHandler(nutritionistBean));
    }

    private void subscribeButtonHandler(NutritionistBean nutritionistBean){
        String subscriber = Session.getSessionInstance().getLoggedUser().getEmail();
        String nutritionist = nutritionistBean.getEmail();
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        SubscriptionRequestBean subscriptionRequestBean = new SubscriptionRequestBean(subscriber,nutritionist,dateTime);
        try {
            subscribeToNutritionistController.sendSubscriptionRequest(subscriptionRequestBean);
        }catch(RemoteException | NotBoundException e){
            System.out.println(e.getMessage());
        }
    }
}
