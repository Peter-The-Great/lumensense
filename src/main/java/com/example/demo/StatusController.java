package com.example.demo;

import com.example.demo.utils.ConnectionDB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
// This is the status controller with all the labels we need to change when the application is running.
public class StatusController extends MainController implements Initializable {
    public Stage stage;
    public Scene scene;
    public Parent root;
    @FXML
    public Label time3;
    @FXML
    public Label ID1;
    @FXML
    public Label status1;
    @FXML
    public Label id2;
    @FXML
    public Label status2;
    @FXML
    public Label id3;
    @FXML
    public Label status3;
    @FXML
    public Label id4;
    @FXML
    public Label status4;

    @FXML
    public Button DataRefresh;
    public Connection connection;

    public StatusController() {
        this.fxml = "status.fxml";
    }
    //Connect to the database and put all the labels within an array
    public void initialize(URL location, ResourceBundle resources) {
        dataRefresher();

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    dataRefresher();
                } catch (Exception e) {
                    System.out.println("Error - " + e.getMessage());
                }
            }
        });
        thread.start();
        this.runningThreads.add(thread);
    }
    public void dataRefresher(){
        try {
            ConnectionDB db  = new ConnectionDB();
            ResultSet result = db.getStatus();

            Label[] ids      = new Label[]{this.ID1, this.id4, this.id3, this.id2};
            Label[] statuses = new Label[]{this.status1, this.status2, this.status3, this.status4};

            //If you get a result from the database fill it with the lampids and statuses. Until you have got them all.
            int index = 0;
            while (result.next()){
                String lamp_id = result.getString("lamp_id");
                String status = result.getString("status");

                int finalIndex = index;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ids[finalIndex].setText(lamp_id);
                        statuses[finalIndex].setText(status);
                    }
                });

                if (lamp_id.equals("RED")){
                    ids[index].setTextFill(Color.RED);
                } else if (lamp_id.equals("GREEN")) {
                    ids[index].setTextFill(Color.GREEN);

                }else if (lamp_id.equals("YELLOW")) {
                    ids[index].setTextFill(Color.YELLOW);

                }else {
                    ids[index].setTextFill(Color.BLACK);
                }

                if (status.equals("ON")){
                    statuses[index].setTextFill(Color.LIMEGREEN);
                }else {
                    statuses[index].setTextFill(Color.RED);
                }

                index++;
            }
        } catch (Exception e){
            System.out.println("Database error: " + e.getMessage());
        }
    }


}
