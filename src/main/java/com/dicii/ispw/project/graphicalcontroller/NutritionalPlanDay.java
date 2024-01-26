package com.dicii.ispw.project.graphicalcontroller;

import com.dicii.ispw.project.applicationcontroller.CreateNutritionalController;

import com.dicii.ispw.project.exceptions.NutritionalPlanFounded;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class NutritionalPlanDay {
    private Stage stage;
    private Scene scene;

    private Parent root;



    @FXML
    private DatePicker dataPicker;

    @FXML
    private Label selectionDate;

    String dataSelected;


    @FXML
    public Label client;

    @FXML
    public Label warning;



    private CreateNutritionalController createNutritionalController;

    public NutritionalPlanDay(){
         createNutritionalController = new CreateNutritionalController();
    }


    @FXML
    void dataPicker(ActionEvent e){
        LocalDate localDate=dataPicker.getValue();
        String pattern="MMMM dd, yyyy";
        String dataPattern= localDate.format(DateTimeFormatter.ofPattern(pattern));
        selectionDate.setText(dataPattern);
        dataSelected=selectionDate.getText();


    }




    public void day(ActionEvent event ) throws Exception {


            FXMLLoader loader =new FXMLLoader(getClass().getResource("/firstGui/nutritionist/CreateNutritionalPlan.fxml"));
            root = loader.load();

            CreateNutritionalPlanControllerGui createNutritionalPlanControllerGui = loader.getController();
            createNutritionalPlanControllerGui.displayData(dataSelected);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();




    }






    public void viewNutritionalPlan(ActionEvent event) throws Exception{


        try{
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/firstGui/nutritionist/ViewNutritionalPlan.fxml"));
            root = loader.load();

            ViewNutritionalPlan viewNutritionalPlan = loader.getController();
            viewNutritionalPlan.takeParameter(dataSelected);


            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (NutritionalPlanNotFoundException e){
            warning.setText(e.getMessage());
        }






    }









}
