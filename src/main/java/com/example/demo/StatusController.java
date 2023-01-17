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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class StatusController implements Initializable {
    public Stage stage;
    public Scene scene;
    public Parent root;


    @FXML
    public static Label ID1;
    @FXML
    public Label status1;
    @FXML
    public Label id2;
    @FXML
    public Label status2;
    @FXML
    public Label id3;
    @FXML
    public Label status3;
    @FXML
    public Label id4;
    @FXML
    public Label status4;
    @FXML
    public Button DataRefresh;


    public Connection connection;



    public void initialize(URL location, ResourceBundle resources) {

        System.out.println("test start testcontroller");




        try {
            ConnectionDB db = new ConnectionDB();
            Connection conn = db.conn;

            String query        = "SELECT * FROM lamp where lamp_id in ('BLACK', 'GREEN','YELLOW','RED')";

            Statement statement = conn.createStatement();
            ResultSet result    = statement.executeQuery(query);


            while (result.next()){
                System.out.println(result);
                String ida = result.getString("lamp_id");
                ID1.setText(ida);
                String sta = result.getString("status");
                status1.setText(sta);



                String idb = result.getString("lamp_id");
                id2.setText(idb);
                String stb = result.getString("status");
                status2.setText(stb);



                String idc = result.getString("lamp_id");
                id3.setText(idc);
                String stc = result.getString("status");
                status3.setText(stc);


                String idd = result.getString("lamp_id");
                id4.setText(idd);
                String std = result.getString("status");
                status4.setText(std);

            }



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
