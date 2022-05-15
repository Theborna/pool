package com.project.controllers;

import static com.project.App.setRoot;

import java.io.IOException;

import com.project.App;
import com.project.Game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class SecondaryController {
    @FXML
    Label name1, name2;
    @FXML
    Canvas gameCanvas;
    @FXML
    ImageView cue;
    @FXML
    Slider strength;
    @FXML
    Label turnLabel;
    private String name1String, name2String;

    @FXML
    private void switchToPrimary() throws IOException {
        setRoot(App.loadFXML("primary").load());
    }

    @FXML
    private void close() {
        System.exit(0);
    }

    @FXML
    private void restart() throws IOException {
        FXMLLoader loader = App.loadFXML("secondary");
        Parent root = loader.load();
        SecondaryController controller = loader.getController();
        controller.init(name1String, name2String);
        setRoot(root);
    }

    public void init(String name1, String name2) {
        name1String = name1;
        name2String = name2;
        this.name1.setText(" " + name1 + this.name1.getText());
        this.name2.setText(" " + name2 + this.name2.getText());
        Game game = new Game(this);
        game.run();
    }

    public Canvas getGameCanvas() {
        return gameCanvas;
    }

    public ImageView getCue() {
        return cue;
    }

    public Slider getStrength() {
        return strength;
    }

    public String getName(int n) {
        if (n == 1)
            return name1String;
        if (n == 2)
            return name2String;
        return "akh tof";
    }

    public void setTurn(String turn) {
        turnLabel.setText(turn);
    }
}