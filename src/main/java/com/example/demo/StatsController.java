package com.example.demo;

import com.example.demo.model.Stats;
import com.example.demo.utils.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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


    @FXML public TableView<Stats> tableView;
    @FXML public TableColumn<Stats, String> id;
    @FXML public TableColumn<Stats, String> id1;

    @FXML public TableColumn<Stats, Integer> da;

    @FXML public TableColumn<Stats, Integer> directA;

    @FXML public TableColumn<Stats, Integer> ia;

    @FXML public TableColumn<Stats, Integer> indirectA;

    @FXML public TableColumn<Stats, Date> date;
    @FXML public TableColumn<Stats, Integer> totalA;
    @FXML public TableColumn<Stats, Integer> ta;

    @FXML public TableView<Stats> tableView1;

    @FXML public CategoryAxis  Xas;
    @FXML public NumberAxis Yas;
    @FXML public BarChart idBar;


    @FXML public Button refreshData;
    public Connection connection;





    ObservableList<Stats> listview = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("test start testcontroller");
        //Tableview1
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        da.setCellValueFactory(new PropertyValueFactory<>("da"));
        ia.setCellValueFactory(new PropertyValueFactory<>("ia"));
        ta.setCellValueFactory(new PropertyValueFactory<>("ta"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        //Tableview2
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        directA.setCellValueFactory(new PropertyValueFactory<>("da"));
        indirectA.setCellValueFactory(new PropertyValueFactory<>("ia"));
        totalA.setCellValueFactory(new PropertyValueFactory<>("ta"));
        //Barchart
        Xas.setUserData(new PropertyValueFactory<>("id"));
        Yas.setUserData(new PropertyValueFactory<>("ta"));





        try {
            ConnectionDB db = new ConnectionDB();
            Connection conn = db.conn;

            String query        = "SELECT * FROM daily_lamp\n" +
                                  "WHERE date = CURDATE()";
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery(query);



            while (result.next()){
                System.out.println(result);
                listview.add(new Stats(
                        result.getString("lamp_id"),
                        result.getInt("direct_activations"),
                        result.getInt("indirect_activations"),
                        result.getInt("total_activations"),
                        result.getDate("date")


                ));
            }
            tableView.setItems(listview);
            tableView1.setItems(listview);


        } catch (Exception e){
            System.out.println("Database error: " + e.getMessage());
        }


    }
    public void loadData(ActionEvent event) {
        tableView.refresh();
        tableView1.refresh();


    }
    public void switchToLogs(ActionEvent event) throws IOException {
        LogsController logs = new LogsController();
        logs.load(event);
    }
    public void switchToLight(ActionEvent event) throws IOException {
        LightController light = new LightController();
        light.load(event);
    }
}
