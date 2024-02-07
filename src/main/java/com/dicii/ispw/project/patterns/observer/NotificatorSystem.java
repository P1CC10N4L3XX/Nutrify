package com.dicii.ispw.project.patterns.observer;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.models.Notification;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class NotificatorSystem extends UnicastRemoteObject implements Observer{
    private final SubscribeToNutritionistController subscribeToNutritionistController;
    public NotificatorSystem() throws RemoteException {
        subscribeToNutritionistController = new SubscribeToNutritionistController();
    }
    @Override
    public void update(Notification notification) throws RemoteException {
        subscribeToNutritionistController.showNotificationPopUp(notification);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        NotificatorSystem other = (NotificatorSystem) obj;
        return Objects.equals(subscribeToNutritionistController, other.subscribeToNutritionistController);
    }
}
