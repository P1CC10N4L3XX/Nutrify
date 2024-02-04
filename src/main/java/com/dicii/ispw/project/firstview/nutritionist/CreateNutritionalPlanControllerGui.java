package com.dicii.ispw.project.firstview.nutritionist;


import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanDayBean;
import com.dicii.ispw.project.beans.RecipeBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.*;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateNutritionalPlanControllerGui  implements Initializable{


    private String colazione;

    private String pranzo;

    private String cena;


    private TextField grammiColazioneField;
    private TextField grammiPranzoField;
    private TextField grammiCenaField;

    public TextArea descrizioneArea;

    @FXML
    Label data;

    @FXML
    private ChoiceBox<String> myChoiceBox1;

    @FXML
    private ChoiceBox<String> myChoiceBox2;

    @FXML
    private ChoiceBox<String> myChoiceBox3;

    @FXML
    private Label warning;

    private final ManageNutritionalController createNutritionalController;

    private NutritionalPlanDayBean nutritionalPlanDayBean;


    private List<RecipeBean> list;


    public CreateNutritionalPlanControllerGui(){
        createNutritionalController = new ManageNutritionalController();
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            display();
        } catch (DuplicatedUserException e) {
            throw new RuntimeException(e);
        }

        for (RecipeBean recipe : list) {


            myChoiceBox1.getItems().addAll(String.valueOf(recipe.getName()));
            myChoiceBox2.getItems().addAll(String.valueOf(recipe.getName()));
            myChoiceBox3.getItems().addAll(String.valueOf(recipe.getName()));
        }


        myChoiceBox1.setOnAction(this::getFood);
        myChoiceBox2.setOnAction(this::getFood);
        myChoiceBox3.setOnAction(this::getFood);
    }


    public void displayData(String dataValue){

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

    }



    public RecipeBean convertStringToReciBean(String ricetta) {
        RecipeBean recipeBean = new RecipeBean();
        recipeBean.setName(ricetta);
        return recipeBean;
    }

    public void createNutritionalPlan(ActionEvent event){

        if(grammiColazioneField.getText().isEmpty()||grammiPranzoField.getText().isEmpty() || grammiCenaField.getText().isEmpty() || colazione.isEmpty() || pranzo.isEmpty() || cena.isEmpty() || descrizioneArea.getText().isEmpty()) {
            warning.setText("Compilare tuttti i campi");
        }else{
            try {

                nutritionalPlanDayBean= new NutritionalPlanDayBean(data.getText(),convertStringToReciBean(colazione),convertStringToReciBean(pranzo),convertStringToReciBean(cena),grammiColazioneField.getText(),grammiPranzoField.getText(),grammiCenaField.getText(),descrizioneArea.getText());

                if(createNutritionalController.sendNutritionalPlanDay(nutritionalPlanDayBean)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "not saved!!") ;
                    alert.showAndWait() ;
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "succesfully saved!!") ;
                    alert.showAndWait() ;
                }
                grammiColazioneField.setText("");
                grammiPranzoField.setText("");
                grammiCenaField.setText("");
                descrizioneArea.setText("");




            }

            catch(Exception e){
                System.out.println(e);
            }


        }


    }






    public void back(ActionEvent event) throws Exception {

        GUI.switchPage(event,"/firstGui/nutritionist/NutritionalPlanDashboard.fxml");

    }



}
