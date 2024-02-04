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
    exports com.dicii.ispw.project.firstview.patient;
    opens com.dicii.ispw.project.firstview.patient to javafx.fxml;
    exports com.dicii.ispw.project.firstview;
    opens com.dicii.ispw.project.firstview to javafx.fxml;
    exports com.dicii.ispw.project.applicationcontroller;
    opens com.dicii.ispw.project.applicationcontroller to javafx.fxml;
    exports com.dicii.ispw.project.firstview.nutritionist;
    opens com.dicii.ispw.project.firstview.nutritionist to javafx.fxml;
    exports com.dicii.ispw.project.firstview.patient.dashboard;
    opens com.dicii.ispw.project.firstview.patient.dashboard to javafx.fxml;
    exports com.dicii.ispw.project.secondView;
    exports com.dicii.ispw.project.secondView.nutritionist;
    opens com.dicii.ispw.project.secondView.nutritionist to javafx.fxml;
    opens com.dicii.ispw.project.secondView to javafx.fxml;
    opens com.dicii.ispw.project.secondView.patient to javafx.fxml;
}