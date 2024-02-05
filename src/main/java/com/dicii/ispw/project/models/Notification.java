package com.dicii.ispw.project.models;

import java.io.Serializable;

public class Notification implements Serializable {
    private User sender;
    private User receiver;
    private String message;
    private String dateTime;
    private String type;
    public Notification(User sender, User receiver, String dateTime, String message,String type){
        this.sender = sender;
        this.receiver = receiver;
        this.dateTime = dateTime;
        this.message = message;
        this.type = type;
    }
    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
