package com.dicii.ispw.project.graphicalcontroller;

import com.dicii.ispw.project.applicationcontroller.CreateNewRecipeController;
import com.dicii.ispw.project.applicationcontroller.CreateNutritionalController;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeViewControllerGui implements Initializable {

    private Stage stage;
    private Scene scene;

    private Parent root;

    private Label nameRecipe;

    private Label ingredientiLabel;

    private Label descrizioneRecipe;
    private CreateNutritionalController createNutritionalController;

    @FXML
    private ListView<String> myListView;

    private List<RecipeBean> list;

    private RecipeBean recipeBean;

    private String selectedRecipe;

    public RecipeViewControllerGui(){
        createNutritionalController = new CreateNutritionalController();
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        try {
            display();
        } catch (DuplicatedUserException e) {
            throw new RuntimeException(e);
        }
        int i=0;
        for (RecipeBean recipe : list) {
            i++;


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

        root = FXMLLoader.load(getClass().getResource("/firstGui/nutritionist/CreateRecipe.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void display() throws DuplicatedUserException {

        list = createNutritionalController.displayRecipe();

    }

    public void deleteRecipe(ActionEvent event) throws Exception {

        RecipeBean recipeBean = new RecipeBean(selectedRecipe);
        CreateNewRecipeController createNewRecipeController = new CreateNewRecipeController();
        createNewRecipeController.deleteRecipe(recipeBean);


    }

    public void modifyRecipe(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource(""));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void back(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource("/firstGui/nutritionist/NutritionalPlanDashBoard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
