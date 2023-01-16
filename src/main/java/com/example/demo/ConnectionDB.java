package com.example.demo;

import java.sql.*;

public class ConnectionDB {

    private static Connection conn;

    public Connection getConnection() {
        try {
            String dbname   = "lumensense";
            String url      = "jdbc:mysql://128.199.63.27/" + dbname + "";
            String user     = "LumensenseJavaApp";
            String password = "Lumensense#Ln.T(RbbuKpF^.fMgixYd10";

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("SUCCES");
            conn = connection;
            return connection;
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

    public static boolean update(String query) {
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ResultSet select(String query) {
        try{
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error in select query");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateLog(String content, String type, String lamp_id) throws SQLException {
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
}

