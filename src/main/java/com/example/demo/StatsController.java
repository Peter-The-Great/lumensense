package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.xml.transform.Result;


public class StatsController implements Initializable {
    public Stage stage;
    public Scene scene;
    public Parent root;

    @FXML public TableView<Stats> tableView;
    @FXML public TableColumn<Stats, Integer> id;

    @FXML public TableColumn<Stats, Integer> da;

    @FXML public TableColumn<Stats, Integer> ia;


    @FXML public TableColumn<Stats, Time> ut;

    @FXML public TableColumn<Stats, Date> date;

    ObservableList<Stats> listview = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test start testcontroller");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        da.setCellValueFactory(new PropertyValueFactory<>("da"));
        ia.setCellValueFactory(new PropertyValueFactory<>("ia"));
        ut.setCellValueFactory(new PropertyValueFactory<>("ut"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            ConnectionDB db = new ConnectionDB();
            Connection conn = db.getConnection();

            String query        = "SELECT * FROM daily_lamp";
            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery(query);

            while (result.next()){
                System.out.println(result);
                listview.add(new Stats(
                        result.getInt("lamp_id"),
                        result.getInt("direct_activations"),
                        result.getInt("indirect_activations"),
                        result.getTime("uptime"),
                        result.getDate("date")
                ));
            }
            tableView.setItems(listview);
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
