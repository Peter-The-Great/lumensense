package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class Login extends Main {
    public Button login;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label actionTarget;

    @FXML public void login(ActionEvent event) {
        if (username.getText().equals("")) {
            actionTarget.setText("Please enter a username");
        } else if (password.getText().equals("")) {
            actionTarget.setText("Please enter a password");
        } else {
            actionTarget.setText("Login Successful");
        }

    }
}
