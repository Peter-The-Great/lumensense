//package com.example.demo;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConnectionDB {
//
//    private String namedatabase = "lumensense";
//
//    private String url = "jdbc:mysql://128.199.63.27/" + namedatabase + "";
//    private String user = "LumensenseJavaApp";
//    private String password = "Lumensense#Ln.T(RbbuKpF^.fMgixYd10";
//
//    public Connection fileconnection() {
//        try {
//
//            Connection connectdata = DriverManager.getConnection(url, user, password);
//            System.out.println("SUCCES");
//        } catch (
//                SQLException ex) {
//            System.out.println("Error");
//            ex.printStackTrace();
//
//        }
//    }
//}
