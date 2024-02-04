package test;

import com.dicii.ispw.project.models.NutritionalPlanDay;
import com.dicii.ispw.project.models.Recipe;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class InsertRecipetoNutritionalpLan {

    /**Author of the test:  Luca Cupellaro
     *                  Matricola 0307070
     */

    /**
     Il seguente test verifica se l'aggiunta di 3 ricicette  al NutritionalPlanDay venga
     effettuata correttamente e ritorna il valore aspettato.
     */

    Recipe expectedColazione = new Recipe("Biscotti", "molto dolci", "zucchero");
    Recipe expectedPranzo = new Recipe("Pasta", "binca", "carboedrati");
    Recipe expectedCena = new Recipe("Carne", "senza grasso", "carne di bovino");

    @Test
    public void getRecipeFromNutritionalPLan() {

        // Setup

        NutritionalPlanDay nutritionalPlanDay = new NutritionalPlanDay("", expectedColazione, expectedPranzo, expectedCena, "34", "56", "100","pasta con poco sale");

        Recipe actualColazione = nutritionalPlanDay.getColazione();
        Recipe actualPranzo = nutritionalPlanDay.getPranzo();
        Recipe actualCena = nutritionalPlanDay.getCena();

        assertEquals(expectedColazione.getName(), actualColazione.getName());
        assertEquals(expectedPranzo.getName(), actualPranzo.getName());
        assertEquals(expectedCena.getName(), actualCena.getName());
    }




}
