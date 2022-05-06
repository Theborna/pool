package com.project;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static double mouseX, mouseY;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary").load(), 800, 600);
        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
        stage.getIcons().add(new Image("icon.jpg"));
        stage.setResizable(false);
        stage.setTitle("pool");
        stage.setScene(scene);
        stage.show();
        mouse();
    }

    public static void setRoot(Parent root) throws IOException {
        scene.setRoot(root);
    }

    private void mouse() {
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                mouseX = event.getX();
                mouseY = event.getY();
            }

        });
    }

    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    /**
     * 
     * @param fxml
     * @return loaded fxml from the resources folder
     * @throws IOException
     */
    public static FXMLLoader loadFXML(String fxml) throws IOException {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }

    /*
     * public static <T> T getController(String fxml, Parent root) throws
     * IOException {
     * FXMLLoader loader = loadFXML(fxml);
     * root = loader.load();
     * return loader.getController();
     * }
     */
    public static void main(String[] args) {
        launch();
    }
}