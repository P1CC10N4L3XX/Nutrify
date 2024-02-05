package com.dicii.ispw.project.beans;



public class NotificationBean {
    private String sender;
    private String receiver;
    private String message;
    private String dateTime;



    public NotificationBean(String sender, String receiver, String dateTime, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.dateTime = dateTime;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


}
