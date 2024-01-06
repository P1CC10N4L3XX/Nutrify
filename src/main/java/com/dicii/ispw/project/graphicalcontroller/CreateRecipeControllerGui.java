package com.dicii.ispw.project.graphicalcontroller;

import com.dicii.ispw.project.applicationcontroller.CreateNewRecipeController;
import com.dicii.ispw.project.applicationcontroller.RegisterApplicationController;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.models.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateRecipeControllerGui {

    private Stage stage;
    private Scene scene;

    private Parent root;


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

    private CreateNewRecipeController createNewRecipeController;



    public CreateRecipeControllerGui(){
        createNewRecipeController = new CreateNewRecipeController();;
    }


    public void createRecipeB(ActionEvent event){

        try {
            recipeBean = new RecipeBean(titleField.getText(),descrizioneArea.getText(),ingredientiField.getText());
            title = titleField.getText();
            descrizione = descrizioneArea.getText();
            ingredienti=ingredientiField.getText();
            createNewRecipeController.CreateNewRecipe(recipeBean);


        }

        catch(Exception e){

            System.out.println(e);
        }

    }


    public void back(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource("/firstGui/nutritionist/RecipeView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
