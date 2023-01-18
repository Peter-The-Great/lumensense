package com.example.demo;

import com.example.demo.utils.ConnectionDB;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//This is the login controller which we use to make sure the user gets logged in.
public class LoginController extends MainController {
    public Button login;
    @FXML
    private Label time;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label actionTarget;

    //Load in the FXML file we are trying to use to login.
    public LoginController() {
        this.fxml = "login.fxml";
        timenow();
    }

    //This is a login function which checks if we have filled something in the input prompts, then it will check if our username and password is correct,
    //and then send us to the status screen.
    @FXML public void login(ActionEvent event) {
        if (username.getText().equals("")) {
            actionTarget.setText("Please enter a username");
        } else if (password.getText().equals("")) {
            actionTarget.setText("Please enter a password");
        } else {
            // check if password is correct
            ConnectionDB db       = new ConnectionDB();
            boolean authenticated = db.authenticateUser(username.getText(), password.getText());
            if (authenticated) {
                try {
                    LogsController logs = new LogsController();
                    logs.load(event);
                } catch (IOException e) {
                    actionTarget.setText("Internal error occurred");
                    e.printStackTrace();
                }
            }
            actionTarget.setText("Wrong username or password");
        }

    }
    public void timenow(){
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf =  new SimpleDateFormat("HH:mm");
            while(true){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() ->{
                    this.time.setText(timenow);
                });
            }
        });
        thread.start();
    }
}
