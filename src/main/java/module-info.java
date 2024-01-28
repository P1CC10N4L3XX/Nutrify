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
    exports com.dicii.ispw.project.graphicalcontroller.patient;
    opens com.dicii.ispw.project.graphicalcontroller.patient to javafx.fxml;
    exports com.dicii.ispw.project.graphicalcontroller;
    opens com.dicii.ispw.project.graphicalcontroller to javafx.fxml;
    exports com.dicii.ispw.project.applicationcontroller;
    opens com.dicii.ispw.project.applicationcontroller to javafx.fxml;
    exports com.dicii.ispw.project.graphicalcontroller.nutritionist;
    opens com.dicii.ispw.project.graphicalcontroller.nutritionist to javafx.fxml;
    exports com.dicii.ispw.project.graphicalcontroller.patient.dashboard;
    opens com.dicii.ispw.project.graphicalcontroller.patient.dashboard to javafx.fxml;

}