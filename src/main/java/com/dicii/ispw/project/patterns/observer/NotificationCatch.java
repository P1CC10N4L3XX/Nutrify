package com.dicii.ispw.project.patterns.observer;


import com.dicii.ispw.project.models.Notification;
import com.dicii.ispw.project.models.User;
import com.dicii.ispw.project.patterns.singleton.Session;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

public class NotificationCatch implements Observable{
    private final List<Observer> observers = new ArrayList<>();
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(Notification notification) throws RemoteException {
        for(Observer observer : observers){
            observer.update(notification);
        }
    }

    public void notifyUser(Notification notification,User ... usersDestination) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(Session.getSessionInstance().getPort()[1]);
        for(User userDestination : usersDestination) {
            addObserver((Observer) registry.lookup(userDestination.getEmail()));
        }
        notify(notification);
    }

}
