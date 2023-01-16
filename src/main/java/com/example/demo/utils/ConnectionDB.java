package com.example.demo.utils;

import java.sql.*;

public class ConnectionDB {

    public Connection conn = null;

    // main method to test the connection
    public ConnectionDB()  {
        try {
            String dbname   = "lumensense";
            String url      = "jdbc:mysql://128.199.63.27/" + dbname + "";
            String user     = "LumensenseJavaApp";
            String password = "Lumensense#Ln.T(RbbuKpF^.fMgixYd10";

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("SUCCES");
            this.conn = connection;
        } catch (SQLException ex) {
            System.out.println("Error");
            ex.printStackTrace();
        }
    }

    public boolean update(String query) {
        try{
            Statement stmt = this.conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet select(String query) {
        try{
            Statement stmt = this.conn.createStatement();
            return stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Error in select query");
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateLog(String content, String type, String lamp_id) throws SQLException {
        // check if log exists
        ResultSet rs = select("SELECT * FROM log WHERE type = '" + type + "' AND lamp_id = '" + lamp_id + "'");
        if (rs != null && rs.next()) {
            // update log
            return update("UPDATE log SET content = '" + content + "' WHERE type = '" + type + "' AND lamp_id = '" + lamp_id + "'");
        } else {
            // insert log
            return update("INSERT INTO log (content, type, lamp_id) VALUES ('" + content + "', '" + type + "', '" + lamp_id + "')");
        }
    }

    public boolean updateLampStatus(String status, String lamp_id) {
        // check if log exists
        try{
            ResultSet rs = select("SELECT * FROM lamp WHERE lamp_id = '" + lamp_id + "'");
            if (rs != null && rs.next()) {
                System.out.println("Entry found updating...");
                // update log
                return update("UPDATE lamp SET status = '" + status +  "' WHERE lamp_id = '" + lamp_id + "'");
            } else {
                System.out.println("Entry not found inserting");
                // insert log
                return update("INSERT INTO lamp (status, lamp_id) VALUES ('" + status + "','" + lamp_id + "')");
            }
        } catch (SQLException e) {
            System.out.printf("SQL - Error: " + e.getMessage());
        }
        return false;
    }
}

