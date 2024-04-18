package com.CSCI300;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
class Database {
    public static Connection connection;

    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/library?serverTimezone=EST", "root", "database28");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void setupClosingDBConnection() {
        Runtime.getRuntime().addShutdownHook (new Thread (new Runnable() {
            public void run() {
                try { Database.connection.close(); 
            } catch (SQLException e) {e.printStackTrace(); }
        }
    }, "Shutdown-thread"));
}
    
