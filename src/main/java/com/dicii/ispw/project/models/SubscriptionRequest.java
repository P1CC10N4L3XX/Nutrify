package com.dicii.ispw.project.models;

public class SubscriptionRequest {
    private Patient subscriber;
    private Nutritionist nutritionist;
    private String dateTime;

    public SubscriptionRequest(Patient subscriber, Nutritionist nutritionist,String dateTime){
        this.subscriber = subscriber;
        this.nutritionist = nutritionist;
        this.dateTime = dateTime;
    }

    public Nutritionist getNutritionist() {
        return nutritionist;
    }

    public Patient getSubscriber() {
        return subscriber;
    }

    public void setNutritionist(Nutritionist nutritionist) {
        this.nutritionist = nutritionist;
    }

    public void setSubscriber(Patient subscriber) {
        this.subscriber = subscriber;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
