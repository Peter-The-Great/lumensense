package com.example.demo;

import com.example.demo.utils.ConnectionDB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

// This is the logs controller which is pretty small considering we take a bunch of functions from the main controller.
public class LogsController extends MainController implements Initializable {
    @FXML public Label time1;
    @FXML public Label generalid;
    @FXML public Label errorid;
    @FXML public Label networkid;
    @FXML public Label updated1;
    @FXML public Label updated2;
    @FXML public Label updated3;


    public LogsController() {


        this.fxml = "logs.fxml";
//        timenow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("test start testcontroller");

        try {

            ConnectionDB db  = new ConnectionDB();
            ResultSet result = db.getLogs();

            Label[] cnt      = new Label[]{this.generalid, this.errorid, this.networkid};
            Label[] updt      = new Label[]{this.updated1, this.updated2, this.updated3};



            int index = 0;
            while (result.next()){
                System.out.println(result);

                String content = result.getString("content");
                cnt [index].setText(content);

                String updated = result.getString("updated");
                updt[index].setText(updated);
                index++;
            }
        } catch (Exception e){
            System.out.println("Database error: " + e.getMessage());
        }






    }
//    public void timenow(){
//        Thread thread = new Thread(() -> {
//            SimpleDateFormat sdf =  new SimpleDateFormat("HH:mm");
//            while(true){
//                try {
//                    Thread.sleep(1000);
//                }catch (Exception e){
//                    System.out.println(e);
//                }
//                String timenow = sdf.format(new Date());
//                Platform.runLater(() ->{
//                    this.time1.setText(timenow);
//                });
//            }
//        });
//        thread.start();
//    }
}
