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
                System.out.println("Publisher ID: " + result.getInt("publisher_id"));
                System.out.println("Publisher Name: " + result.getString("publisher_name"));
                System.out.println("Publisher Focus: " + result.getString("publisher_focus"));
                System.out.println("Publish Date: " + result.getString("publish_date"));
                System.out.println("ISBN: " + result.getString("isbn"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void addPublisher() {
        Scanner getInput = new Scanner(System.in);
        System.out.println("Enter Publisher ID: ");
        int publisherID = getInput.nextInt();
        System.out.println("Enter Publisher Name: ");
        String publisherName = getInput.next();
        System.out.println("What subject/genre does the publishing company work for?: ");
        String publisherFocus = getInput.next();
        System.out.println("When was the book published?: ");
        String publisherDate = getInput.next();
        System.out.println("Enter ISBN number: ");
        String isbn = getInput.next();
        try {
            Connection connection = Database.connection;
            String query = "INSERT INTO books VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, publisherID);
            stm.setString(2, publisherName);
            stm.setString(3, publisherFocus);
            stm.setString(4, publisherDate);
            stm.setString(5,isbn);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
