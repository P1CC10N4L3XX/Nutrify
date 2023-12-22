module com.example.progetto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.dicii.ispw.project to javafx.fxml;
    exports com.dicii.ispw.project;
    exports com.dicii.ispw.project.utente;
    opens com.dicii.ispw.project.utente to javafx.fxml;
    exports com.dicii.ispw.project.graphicalControllers;
    opens com.dicii.ispw.project.graphicalControllers to javafx.fxml;
    exports com.dicii.ispw.project.applicationController;
    opens com.dicii.ispw.project.applicationController to javafx.fxml;
}