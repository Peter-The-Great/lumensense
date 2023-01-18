package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

//The main controller in the entire program.
public class MainController {
    public Stage stage;
    public Scene scene;
    public Parent root;
    String fxml = "login.fxml";


    //loads up the first screen which in our case is the login screen.
    public void load(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(this.fxml)));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Switch to logs screen by clicking a button on screen.
    public void switchToLogs(ActionEvent event) throws IOException {
        LogsController logs = new LogsController();
        logs.load(event);
    }
    //Switch to status screen by clicking a button on screen. Or after completing the login.
    public void switchToStatus(ActionEvent event) throws IOException {
        StatusController status = new StatusController();
        status.load(event);
    }

    //Switch to stats screen by clicking a button on screen.
    public void switchToStats(ActionEvent event) throws IOException {
        StatsController stats = new StatsController();
        stats.load(event);
    }
}
