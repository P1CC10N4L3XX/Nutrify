package com.dicii.ispw.project.firstview.nutritionist;


import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;


public class CreateRecipeControllerGui extends DashboardController {

    private String title;

    private String descrizione;
    private String ingredienti;

    private RecipeBean recipeBean;

    @FXML
    private TextField ingredientiField;

    @FXML
    private TextField titleField;
    @FXML
    private TextArea descrizioneArea;

    private ManageNutritionalController createNewRecipeController;



    public CreateRecipeControllerGui(){
        createNewRecipeController = new ManageNutritionalController();
    }


    public void createRecipeB() throws DuplicatedUserException {


            recipeBean = new RecipeBean(titleField.getText(),descrizioneArea.getText(),ingredientiField.getText());
            title = titleField.getText();
            descrizione = descrizioneArea.getText();
            ingredienti=ingredientiField.getText();
            createNewRecipeController.createNewRecipe(recipeBean);


    }


    public void back(ActionEvent event) throws IOException {
        GUI.switchPage(event,"/firstGui/nutritionist/dashboard/DashBoardRecipes.fxml");

    }
}
