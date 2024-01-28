package com.dicii.ispw.project.graphicalcontroller.patient.dashboard;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.graphicalcontroller.patient.DashboardController;
import com.dicii.ispw.project.graphicalcontroller.patient.NutritionistCardController;
import com.dicii.ispw.project.graphicalcontroller.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController extends DashboardController implements Initializable {

    private final SubscribeToNutritionistController subscribeToNutritionistController;
    @FXML
    private VBox cardLayout;
    private final int LIMIT_NUMBER = 7;

    private int offset;
    public HomeController(){
        subscribeToNutritionistController=new SubscribeToNutritionistController();
        offset = 0;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<NutritionistBean> nutritionistBeanList = new ArrayList<>(subscribeToNutritionistController.getNutritionistList(LIMIT_NUMBER,offset));
        try{
            showNutritionistList(nutritionistBeanList);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void handleScroll(){
        double scrollPosition = cardLayout.getScene().getWindow().getHeight() + cardLayout.getHeight();
        if(scrollPosition >= cardLayout.getBoundsInParent().getHeight()){

            onScrollFinished();
        }
    }
    public void onScrollFinished() {
        offset = offset+LIMIT_NUMBER;
        List<NutritionistBean> nutritionistBeanList = new ArrayList<>(subscribeToNutritionistController.getNutritionistList(LIMIT_NUMBER,offset));
        try {
            showNutritionistList(nutritionistBeanList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    private void showNutritionistList(List<NutritionistBean> nutritionistBeanList) throws IOException{
        for(NutritionistBean nutritionistBean : nutritionistBeanList){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/firstGui/patient/dashboard/obj/NutritionistCard.fxml"));
            Pane cardBox = fxmlLoader.load();
            NutritionistCardController cardController = fxmlLoader.getController();
            cardController.initCard(nutritionistBean);
            cardLayout.getChildren().add(cardBox);
        }
    }
}
