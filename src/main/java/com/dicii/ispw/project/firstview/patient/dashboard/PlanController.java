package com.dicii.ispw.project.firstview.patient.dashboard;

import com.dicii.ispw.project.applicationcontroller.ManageNutritionalController;
import com.dicii.ispw.project.beans.PatientBean;
import com.dicii.ispw.project.beans.UserBean;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.exceptions.NutritionalPlanNotFoundException;
import com.dicii.ispw.project.firstview.ViewNutritionalPlan;
import com.dicii.ispw.project.firstview.patient.DashboardController;
import com.dicii.ispw.project.patterns.singleton.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PlanController extends DashboardController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label name;
    @FXML
    private Label surname;

    @FXML
    private Label birthday;

    @FXML
    private Label weight;

    @FXML
    private Label height;

    @FXML
    private Label ilneeses;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private Label selectionDate;

    @FXML
    private Label warning;

    String dataSelected;

    private PatientBean patientBean;

    private ManageNutritionalController createNutritionalController;



    public PlanController(){
        createNutritionalController = new ManageNutritionalController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewPatientInfo();
    }
    @FXML
    void dataPicker(ActionEvent e){
        LocalDate localDate=dataPicker.getValue();
        String pattern="MMMM dd, yyyy";
        String dataPattern= localDate.format(DateTimeFormatter.ofPattern(pattern));
        selectionDate.setText(dataPattern);
        dataSelected=selectionDate.getText();


    }

    public void viewNutritionalPlan(ActionEvent event) throws Exception{


        try{

            UserBean userBean = createNutritionalController.loadNutritionistSubscribed(Session.getSessionInstance().getLoggedUser().getEmail());

            FXMLLoader loader =new FXMLLoader(getClass().getResource("/firstGui/nutritionist/ViewNutritionalPlan.fxml"));
            root = loader.load();

            ViewNutritionalPlan viewNutritionalPlan = loader.getController();
            viewNutritionalPlan.takeParameter(dataSelected,patientBean.getIlnessesBean().getName(),userBean.getEmail());


            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (NutritionalPlanNotFoundException e){
            warning.setText(e.getMessage());
        }catch (NotExistentUserException e){
            warning.setText("attualmente non sei iscritto a nessun nutrizionista");
        }


    }

    public void viewPatientInfo() {

        try {


            patientBean=createNutritionalController.displayUserInfo(Session.getSessionInstance().getLoggedUser().getEmail());
            this.name.setText(patientBean.getName());
            this.surname.setText(patientBean.getSurname());
            this.birthday.setText(patientBean.getDateOfBirth());
            this.weight.setText(patientBean.getWeight());
            this.height.setText(patientBean.getHeight());
            this.ilneeses.setText(patientBean.getIlnessesBean().getName());



        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }


    }
}
