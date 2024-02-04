package com.dicii.ispw.project.secondview.patient;

import com.dicii.ispw.project.firstview.utils.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PatientDashBoard {

    @FXML
    private TextField commandLine;

    private static final String MY_PLAN="my plan";

    @FXML
    public void onCommand(ActionEvent event) throws Exception {

        String commandText = commandLine.getText() ;

        commandLine.setStyle(null);
        commandLine.setText("");
        if (commandText.matches(MY_PLAN)) {
            GUI.switchPage(event,"/SecondGui/patient/DashboardPlan.fxml");

        }



    }
}
