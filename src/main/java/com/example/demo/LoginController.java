package com.example.demo;

import com.example.demo.utils.ConnectionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController extends MainController {
    public Button login;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label actionTarget;

    public LoginController() {
        this.fxml = "login.fxml";
    }

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
}
