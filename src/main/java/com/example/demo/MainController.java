package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    public Stage stage;
    public Scene scene;
    public Parent root;
    String fxml = "login.fxml";

    public void load(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(this.fxml)));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogs(ActionEvent event) throws IOException {
        LogsController logs = new LogsController();
        logs.load(event);
    }
    public void switchToStatus(ActionEvent event) throws IOException {
        StatusController status = new StatusController();
        status.load(event);
    }

    public void switchToStats(ActionEvent event) throws IOException {
        StatsController stats = new StatsController();
        stats.load(event);
    }
}
