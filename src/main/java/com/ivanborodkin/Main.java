package com.ivanborodkin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.application.Application;

import java.io.File;
import java.net.URL;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = new File("src/main/resources/config.fxml").toURI().toURL();
        Parent root = FXMLLoader.load((url));
        Scene scene = new Scene(root);
        primaryStage.setTitle("JavaFx Test Task");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
