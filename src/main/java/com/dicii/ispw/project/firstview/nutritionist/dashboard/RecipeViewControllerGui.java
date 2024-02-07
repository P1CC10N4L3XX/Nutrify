package com.dicii.ispw.project.firstview.nutritionist.dashboard;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.firstview.nutritionist.DashboardController;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeViewControllerGui extends DashboardController implements Initializable {


    private ManageNutritionalController createNutritionalController;

    @FXML
    private ListView<String> myListView;

    @FXML
    private Label warning;

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
        GUI.switchPage(event,"/firstGui/nutritionist/CreateRecipes.fxml");


    }



    public void deleteRecipe(ActionEvent event) throws Exception {

        if(selectedRecipe==null){
            warning.setText("select recipe to delete before");
        }

        RecipeBean recipeBean = new RecipeBean(selectedRecipe);
        ManageNutritionalController createNewRecipeController = new ManageNutritionalController();
        createNewRecipeController.deleteRecipe(recipeBean);


    }


    public void back(ActionEvent event) throws Exception {

        GUI.switchPage(event,"/firstGui/nutritionist/dashboard/DashBoardHome.fxml");

    }

}
