package com.CSCI300;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	Database.connect();
      	setupClosingDBConnection();
    	addBooks();
    	
    }

    public static void setupClosingDBConnection() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try { Database.connection.close(); System.out.println("Application Closed - DB Connection Closed");
                } catch (SQLException e) { e.printStackTrace(); }
            }
        }, "Shutdown-thread"));
    }
    
    public static void addBooks() {
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("What is the Book ID");
			int bookid = input.nextInt();
			System.out.println("What is the Author ID");
			int authorid = input.nextInt();
			System.out.println("What is the Book's Title");
			input.nextLine();
			String booktitle = input.nextLine();
			System.out.println("What is the Genre?");
			String genre = input.nextLine();
			System.out.println("What is the Publisher ID");
			int publisherid = input.nextInt();
			System.out.println("Is the book Paperback or Hardcover?");
			input.nextLine();
			String book_type = input.nextLine();
			Connection connection = Database.connection;
			String query = "INSERT INTO Books VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stm = connection.prepareStatement(query);
			
			stm.setInt(1, bookid);
			stm.setInt(2, authorid);
			stm.setString(3, booktitle);
			stm.setString(4, genre);
			stm.setInt(5, publisherid);
			stm.setString(6, book_type);
			stm.executeUpdate();
			input.close();
		} catch (Exception e) {
			System.out.println(e);
    	}
    }
}
