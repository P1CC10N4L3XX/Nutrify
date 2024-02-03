package com.dicii.ispw.project.secondView.nutritionist;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.firstView.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;


public class AddNewRecipe {

    private ManageNutritionalController createNewRecipeController;

    public AddNewRecipe(){
        createNewRecipeController = new ManageNutritionalController();
    }

    private static final String BACK="back";
    private static final String SUBMIT="submit";
    private static final String DESCRIPTIONS="set description .*";
    private static final String SET_NAME="set name .*";
    private static final String SET_INGREDIENTS="set ingredients .*";

    @FXML
    public TextField commandLine;
    @FXML
    public TextField name;
    @FXML
    public TextField ingredients;
    @FXML
    public TextField descriptions;

    private RecipeBean recipeBean;




    public void onCommand(ActionEvent event) throws IOException, DuplicatedUserException {

        String commandText = commandLine.getText() ;

        commandLine.setStyle(null);
        commandLine.setText("");

        if (commandText.matches(SET_NAME)) {

            String namerecipe = commandText.replace("set name ", "") ;
            name.setText(namerecipe);
            return ;

        }
        else if (commandText.matches(DESCRIPTIONS)) {
            String description = commandText.replace("set description ", "") ;
            descriptions.setText(description);
            return ;

        }else if(commandText.matches(SET_INGREDIENTS)){
            String ingredietsRecipe = commandText.replace("set ingredients ", "") ;
            ingredients.setText(ingredietsRecipe);
            return ;

        }else if(commandText.matches(SUBMIT)){

            recipeBean = new RecipeBean(name.getText(),descriptions.getText(),ingredients.getText());
            createNewRecipeController.createNewRecipe(recipeBean);

        }else if(commandText.matches(BACK)){

            GUI.switchPage(event,"/secondGui/nutritionist/RecipeViewController.fxml");

        }


    }




}
