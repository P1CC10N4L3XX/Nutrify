package com.dicii.ispw.project.models;

public class SubscriptionRequest {
    private Patient subscriber;
    private Nutritionist nutritionist;
    private String dateSubscription;
    public SubscriptionRequest(Patient subscriber, Nutritionist nutritionist,String dateSubscription){
        this.subscriber = subscriber;
        this.nutritionist = nutritionist;
        this.dateSubscription = dateSubscription;
    }

    public void setDateSubscription(String dateSubscription) {
        this.dateSubscription = dateSubscription;
    }
    public String getDateSubscription(){
        return this.dateSubscription;
    }

}
