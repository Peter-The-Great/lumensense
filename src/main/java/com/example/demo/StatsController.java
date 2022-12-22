package com.example.demo;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StatsController implements Initializable {

    //Table
    @FXML public TableView<Stats> tableView;
    @FXML public TableColumn<Stats, String> idColomn;

    @FXML public TableColumn<Stats, String> dActivationsColomn;

    @FXML public TableColumn<Stats, String> iActivationsColomn;

    //@FXML public TableColumn<Stats, String> dateColomn;
    @FXML public TableColumn<Stats, String> uptimeColomn;


    public Stage stage;
    public Scene scene;
    public Parent root;

    public void switchToLight(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToStats(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("stats.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLogs(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("logs.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColomn.setCellValueFactory(new PropertyValueFactory<Stats, String>("id"));
        dActivationsColomn.setCellValueFactory(new PropertyValueFactory<Stats, String>("dActivations"));
        iActivationsColomn.setCellValueFactory(new PropertyValueFactory<Stats, String>("iActivations"));
        uptimeColomn.setCellValueFactory(new PropertyValueFactory<Stats, String>("uptime"));
        //dateColomn.setCellValueFactory(new PropertyValueFactory<Stats, String>("uptime"));

        tableView.setItems(getPeople());
    }
    public ObservableList<Stats> getPeople(){
        ObservableList<Stats> people = FXCollections.observableArrayList();
        people.add(new Stats("001", "001", "003","12:00:04"));
        people.add(new Stats("002", "002", "003","13:00:04"));
        people.add(new Stats("003", "003", "003","11:00:35"));
        people.add(new Stats("004", "005", "003","00:00:00"));
        people.add(new Stats("005", "006", "003","06:00:49"));
        people.add(new Stats("006", "007", "003","07:00:23"));
        people.add(new Stats("007", "008", "003","09:00:23"));
        return people;

    }
}