package com.example.demo;

import com.example.demo.model.Stats;
import com.example.demo.model.Stats2;
import com.example.demo.utils.ConnectionDB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class StatsController extends MainController implements Initializable {
    public Stage stage;
    public Scene scene;
    public Parent root;


    @FXML
    public Label time2;
    @FXML public TableView<Stats> tableView;
    @FXML public TableView<Stats2> tableView1;
    @FXML public TableColumn<Stats, String> id;
    @FXML public TableColumn<Stats2, String> id1;
    @FXML public TableColumn<Stats, Date> date;
    @FXML public TableColumn<Stats2, Integer> totalA;
    @FXML public TableColumn<Stats, Integer> ta;
    @FXML public CategoryAxis  Xas;
    @FXML public NumberAxis Yas;
    @FXML public Button refreshData;
    public Connection connection;
    public StatsController() {
        this.fxml = "stats.fxml";
    }
    //Make sure that the list of information is correctly displayed on the stats page
    ObservableList<Stats> listview = FXCollections.observableArrayList();
    ObservableList<Stats2> listview1 = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test start testcontroller");
        loadDataStats();

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    loadDataStats();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        this.runningThreads.add(thread);
    }
    //Load all the data into the stats.
    public void loadData(ActionEvent event) {
        loadDataStats();
    }
    public void loadDataStats(){
        //Tableview1
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ta.setCellValueFactory(new PropertyValueFactory<>("ta"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        //Tableview2
        id1.setCellValueFactory(new PropertyValueFactory<>("id1"));
        totalA.setCellValueFactory(new PropertyValueFactory<>("totalA"));

        try {
            ConnectionDB db  = new ConnectionDB();
            ResultSet result = db.getStats();

            Stats[] stats = new Stats[4];
            int index = 0;
            while (result.next()){
                stats[index] = new Stats(
                        result.getString("lamp_id"),
                        result.getInt("total_activations"),
                        result.getDate("date")
                );
                index++;
//                listview.add(new Stats(
//                        result.getString("lamp_id"),
//                        result.getInt("total_activations"),
//                        result.getDate("date")
//                ));
            }
            listview.setAll(stats);
        } catch (Exception e){
            System.out.println("Database error: " + e.getMessage());
        }
        tableView.setItems(listview);
        try {
            ConnectionDB db1  = new ConnectionDB();
            ResultSet result1 = db1.getStats1();

            Stats2[] stats2 = new Stats2[4];
            int index = 0;
            while (result1.next()){
                stats2[index] = new Stats2(
                        result1.getString("lamp_id"),
                        result1.getInt("total_activations")
                );
                index++;
            }
            listview1.setAll(stats2);
        } catch (Exception e){
            System.out.println("Database error: " + e.getMessage());
        }
        tableView1.setItems(listview1);
    }
}
