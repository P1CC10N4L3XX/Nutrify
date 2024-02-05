package com.dicii.ispw.project.beans;

public class SubscriptionRequestBean {
    private String subscriber;
    private String nutritionist;

    private String dateTime;

    public SubscriptionRequestBean(String subscriber, String nutritionist,String dateTime){
        this.subscriber = subscriber;
        this.nutritionist = nutritionist;
        this.dateTime = dateTime;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public void setNutritionist(String nutritionist) {
        this.nutritionist = nutritionist;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getNutritionist() {
        return nutritionist;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public String getDateTime() {
        return dateTime;
    }
}
