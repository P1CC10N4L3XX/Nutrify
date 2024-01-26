package com.dicii.ispw.project.graphicalcontroller;

import com.dicii.ispw.project.applicationcontroller.CreateNewRecipeController;
import com.dicii.ispw.project.applicationcontroller.CreateNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanDayBean;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.models.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.dicii.ispw.project.database.dao_classes.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateNutritionalPlanControllerGui  implements Initializable{

    private Stage stage;
    private Scene scene;

    private Parent root;
    private String colazione;

    private String pranzo;

    private String cena;

    private float grammiColazione;

    private float grammiPranzo;

    private float grammiCena;

    private String descrizione;

    public TextField grammiColazioneField;
    public TextField grammiPranzoField;
    public TextField grammiCenaField;

    public TextArea descrizioneArea;

    @FXML
    Label data;

    @FXML
    private ChoiceBox<String> myChoiceBox1;

    @FXML
    private ChoiceBox<String> myChoiceBox2;

    @FXML
    private ChoiceBox<String> myChoiceBox3;

    private CreateNutritionalController createNutritionalController;

    private NutritionalPlanDayBean nutritionalPlanDayBean;


    private List<RecipeBean> list;


    public CreateNutritionalPlanControllerGui(){
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

            myChoiceBox1.getItems().addAll(String.valueOf(recipe.getName()));
            myChoiceBox2.getItems().addAll(String.valueOf(recipe.getName()));
            myChoiceBox3.getItems().addAll(String.valueOf(recipe.getName()));
        }


        myChoiceBox1.setOnAction(this::getFood);
        myChoiceBox2.setOnAction(this::getFood);
        myChoiceBox3.setOnAction(this::getFood);
    }


    public void displayData(String dataValue){
        System.out.println(dataValue);
        if(dataValue!=null){
            data.setText(dataValue);
        }


    }



    public void display() throws DuplicatedUserException {

        list = createNutritionalController.displayRecipe();

    }

    public void getFood(ActionEvent event){
        colazione= myChoiceBox1.getValue();
        pranzo= myChoiceBox2.getValue();
        cena= myChoiceBox3.getValue();
        System.out.println(colazione);
        System.out.println(pranzo);
        System.out.println(cena);

    }




    public void createNutritionalPlan(ActionEvent event){

        try {

            nutritionalPlanDayBean= new NutritionalPlanDayBean(data.getText(),colazione,pranzo,cena,grammiColazioneField.getText(),grammiPranzoField.getText(),grammiCenaField.getText());

            createNutritionalController.sendNutritionalPlanDay(nutritionalPlanDayBean);




            System.out.println(grammiColazione);

            display();

        }

        catch(Exception e){


            System.out.println(e);
        }

    }






    public void back(ActionEvent event) throws Exception {

        root = FXMLLoader.load(getClass().getResource("/firstGui/nutritionist/NutritionalPlanDashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



}
