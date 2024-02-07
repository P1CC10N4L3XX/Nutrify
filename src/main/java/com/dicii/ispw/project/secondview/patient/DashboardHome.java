package com.dicii.ispw.project.secondview.patient;

import com.dicii.ispw.project.applicationcontroller.SubscribeToNutritionistController;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardHome implements Initializable {
    @FXML
    private TextField commandLine;
    @FXML
    private ListView<String> nutritionistListView;

    private static final String MY_PLAN = "My plan";
    private static final String NOTIFICATION = "notificationArea";

    private static final String SEE_NUTRITIONIST = "see .*";

    List<NutritionistBean> nutritionistBeanList;
    private final SubscribeToNutritionistController subscribeToNutritionistController;

    public DashboardHome(){
        subscribeToNutritionistController = new SubscribeToNutritionistController();
        nutritionistBeanList = new ArrayList<>(subscribeToNutritionistController.getNutritionistList(100, 0));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(NutritionistBean nutritionistBean : nutritionistBeanList){
            nutritionistListView.getItems().addAll(nutritionistBean.getEmail());
        }
    }
    public void onCommand(ActionEvent event) throws IOException {
        String commandText = commandLine.getText();
        commandLine.setText("");
        if(commandText.matches(MY_PLAN)){
            GUI.switchPage(event,"/SecondGui/patient/DashboardPlan.fxml");
        }else if(commandText.matches(NOTIFICATION)){
            GUI.switchPage(event,"/SecondGui/patient/NotificationArea.fxml");
        }else if(commandText.matches(SEE_NUTRITIONIST)){
            String nutritionistEmail = commandText.replace("see ","");
            showNutritionistDetails(nutritionistEmail,event);
        }
    }

    private void showNutritionistDetails(String nutritionistEmail,ActionEvent event) throws IOException{
        NutritionistBean nutritionistBeanResult = null;
        for(NutritionistBean nutritionistBean : nutritionistBeanList){
            if(nutritionistBean.getEmail().equals(nutritionistEmail)){
                nutritionistBeanResult = nutritionistBean;
                break;
            }
        }
        if(nutritionistBeanResult==null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Nutritionist doesn't exist");
            alert.showAndWait();
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/secondGui/patient/nutritionistDetails.fxml"));
            Parent root = loader.load();
            NutritionistDetailsController nutritionistDetailsController = loader.getController();
            nutritionistDetailsController.showNutritionistDetails(nutritionistBeanResult);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
