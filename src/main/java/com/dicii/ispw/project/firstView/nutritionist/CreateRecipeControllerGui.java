package com.dicii.ispw.project.firstView.nutritionist;


import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.firstView.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class CreateRecipeControllerGui {

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


    public void createRecipeB(ActionEvent event){

        try {
            recipeBean = new RecipeBean(titleField.getText(),descrizioneArea.getText(),ingredientiField.getText());
            title = titleField.getText();
            descrizione = descrizioneArea.getText();
            ingredienti=ingredientiField.getText();
            createNewRecipeController.createNewRecipe(recipeBean);


        }

        catch(Exception e){

            System.out.println(e);
        }

    }


    public void back(ActionEvent event) throws Exception {
        GUI.switchPage(event,"/firstGui/nutritionist/RecipeView.fxml");

    }
}
