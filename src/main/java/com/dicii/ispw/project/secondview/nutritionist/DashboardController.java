package com.dicii.ispw.project.secondview.nutritionist;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.NutritionalPlanBean;
import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.firstview.utils.GUI;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {
    @FXML
    private TextField commandLine;

    private NutritionalPlanBean nutritionalPlanBean;
    private ManageNutritionalController createNutritionalController;

    private NutritionistBean nutritionistBean;
    private Stage stage;
    private Scene scene;

    private Parent root;



    private static final String CREATE="create plan";

    private static final String RECIPE="add recipe";

    private static final String SET_PATIENT="set patient .*";

    @FXML
    private ListView<String> myListView;
    @FXML
    private TextField patientField;
    @FXML
    private Label warning;

    private List<PatientBean> list;



    public DashboardController(){
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



    }

    @FXML
    public void onCommand(ActionEvent event) throws Exception {

        String commandText = commandLine.getText() ;

        commandLine.setStyle(null);
        commandLine.setText("");
        if (commandText.matches(CREATE)) {
            if(patientField.getText().isEmpty()){
                warning.setText("select patient before");
            }else{

                try {
                    nutritionistBean = new NutritionistBean(Session.getSessionInstance().getLoggedUser().getEmail());

                    PatientBean patientBeanSelected = new PatientBean();
                    patientBeanSelected.setEmail(patientField.getText());

                    FXMLLoader loader =new FXMLLoader(getClass().getResource("/SecondGui/nutritionist/NutritionalPlanDay.fxml"));
                    root = loader.load();

                    NutritionalPlanDay nutritionalPlanDay = loader.getController();
                    nutritionalPlanDay.viewPatientInfo(patientField.getText());

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

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }


        }
        else if (commandText.matches(RECIPE)) {
            GUI.switchPage(event,"/SecondGui/nutritionist/RecipeView.fxml");

        }else if(commandText.matches(SET_PATIENT)){
            String patient = commandText.replace("set patient ", "") ;


            for (PatientBean patientBean : list) {
                if(patient.matches(patientBean.getEmail())){
                    patientField.setText(patient);
                }
            }
            if(patientField.getText().isEmpty()){
                warning.setText("paziente not existes");
            }



        }


    }
}
