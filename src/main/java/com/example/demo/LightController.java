package com.example.demo;

import javafx.event.ActionEvent;

import java.io.IOException;

public class LightController extends MainController {
    public LightController() {
        this.fxml = "light.fxml";


    public void switchToLogs(ActionEvent event) throws IOException {
        LogsController logs = new LogsController();
        logs.load(event);
    }
    public void switchToLight(ActionEvent event) throws IOException {
        LightController light = new LightController();
        light.load(event);
    }

    public void switchToStats(ActionEvent event) throws IOException {
        StatsController stats = new StatsController();
        stats.load(event);
    }
}
