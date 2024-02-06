package com.dicii.ispw.project.firstview.nutritionist.dashboard;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanBean;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;

import com.dicii.ispw.project.firstview.nutritionist.DashboardController;
import com.dicii.ispw.project.firstview.nutritionist.NutritionalPlanDay;
import com.dicii.ispw.project.firstview.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardHome extends DashboardController implements Initializable {
    private NutritionalPlanBean nutritionalPlanBean;

    private Stage stage;
    private Scene scene;

    private Parent root;



    private NutritionistBean nutritionistBean;

    private ManageNutritionalController createNutritionalController;

    @FXML
    private ListView<String> myListView;

    private List<PatientBean> list;
    private String selectedPatient;


    public DashboardHome(){
        createNutritionalController = new ManageNutritionalController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            list = createNutritionalController.displayPatient(Session.getSessionInstance().getLoggedUser().getEmail());

        } catch (DuplicatedUserException e) {
            throw new RuntimeException(e);
        }
        for (PatientBean patientBean : list) {

            myListView.getItems().addAll(String.valueOf(patientBean.getEmail()));

        }

        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                selectedPatient=myListView.getSelectionModel().getSelectedItem();

            }
        });


    }

    public void createNutritionalPlan(ActionEvent event) throws Exception {

        nutritionistBean = new NutritionistBean(Session.getSessionInstance().getLoggedUser().getEmail());

        PatientBean patientBeanSelected = new PatientBean();
        patientBeanSelected.setEmail(selectedPatient);


        FXMLLoader loader =new FXMLLoader(getClass().getResource("/firstGui/nutritionist/NutritionalPlanDay.fxml"));
        root = loader.load();

        NutritionalPlanDay nutritionalPlanDay = loader.getController();
        nutritionalPlanDay.viewPatientInfo(selectedPatient);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDateString = currentDate.format(formatter);
        nutritionalPlanBean = new NutritionalPlanBean(formattedDateString,patientBeanSelected,nutritionistBean);
        createNutritionalController.createNutrutionalPlan(nutritionalPlanBean);




    }

    public void recipeDashBoard(ActionEvent event) throws Exception {

        GUI.switchPage(event,"/firstGui/nutritionist/RecipeView.fxml");

    }


}
