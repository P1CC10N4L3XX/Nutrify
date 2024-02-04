package com.dicii.ispw.project.firstview.nutritionist;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeViewControllerGui implements Initializable {


    private ManageNutritionalController createNutritionalController;

    @FXML
    private ListView<String> myListView;

    private List<RecipeBean> list;

    private String selectedRecipe;

    public RecipeViewControllerGui(){
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

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                selectedRecipe=myListView.getSelectionModel().getSelectedItem();

            }
        });

    }
    public void addRecipe(ActionEvent event) throws Exception {
        GUI.switchPage(event,"/firstGui/nutritionist/CreateRecipe.fxml");


    }



    public void deleteRecipe(ActionEvent event) throws Exception {

        RecipeBean recipeBean = new RecipeBean(selectedRecipe);
        ManageNutritionalController createNewRecipeController = new ManageNutritionalController();
        createNewRecipeController.deleteRecipe(recipeBean);


    }


    public void back(ActionEvent event) throws Exception {

        GUI.switchPage(event,"/firstGui/nutritionist/NutritionalPlanDashBoard.fxml");

    }

}
