package com.dicii.ispw.project.graphicalcontroller;

import com.dicii.ispw.project.applicationcontroller.OAuthGoogle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class GoogleController implements Initializable {

    @FXML
    private WebView webView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine engine;
        try{
            engine = webView.getEngine();
            engine.load(OAuthGoogle.generateQuery());
        }catch(Exception e){
            System.out.println("Errore nella generazione di WebView");
        }

    }
}
