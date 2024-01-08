package com.dicii.ispw.project.patterns.singleton;

import com.dicii.ispw.project.beans.UserBean;

public class Session {
    private UserBean loggedUser;
    private static Session sessionInstance;

    private Session(){
        loggedUser = null;
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
}
