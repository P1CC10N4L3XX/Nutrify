package com.dicii.ispw.project.graphicalcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateNutritionalPlanControllerGui  implements Initializable{

    private Stage stage;
    private Scene scene;

    private Parent root;
    private String colazione;

    private String pranzo;

    private String cena;

    private String grammiColazione;

    private String grammiPranzo;

    private String grammiCena;

    private String descrizione;

    public TextField grammiColazioneField;
    public TextField grammiPranzoField;
    public TextField grammiCenaField;

    public TextArea descrizioneArea;

    private String parametro;

    @FXML
    private ChoiceBox<String> myChoiceBox1;

    @FXML
    private ChoiceBox<String> myChoiceBox2;

    @FXML
    private ChoiceBox<String> myChoiceBox3;

    private String[] food={"pizza","sushi","pasta"};


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myChoiceBox1.getItems().addAll(food);
        myChoiceBox2.getItems().addAll(food);
        myChoiceBox3.getItems().addAll(food);

        myChoiceBox1.setOnAction(this::getFood);
    }


   public void getFood(ActionEvent event){
       String myFood= myChoiceBox1.getValue();
       System.out.println(myFood);
   }




    public void createNutritionalPlan(ActionEvent event){

        try {
            grammiColazione = grammiColazioneField.getText();
            grammiPranzo = grammiColazioneField.getText();
            grammiCena = grammiColazioneField.getText();
            descrizione = descrizioneArea.getText();

            //myLabel.setText("campi non validi");
            System.out.println(grammiColazione);

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
