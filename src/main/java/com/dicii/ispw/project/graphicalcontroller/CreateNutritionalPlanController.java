package com.dicii.ispw.project.graphicalcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateNutritionalPlanController {

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


    //per prende il giorno che e stato selezionato


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

        root = FXMLLoader.load(getClass().getResource("/firstGui/Nutritionist/NutritionalPlanDashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
