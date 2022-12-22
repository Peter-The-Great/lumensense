package com.example.demo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        String namedatabase = "lumensense";

        String url ="jdbc:mysql://128.199.63.27/"+namedatabase+"";
        String user ="LumensenseJavaApp";
        String password ="Lumensense#Ln.T(RbbuKpF^.fMgixYd10";

        try {
            Connection connectdata = DriverManager.getConnection(url, user,password);
            System.out.println("SUCCES");
        } catch (SQLException ex) {
            System.out.println("Error");
            ex.printStackTrace();

        }

        launch(args);

    }
    @Override


    public void start(Stage stage) {
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
            Scene scene = new Scene(root);

            Image icon = new Image("file:img/logo.png");
            stage.getIcons().add(icon);

            stage.setTitle("Lumensense");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
