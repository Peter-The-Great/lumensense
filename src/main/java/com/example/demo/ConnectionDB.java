package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionDB {
    public String namedatabase = "includedatabase";

    private String url ="jdbc:h2:./database/"+namedatabase+";IFEXISTS=TRUE";
    private String user ="";
    private String password ="";

    public Connection fileconnection() {
        try {
            Class.forName("org.hs.Driver");
            Connection connectdata = DriverManager.getConnection(url, user,password);
            System.out.println("SUCCES");
            return connectdata;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "ERROR"+ex.getMessage());
            return null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR"+ex.getMessage());
            return null;
        }
    }
}
