package com.dicii.ispw.project.patterns.observer;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.beans.NotificationBean;
import com.dicii.ispw.project.firstview.obj.NotificationPopUpController;
import com.dicii.ispw.project.models.Notification;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NotificatorSystem extends UnicastRemoteObject implements Observer{
    SubscribeToNutritionistController subscribeToNutritionistController;
    public NotificatorSystem() throws RemoteException {
        super();
        subscribeToNutritionistController = new SubscribeToNutritionistController();
    }
    @Override
    public void update(Notification notification) throws RemoteException {
        subscribeToNutritionistController.showNotification(notification);
    }
}
