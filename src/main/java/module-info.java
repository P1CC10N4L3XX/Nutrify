module com.example.progetto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.dicii.ispw.project to javafx.fxml;
    exports com.dicii.ispw.project;
    exports com.dicii.ispw.project.utente;
    opens com.dicii.ispw.project.utente to javafx.fxml;
    exports com.dicii.ispw.project.graphicalcontroller;
    opens com.dicii.ispw.project.graphicalcontroller to javafx.fxml;
    exports com.dicii.ispw.project.applicationcontroller;
    opens com.dicii.ispw.project.applicationcontroller to javafx.fxml;
}