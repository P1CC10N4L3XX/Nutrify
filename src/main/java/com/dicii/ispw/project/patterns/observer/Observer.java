package com.dicii.ispw.project.patterns.observer;

import com.dicii.ispw.project.beans.NotificationBean;
import com.dicii.ispw.project.models.Notification;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {

    void update(Notification notificationBean) throws RemoteException;
}
