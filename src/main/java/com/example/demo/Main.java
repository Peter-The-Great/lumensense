package com.example.demo;
import com.example.demo.utils.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.Objects;

public class Main extends Application {

    //Start the application via the launch function
    public static void main(String[] args) {
        launch(args);

    }
    @Override

    //Start the application with loading the login screen. By doing a try.
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
            Scene scene = new Scene(root);

            Image icon = new Image("file:img/logo.png");
            stage.getIcons().add(icon);

            stage.setTitle("Lumensense");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            this.startUpdaterThreads();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void startUpdaterThreads() {
        Updater updater = new Updater();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    updater.updateFast();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                    updater.updateSlow();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
