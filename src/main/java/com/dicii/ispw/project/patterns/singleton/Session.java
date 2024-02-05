package com.dicii.ispw.project.patterns.singleton;

import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.patterns.observer.NotificatorSystem;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Session {
    private UserBean loggedUser;
    private NotificatorSystem notificatorSystem;

    private int[] port;
    private static Session sessionInstance;

    private Session(){
        loggedUser = null;
        notificatorSystem = null;
        port= new int[]{0, 0};
    }

    public synchronized static Session getSessionInstance(){
        if(Session.sessionInstance == null){
            Session.sessionInstance = new Session();
        }
        return sessionInstance;
    }

    public UserBean getLoggedUser(){
        return loggedUser;
    }

    public void setLoggedUser(UserBean loggedUser) {
        this.loggedUser = loggedUser;
    }

    public int[] getPort() {
        return port;
    }

    public void initNotificatorSystem() {
        if(loggedUser.getType()){
            port = new int[]{1888, 1889};
        }else{
            port = new int[]{1889,1888};
        }
        try{
            notificatorSystem = new NotificatorSystem();
            Registry registry = LocateRegistry.createRegistry(port[0]);
            registry.rebind(loggedUser.getEmail(),notificatorSystem);
        }catch(RemoteException e){
            System.out.println(e.getMessage());
        }
    }
    public NotificatorSystem getNotificatorSystem() {
        return notificatorSystem;
    }
}
