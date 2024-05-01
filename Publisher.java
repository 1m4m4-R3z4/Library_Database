package com.CSCI300;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Publisher {
    public static void getPublisher(){
        try {
            Connection connection = Database.connection; // Connect to database
            String query = "SELECT * FROM publishers"; // Enter the query
            Statement stm = connection.createStatement(); // Create statement
            ResultSet result = stm.executeQuery(query); // Execute the query

            while (result.next()) {
                System.out.println("Book ID: " + result.getInt("book_id"));
                System.out.println("Book Name: " + result.getString("book_name"));
                System.out.println("Author ID: " + result.getString("author_id"));
                System.out.println("Genre: " + result.getString("genre"));
                System.out.println("Publisher ID: " + result.getInt("publisher_id"));
                System.out.println("Book Type: " + result.getString("book_type"));
                System.out.println("Status: " + result.getString("status"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void addPublisher(int bookID, String bookName, int authorID, String genre, int publisherID, String bookType, String status) {
        try {
            Connection connection = Database.connection;
            String query = "INSERT INTO books VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(query);

            stm.setInt(1, bookID);
            stm.setInt(2, authorID);
            stm.setString(3, bookName);
            stm.setString(4, genre);
            stm.setInt(5, publisherID);
            stm.setString(6, bookType);
            stm.setString(7,status);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
