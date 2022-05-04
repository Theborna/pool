package com.project.controllers;

import static com.project.App.setRoot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.project.App;
import com.project.models.Ball;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

public class SecondaryController {
    @FXML
    Label name1, name2;
    @FXML
    Canvas gameCanvas = new Canvas();

    // private ArrayList<Ball> balls = new ArrayList<>(
    // List.of(1, 2, 3, 4, 5, 6, 7, 8, 9).stream().map(Ball::new).toList());// list
    // of balls
    private Ball myBall;// current ball

    @FXML
    private void switchToPrimary() throws IOException {
        setRoot(App.loadFXML("primary").load());
    }

    public void displayName(String name1, String name2) {
        this.name1.setText(" " + name1 + this.name1.getText());
        this.name2.setText(" " + name2 + this.name2.getText());
    }
}