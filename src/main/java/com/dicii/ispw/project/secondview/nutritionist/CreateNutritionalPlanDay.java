package com.dicii.ispw.project.secondview.nutritionist;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanDayBean;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateNutritionalPlanDay implements Initializable {


    private List<RecipeBean> list;
    private ManageNutritionalController createNutritionalController;
    @FXML
    private ListView<String> myListView;

    @FXML
    private TextField commandLine ;

    @FXML
    private TextField breakfast;

    @FXML
    private TextField launch;

    @FXML
    private TextField dinner;

    @FXML
    private TextField quantityb;

    @FXML
    private TextField quantityl;

    @FXML
    private TextField quantityd;

    @FXML
    private Label data;

    private String email="dhb";


    private Alert completeAlert;

    private NutritionalPlanDayBean nutritionalPlanDayBean;

    private static final String SUBMIT="submit";

    private static final String BACK="back";

    private static final String SET_BREAKFAST="set breakfast .*";

    private static final String SET_LAUNCH="set launch .*";

    private static final String SET_DINNER="set dinner .*";

    private static final String SET_QUANTITYB="set quantityb .*";

    private static final String SET_QUANTITYL="set quantityl .*";

    private static final String SET_QUANTITYD="set quantityd .*";


    public CreateNutritionalPlanDay(){
        createNutritionalController = new ManageNutritionalController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            list = createNutritionalController.displayRecipe();
        } catch (DuplicatedUserException e) {
            e.printStackTrace();
            System.exit(0);
        }
        for (RecipeBean recipe : list) {

            myListView.getItems().addAll(String.valueOf(recipe.getName()));

        }

    }

    public void displayData(String dataValue){

        if(dataValue!=null){
            data.setText(dataValue);
        }


    }

    public void onCommand(ActionEvent event) throws IOException {

        completeAlert = new Alert(Alert.AlertType.WARNING, "Recipe not found") ;

        String commandText = commandLine.getText();
        commandLine.setStyle(null);
        commandLine.setText("");
        if (commandText.matches(SET_BREAKFAST)) {
            String breakfastName = commandText.replace("set breakfast ", "");
            if(checkRecipe(breakfastName)){
                breakfast.setText(breakfastName);
            }else{
                completeAlert.showAndWait() ;
            }

        } else if (commandText.matches(SET_LAUNCH)) {
            String launchName = commandText.replace("set launch ", "");
            if(checkRecipe(launchName)){
                launch.setText(launchName);
            }else{
                completeAlert.showAndWait() ;
            }

        }
        else if (commandText.matches(SET_DINNER)) {
            String dinnerName = commandText.replace("set dinner ", "");
            if(checkRecipe(dinnerName)){
                dinner.setText(dinnerName);
            }else{
                completeAlert.showAndWait() ;
            }


        }
        else if (commandText.matches(SET_QUANTITYB)) {
            String quantityB = commandText.replace("set quantityb ", "");
            quantityb.setText(quantityB);

        }
        else if (commandText.matches(SET_QUANTITYL)) {
            String quantityL = commandText.replace("set quantityl ", "");
            quantityl.setText(quantityL);

        }
        else if (commandText.matches(SET_QUANTITYD)) {
            String quantityD = commandText.replace("set quantityd ", "");
            quantityd.setText(quantityD);

        } else if (commandText.matches(BACK)) {
            GUI.switchPage(event,"/secondGui/nutritionist/NutritionalPlanDashboard.fxml");

        }

        else if (commandText.matches(SUBMIT)) {

            if(quantityb.getText().isEmpty()||quantityd.getText().isEmpty() || quantityl.getText().isEmpty() || launch.getText().isEmpty() || dinner.getText().isEmpty() || breakfast.getText().isEmpty()) {
                completeAlert = new Alert(Alert.AlertType.WARNING, "Compile all fields") ;
                completeAlert.showAndWait() ;
            }else{
                try {

                    nutritionalPlanDayBean= new NutritionalPlanDayBean(data.getText(),convertStringToReciBean(breakfast.getText()),convertStringToReciBean(launch.getText()),convertStringToReciBean(dinner.getText()),quantityb.getText(),quantityd.getText(),quantityl.getText());
                    createNutritionalController.sendNutritionalPlanDay(nutritionalPlanDayBean,email);

                }

                catch(Exception e){
                    e.printStackTrace();
                    System.exit(0);
                }


            }


        }

    }

    public boolean checkRecipe(String recipeName){
        boolean a=false;

        for (RecipeBean recipe : list) {
            if(recipeName.matches(recipe.getName())){
                a=true;
                return a;
            }
        }

        return a;
    }

    public RecipeBean convertStringToReciBean(String ricetta) {
        RecipeBean recipeBean = new RecipeBean();
        recipeBean.setName(ricetta);
        return recipeBean;
    }


}
