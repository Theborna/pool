package com.project.controllers;

import static com.project.App.setRoot;

import java.io.IOException;

import com.project.App;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class PrimaryController {
    @FXML
    TextField field1, field2;

    private Parent root;

    @FXML
    private void switchToSecondary() throws IOException {
        setRoot(App.loadFXML("secondary").load());
    }

    @FXML
    private void login() throws IOException {
        FXMLLoader loader = App.loadFXML("secondary");
        root = loader.load();
        SecondaryController controller = loader.getController();
        controller.displayName(field1.getText(), field2.getText());
        setRoot(root);
    }

    @FXML
    private void close() {
        System.exit(0);
    }
}
