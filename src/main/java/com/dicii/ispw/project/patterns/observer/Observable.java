package com.dicii.ispw.project.patterns.observer;

import com.dicii.ispw.project.beans.NotificationBean;
import com.dicii.ispw.project.models.Notification;
import com.dicii.ispw.project.models.User;

import java.rmi.RemoteException;

public interface Observable {
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notify(Notification notification) throws RemoteException;
}
