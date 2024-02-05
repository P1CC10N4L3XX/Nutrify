module com.example.progetto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires controlsfx;
    requires junit;
    requires org.junit.jupiter.api;
    requires java.rmi;


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
    exports com.dicii.ispw.project.firstview.patient.obj;
    opens com.dicii.ispw.project.firstview.patient.obj to javafx.fxml;
    exports com.dicii.ispw.project.patterns.observer to java.rmi;
    opens com.dicii.ispw.project.patterns.observer;
    exports com.dicii.ispw.project.firstview.obj to javafx.fxml;
    opens com.dicii.ispw.project.firstview.obj;
    exports com.dicii.ispw.project.firstview.nutritionist.dashboard to javafx.fxml;
    opens com.dicii.ispw.project.firstview.nutritionist.dashboard;
    exports com.dicii.ispw.project.beans;
    exports com.dicii.ispw.project.models;
    exports com.dicii.ispw.project.firstview.nutritionist.obj to javafx.fxml;
    opens com.dicii.ispw.project.firstview.nutritionist.obj;

}