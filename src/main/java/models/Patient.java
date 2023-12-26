package models;

import models.record.Card;
import models.record.Credentials;
import models.record.PersonalInfo;

public class Patient extends User {

    private NutritionalPlan plan;
    private Nutritionist nutritionist;

    private Card card;

    public Patient(String username, PersonalInfo personalInfo, Credentials credentials){
        super(username, personalInfo, credentials);
        card = new Card(
                "",
                null
        );
    }

    public Nutritionist getNutritionist() {
        return nutritionist;
    }

    public void setNutritionist(Nutritionist nutritionist) {
        this.nutritionist = nutritionist;
    }
}
