package models;

import java.util.ArrayList;
import java.util.List;

public abstract class NutritionalPlanBase implements NutritionalPlan{

    private int id;
    private List<NutritionalPlanDay> nutritionalPlanDayList;

    public NutritionalPlanBase(int id){
        this();
        this.id = id;
    }

    public NutritionalPlanBase(){
        this.nutritionalPlanDayList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<NutritionalPlanDay> getNutritionalPlanBaseDayList() {
        return nutritionalPlanDayList;
    }

    public void addAllWorkoutDays(List<NutritionalPlanDay> nutritionalPlanDayList) {
        this.nutritionalPlanDayList = new ArrayList<>();
        for (NutritionalPlanDay nutritionalPlanDay : nutritionalPlanDayList) {
            NutritionalPlanDay newNutritionalPlanDay = new NutritionalPlanDay(nutritionalPlanDay.getDay());
            newNutritionalPlanDay.addAllRecipe(nutritionalPlanDay.getRecipeList());
            addNutritionalPlanDay(newNutritionalPlanDay);
        }
    }

    public void addNutritionalPlanDay(NutritionalPlanDay nutritionalPlanDay) {
        nutritionalPlanDayList.add(nutritionalPlanDay);
    }

    public void removeNutritionalPlanDay(NutritionalPlanDay nutritionalPlanDay) {
        nutritionalPlanDayList.remove(nutritionalPlanDay);
    }
}
