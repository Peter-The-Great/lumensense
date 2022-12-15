package com.example.demo;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class DBController implements Initializable{



    @Override
    public void initialize(URL url, ResourceBundle resources) {

        try {
            ConnectionDB cn = new ConnectionDB();
            Connection cnl = cn.fileconnection();


        }catch (Exception e){

        }
    }
}
