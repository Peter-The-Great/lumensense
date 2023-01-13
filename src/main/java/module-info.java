module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires bcrypt;
    requires LumenSerial;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}