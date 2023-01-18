package com.example.demo;

import com.example.demo.utils.ConnectionDB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

// This is the logs controller which is pretty small considering we take a bunch of functions from the main controller.
public class LogsController extends MainController implements Initializable {
    @FXML
    public Label time1;
    @FXML
    public Button verversData;
    @FXML
    public TextArea generalid;
    @FXML
    public TextArea errorid;
    @FXML
    public TextArea networkid;
    @FXML
    public Label updated1;
    @FXML
    public Label updated2;
    @FXML
    public Label updated3;
    public LogsController() {
        this.fxml = "logs.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("test start testcontroller");
        this.loadLogs();
    }

    public void reloadLogs(ActionEvent event) {
        System.out.println("test start testcontroller");
        this.loadLogs();
    }

    public void loadLogs() {

        try {
            ConnectionDB db = new ConnectionDB();
            ResultSet result = db.getLogs();

            TextArea[] cnt = new TextArea[]{this.generalid, this.networkid, this.errorid};
            Label[] updt = new Label[]{this.updated1, this.updated2, this.updated3};


            int index = 0;
            while (result.next()) {
                System.out.println(result);

                String content = result.getString("content");
                cnt[index].setText(content);

                String updated = result.getString("updated");
                updt[index].setText(updated);
                index++;
            }
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

}
