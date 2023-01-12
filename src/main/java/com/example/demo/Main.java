package com.example.demo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {


        launch(args);

    }
    @Override


    public void start(Stage stage) {
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
            Scene scene = new Scene(root);

            Image icon = new Image("file:img/logo.png");
            stage.getIcons().add(icon);

            stage.setTitle("Lumensense");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
