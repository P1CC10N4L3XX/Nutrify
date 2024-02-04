package com.dicii.ispw.project.secondView.nutritionist;


import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;

import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class RecipeViewController implements Initializable {

    @FXML
    public TextField commandLine;

    private ManageNutritionalController createNutritionalController;


    private static final String CREATE="add new recipe";

    private static final String DELETE="delete recipe .*";
    private static final String SUBMIT="submit";

    @FXML
    public ListView<String> myListView;

    @FXML
    public TextField recipes;

    private List<RecipeBean> list;

    private String recipeName;


    public RecipeViewController(){
        createNutritionalController = new ManageNutritionalController();
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        try {
            list = createNutritionalController.displayRecipe();
        } catch (DuplicatedUserException e) {
            throw new RuntimeException(e);
        }
        for (RecipeBean recipe : list) {

            myListView.getItems().addAll(String.valueOf(recipe.getName()));

        }
    }

    @FXML
    public void onCommand(ActionEvent event) throws IOException, DuplicatedUserException {

        String commandText = commandLine.getText() ;

        commandLine.setStyle(null);
        commandLine.setText("");
        if (commandText.matches(CREATE)) {
            GUI.switchPage(event,"/SecondGui/nutritionist/AddNewRecipe.fxml");
        }
        else if (commandText.matches(DELETE)) {

            recipeName = commandText.replace("delete recipe ", "") ;

            for (RecipeBean recipe : list) {
                if(recipeName.matches(recipe.getName())){
                    recipes.setText(recipeName);
                }else{
                    System.out.println("non puoi eliminarla non e presente");
                }
            }

        }else if(commandText.matches(SUBMIT)){


                RecipeBean recipeBean = new RecipeBean(recipeName);
                ManageNutritionalController createNewRecipeController = new ManageNutritionalController();
                createNewRecipeController.deleteRecipe(recipeBean);

        }


    }
}
