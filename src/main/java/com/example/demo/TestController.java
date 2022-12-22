//package com.example.demo;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.Initializable;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.Time;
//import java.util.Date;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//import javax.xml.transform.Result;
//
//
//public class TestController implements Initializable {
//
//    @FXML public TableView<TableModel> tableview2;
//    @FXML public TableColumn<TableModel, Integer> id;
//
//    @FXML public TableColumn<TableModel, Integer> da;
//
//    @FXML public TableColumn<TableModel, Integer> ia;
//
//
//    @FXML public TableColumn<TableModel, Time> ut;
//
//    @FXML public TableColumn<TableModel, Date> date;
//
//    ObservableList<TableModel> listview = FXCollections.observableArrayList();
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println("test start testcontroller");
//       id.setCellValueFactory(new PropertyValueFactory<>("id"));
//       da.setCellValueFactory(new PropertyValueFactory<>("da"));
//       ia.setCellValueFactory(new PropertyValueFactory<>("ia"));
//       ut.setCellValueFactory(new PropertyValueFactory<>("ut"));
//       date.setCellValueFactory(new PropertyValueFactory<>("date"));
//
//        try {
//            ConnectionDB db = new ConnectionDB();
//            Connection conn = db.getConnection();
//
//            String query        = "SELECT * FROM daily_lamp";
//            Statement statement = conn.createStatement();
//            ResultSet result    = statement.executeQuery(query);
//
//            while (result.next()){
//                System.out.println(result);
//                listview.add(new TableModel(
//                        result.getInt("lamp_id"),
//                        result.getInt("direct_activations"),
//                        result.getInt("indirect_activations"),
//                        result.getTime("uptime"),
//                        result.getDate("date")
//                ));
//            }
//            tableview2.setItems(listview);
//        } catch (Exception e){
//            System.out.println("Database error: " + e.getMessage());
//        }
//    }
//
//
//}
