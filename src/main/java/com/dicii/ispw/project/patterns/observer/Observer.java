package com.dicii.ispw.project.patterns.observer;

import com.dicii.ispw.project.models.Notification;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {

    void update(Notification notification) throws RemoteException;
}
