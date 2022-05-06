package com.project.controllers;

import static com.project.App.setRoot;

import java.io.IOException;

import com.project.App;
import com.project.Game;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

public class SecondaryController {
    @FXML
    Label name1, name2;
    @FXML
    Canvas gameCanvas;

    @FXML
    private void switchToPrimary() throws IOException {
        setRoot(App.loadFXML("primary").load());
    }

    @FXML
    private void close() {
        System.exit(0);
    }

    public void init(String name1, String name2) {
        this.name1.setText(" " + name1 + this.name1.getText());
        this.name2.setText(" " + name2 + this.name2.getText());
        Game game = new Game(gameCanvas);
        game.run();
    }
}