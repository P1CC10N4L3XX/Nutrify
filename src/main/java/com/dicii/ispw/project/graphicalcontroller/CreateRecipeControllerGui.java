package com.dicii.ispw.project.graphicalcontroller;

import javafx.event.ActionEvent;
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

    public TextField titleField;

    public TextArea descrizioneArea;




    public void createRecipeB(ActionEvent event){

        try {
            title = titleField.getText();
            descrizione = descrizioneArea.getText();

            //myLabel.setText("campi non validi");
           // System.out.println(grammiColazione);
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
