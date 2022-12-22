package com.example.demo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private String namedatabase = "lumensense";

    private String url = "jdbc:mysql://128.199.63.27/" + namedatabase + "";
    private String user = "LumensenseJavaApp";
    private String password = "Lumensense#Ln.T(RbbuKpF^.fMgixYd10";

    public Connection getConnection() {
        try {
//            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("SUCCES");
            return conn;
        } catch (SQLException ex) {
            System.out.println("Error");
            ex.printStackTrace();

//        } catch (ClassNotFoundException ex) {
//           System.out.println("Error");
//            ex.printStackTrace();
//        }

        }
        System.out.println("Error connection db AFTER CATCH");
        return null;
    }
}

