package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

// This is the logs controller which is pretty small considering we take a bunch of functions from the main controller.
public class LogsController extends MainController {
    @FXML
    private Label time;
    public LogsController() {
        this.fxml = "logs.fxml";
        timenow();
    }
    public void timenow(){
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf =  new SimpleDateFormat("HH:mm");
            while(true){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() ->{
                    this.time.setText(timenow);
                });
            }
        });
        thread.start();
    }
}
