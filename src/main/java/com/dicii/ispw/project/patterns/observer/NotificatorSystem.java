package com.dicii.ispw.project.patterns.observer;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.models.Notification;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class NotificatorSystem extends UnicastRemoteObject implements Observer{
    public NotificatorSystem() throws RemoteException {
        super();
    }

    @Override
    public void update(Notification notification) throws RemoteException {
        SubscribeToNutritionistController subscribeToNutritionistController = new SubscribeToNutritionistController();
        subscribeToNutritionistController.showNotificationPopUp(notification);
    }
}
