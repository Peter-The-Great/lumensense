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

    @FXML public TableColumn<Stats, Integer> da;

    @FXML public TableColumn<Stats2, Integer> directA;

    @FXML public TableColumn<Stats, Integer> ia;

    @FXML public TableColumn<Stats2, Integer> indirectA;

    @FXML public TableColumn<Stats, Date> date;
    @FXML public TableColumn<Stats2, Integer> totalA;
    @FXML public TableColumn<Stats, Integer> ta;



    @FXML public CategoryAxis  Xas;
    @FXML public NumberAxis Yas;
    @FXML public BarChart idBar;


    @FXML public Button refreshData;
    public Connection connection;

    public StatsController() {
        this.fxml = "stats.fxml";
//        timenow();
    }



    //Make sure that the list of information is correctly displayed on the stats page
    ObservableList<Stats> listview = FXCollections.observableArrayList();
    ObservableList<Stats2> listview1 = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        System.out.println("test start testcontroller");
        //Tableview1
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ta.setCellValueFactory(new PropertyValueFactory<>("ta"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        //Tableview2
        id1.setCellValueFactory(new PropertyValueFactory<>("id1"));
        totalA.setCellValueFactory(new PropertyValueFactory<>("totalA"));
        //Barchart
//        Xas.setUserData(new PropertyValueFactory<>("id"));
//        Yas.setUserData(new PropertyValueFactory<>("ta"));





        //Make a query to get all the daily lamps where the date is today.
        try {
            ConnectionDB db  = new ConnectionDB();
            ResultSet result = db.getStats();

            while (result.next()){
                System.out.println(result);
                listview.add(new Stats(
                        result.getString("lamp_id"),
                        result.getInt("total_activations"),
                        result.getDate("date")


                ));
            }
        } catch (Exception e){
            System.out.println("Database error: " + e.getMessage());
        }

        tableView.setItems(listview);

            try {
                ConnectionDB db1  = new ConnectionDB();
                ResultSet result1 = db1.getStats1();

                while (result1.next()){
                    System.out.println(result1);
                    listview1.add(new Stats2(
                            result1.getString("lamp_id"),
                            result1.getInt("total_activations")
                    ));
                }



        } catch (Exception e){
            System.out.println("Database error: " + e.getMessage());
        }
        tableView1.setItems(listview1);
//            idBar.setData(Xas);


    }
    //Load all the data into the stats.
    public void loadData(ActionEvent event) {
        tableView.refresh();
        tableView1.refresh();
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
//                final String timenow = sdf.format(new Date());
//                Platform.runLater(() ->{
//                    this.time2.setText(timenow);
//                });
//            }
//        });
//        thread.start();
//    }
}
