module com.example.progetto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires controlsfx;
    requires junit;
    requires org.junit.jupiter.api;


    exports test;


    opens com.dicii.ispw.project to javafx.fxml;
    exports com.dicii.ispw.project;
    exports com.dicii.ispw.project.firstView.patient;
    opens com.dicii.ispw.project.firstView.patient to javafx.fxml;
    exports com.dicii.ispw.project.firstView;
    opens com.dicii.ispw.project.firstView to javafx.fxml;
    exports com.dicii.ispw.project.applicationcontroller;
    opens com.dicii.ispw.project.applicationcontroller to javafx.fxml;
    exports com.dicii.ispw.project.firstView.nutritionist;
    opens com.dicii.ispw.project.firstView.nutritionist to javafx.fxml;
    exports com.dicii.ispw.project.firstView.patient.dashboard;
    opens com.dicii.ispw.project.firstView.patient.dashboard to javafx.fxml;
    exports com.dicii.ispw.project.secondView;
    exports com.dicii.ispw.project.secondView.nutritionist;
    opens com.dicii.ispw.project.secondView.nutritionist to javafx.fxml;
    opens com.dicii.ispw.project.secondView to javafx.fxml;
}