package com.dicii.ispw.project.models;

import com.dicii.ispw.project.models.record.Card;
import com.dicii.ispw.project.models.record.Credentials;
import com.dicii.ispw.project.models.record.PersonalInfo;

import java.time.LocalDate;
import java.time.YearMonth;

public class Patient extends User {

    private Card card;
    private float weight;
    private float height;

    private Ilnesses ilnesses;

    private Nutritionist nutritionist;

    private NutritionalPlanBase nutritionalPlan;



    public Patient(String username, PersonalInfo personalInfo, Credentials credentials) {
        super(username, personalInfo, credentials);
        card = new Card(
                "",
                null
        );
    }


    public  Patient(String email, PersonalInfo personalInfo, Credentials credentials, Card card,  float weight, float height, Ilnesses ilnesses)  {
        super(email, personalInfo, credentials);

        this.card = card;
        this.weight=weight;
        this.height=height;
        this.ilnesses=ilnesses;
    }






    public float getWeight() {
        return this.weight;
    }

    public float getHeight() {
        return this.height;
    }

    public NutritionalPlanBase getNutritionalPlanBase() {
        return nutritionalPlan;
    }

    public Nutritionist getNutritionist() {
        return nutritionist;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }


}
