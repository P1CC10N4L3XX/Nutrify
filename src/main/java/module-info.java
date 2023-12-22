module com.dicii.ispw.project.nutrify {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.dicii.ispw.project.nutrify to javafx.fxml;
    exports com.dicii.ispw.project.nutrify;
}