package com.dicii.ispw.project.patterns.observer;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.models.Notification;

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
