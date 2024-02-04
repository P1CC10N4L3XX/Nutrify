package com.dicii.ispw.project.firstview;

import com.dicii.ispw.project.applicationcontroller.OAuthGoogle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class GoogleController implements Initializable {

    @FXML
    private WebView webView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine engine;

            engine = webView.getEngine();
        try {
            engine.load(OAuthGoogle.generateQuery());
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }


    }
}
