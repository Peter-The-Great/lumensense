package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.transform.Result;


public class TestController implements Initializable {

    @FXML public TableView<TableModel> tableview2;
    @FXML public TableColumn<TableModel, String> id;

    @FXML public TableColumn<TableModel, String> da;

    @FXML public TableColumn<TableModel, String> ia;


    @FXML public TableColumn<TableModel, String> ut;

    @FXML public TableColumn<TableModel, Date> date;

    ObservableList<TableModel> listview = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
       id.setCellValueFactory(new PropertyValueFactory<>("da"));
       id.setCellValueFactory(new PropertyValueFactory<>("ia"));
       id.setCellValueFactory(new PropertyValueFactory<>("ut"));
       id.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            ConnectionDB cn = new ConnectionDB();
            Connection cnl = cn.fileconnection();

            String sql = "SELECT * FROM daily_lamp";
            Statement s = cnl.createStatement();
            ResultSet r = s.executeQuery(sql);

            while (r.next()){
                listview.add(new TableModel(
                        r.getString("lamp_id"),
                        r.getString("direct_activations"),
                        r.getString("indirect_activations"),
                        r.getString("uptime"),
                        r.getDate("date")
                ));
            }
            tableview2.setItems(listview);
        }catch (Exception e){

        }
    }


}
