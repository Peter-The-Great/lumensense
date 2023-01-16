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

    public boolean updateLog(String content, String type) {
        try {
            // check if log already exists
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM log WHERE type = ?");
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // update log
                stmt = this.conn.prepareStatement("UPDATE log SET content = ?, updated = now() WHERE type = ?");
            } else {
                // insert log
                stmt = this.conn.prepareStatement("INSERT INTO log (content, type, updated) VALUES (?, ?, now())");
            }

            stmt.setString(1, content);
            stmt.setString(2, type);
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.printf("SQL - Error: " + e.getMessage());
        }

        return false;
    }

    public boolean updateLampStatus(String status, String lamp_id) {
        try {
            // fetch lamp by id to check if it exists
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM lamp WHERE lamp_id = ?");
            stmt.setString(1, lamp_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // update log
                stmt = this.conn.prepareStatement("UPDATE lamp SET status = ? WHERE lamp_id = ?");
            } else {
                // insert log
                stmt = this.conn.prepareStatement("INSERT INTO lamp (status, lamp_id) VALUES (?, ?)");
            }

            stmt.setString(1, status);
            stmt.setString(2, lamp_id);
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.printf("SQL - Error: " + e.getMessage());
        }
        return false;
    }

    public boolean updateActivations(String lamp_id, String directs, String indirects) {

        try {
            // check if daily_lamp exists for lamp_id
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM daily_lamp WHERE lamp_id = ? AND date = CURDATE()");
            stmt.setString(1, lamp_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // update log
                stmt = this.conn.prepareStatement("UPDATE daily_lamp SET direct_activations = ?, indirect_activations = ? WHERE lamp_id = ? AND date = CURDATE()");
            } else {
                // insert log
                stmt = this.conn.prepareStatement("INSERT INTO daily_lamp (direct_activations, indirect_activations, lamp_id, date) VALUES (?, ?, ?, CURDATE())");
            }

            stmt.setString(1, directs);
            stmt.setString(2, indirects);
            stmt.setString(3, lamp_id);

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.printf("SQL - Error: " + e.getMessage());
        }
        return false;
    }
}

