package com.example.demo;

import com.example.demo.utils.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class StatsController implements Initializable {
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
    @FXML public TableColumn<Stats, Date> totalU;

    @FXML public BarChart <String, Integer>  barChart;
    @FXML public TableView<Stats> tableView1;

    @FXML public Button refreshData;
    public Connection connection;





    ObservableList<Stats> listview = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("test start testcontroller");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        da.setCellValueFactory(new PropertyValueFactory<>("da"));
        ia.setCellValueFactory(new PropertyValueFactory<>("ia"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        directA.setCellValueFactory(new PropertyValueFactory<>("da"));
        indirectA.setCellValueFactory(new PropertyValueFactory<>("ia"));
        totalU.setCellValueFactory(new PropertyValueFactory<>("date"));



        try {
            ConnectionDB db = new ConnectionDB();
            Connection conn = db.getConnection();

            String query        = "SELECT * FROM daily_lamp";
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery(query);



            while (result.next()){
                System.out.println(result);
                listview.add(new Stats(
                        result.getString("lamp_id"),
                        result.getInt("direct_activations"),
                        result.getInt("indirect_activations"),
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
        XYChart.Series<String, Integer> series = new XYChart.Series();
        try {
            ConnectionDB db = new ConnectionDB();
            Connection conn = db.getConnection();

            String query        = "SELECT * FROM daily_lamp";
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery(query);
            while (result.next()){
                series.getData().add(new XYChart.Data<>(
                        result.getString("lamp_id"),
                        result.getInt("direct_activations")


                ));



            }
            barChart.getData().addAll(series);
            /*this.barChart.setData(series);*/



        } catch (Exception e){
            System.out.println("Database error: " + e.getMessage());
        }
    }


    public void switchToLogs(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("logs.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToLight(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
