package com.CSCI300;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Database.connect();
        setupClosingDBConnection();
        //addBooks();
        //getBooks();
        //delBooks();
        //updateBooks();
        //addAuthors();
        //getAuthors();
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
            int book_id = input.nextInt();
            System.out.println("What is the Author ID");
            int author_id = input.nextInt();
            System.out.println("What is the Book's Title");
            input.nextLine();
            String book_title = input.nextLine();
            System.out.println("What is the Genre?");
            String genre = input.nextLine();
            System.out.println("What is the Publisher ID");
            int publisher_id = input.nextInt();
            System.out.println("Is the book Paperback or Hardcover?");
            input.nextLine();
            String book_type = input.nextLine();
            Connection connection = Database.connection;
            String query = "INSERT INTO Books VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(query);

            stm.setInt(1, book_id);
            stm.setInt(2, author_id);
            stm.setString(3, book_title);
            stm.setString(4, genre);
            stm.setInt(5, publisher_id);
            stm.setString(6, book_type);
            stm.executeUpdate();
            input.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getBooks() {
        try {
            Connection connection = Database.connection;
            String query = "SELECT * FROM Books";
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(query);

            while (result.next()) {
                System.out.println("Book ID: " + result.getInt("book_id"));
                System.out.println("Author ID: " + result.getInt("author_id"));
                System.out.println("Book Title: " + result.getString("book_title"));
                System.out.println("Genre: " + result.getString("genre"));
                System.out.println("Publisher ID: " + result.getInt("publisher_id"));
                System.out.println("Book Type: " + result.getString("book_type"));
                System.out.println("\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void delBooks() {
        try {
            Connection connection = Database.connection;
            Scanner input = new Scanner(System.in);
            System.out.println("What is the ID of the book you want to delete?");
            int id = input.nextInt();
            String query = "DELETE FROM Books WHERE book_id = ?";
            PreparedStatement stm = connection.prepareStatement(query);
            input.close();
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateBooks() {
        try {
            Connection connection = Database.connection;
            Scanner input = new Scanner(System.in);
            System.out.println("What is the book_id you want to edit?");
            int book_id = input.nextInt();

            int choice;
            do {
                System.out.println("1. Edit title");
                System.out.println("2. Edit author_id");
                System.out.println("3. Edit genre");
                System.out.println("4. Edit publisher_id");
                System.out.println("5. Edit type");
                System.out.println("6. Change book id");
                System.out.println("0. Exit");
                System.out.println("Enter choice: ");
                choice = input.nextInt();
                String query;
                switch (choice) {
                    case 1:
                        System.out.println("case 1");
                        try {
                            String title;
                            System.out.println("What would you like to update the title to?");
                            input.nextLine();
                            title = input.nextLine();
                            query = "UPDATE Books SET book_title = ? WHERE book_id = ?";
                            PreparedStatement stm = connection.prepareStatement(query);
                            stm.setString(1, title);
                            stm.setInt(2, book_id);
                            stm.executeUpdate();
                            System.out.println("Update successful");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case 2:
                        try {
                            int author_id;
                            System.out.println("What would you like to update the author_id to?");
                            author_id = input.nextInt();
                            query = "UPDATE Books SET author_id = ? WHERE book_id = ?";
                            PreparedStatement stm = connection.prepareStatement(query);
                            stm.setInt(1, author_id);
                            stm.setInt(2, book_id);
                            stm.executeUpdate();
                            System.out.println("Update successful");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case 3:
                        try {
                            String genre;
                            System.out.println("What would you like to update the genre to?");
                            input.nextLine();
                            genre = input.nextLine();
                            query = "UPDATE Books SET genre = ? WHERE book_id = ?";
                            PreparedStatement stm = connection.prepareStatement(query);
                            stm.setString(1, genre);
                            stm.setInt(2, book_id);
                            stm.executeUpdate();
                            System.out.println("Update Successful");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case 4:
                        try {
                            int publisher_id;
                            System.out.println("What would you like to update the publisher_id to?");
                            publisher_id = input.nextInt();
                            query = "UPDATE Books SET publisher_id = ? WHERE book_id = ?";
                            PreparedStatement stm = connection.prepareStatement(query);
                            stm.setInt(1, publisher_id);
                            stm.setInt(2, book_id);
                            stm.executeUpdate();
                            System.out.println("Update Successful");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case 5:
                        try {
                            String book_type;
                            System.out.println("What would you like to update the book type to?");
                            input.nextLine();
                            book_type = input.nextLine();
                            query = "UPDATE Books SET book_type = ? WHERE book_id = ?";
                            PreparedStatement stm = connection.prepareStatement(query);
                            stm.setString(1, book_type);
                            stm.setInt(2, book_id);
                            stm.executeUpdate();
                            System.out.println("Update successful");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case 6:
                        System.out.println("What is the book_id you want to edit?");
                        book_id = input.nextInt();
                        break;
                    case 0:
                        System.out.println("Finished updating.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose again");
                }
            } while (choice != 0);

            input.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getFromBooks() {

    }

    public static void addAuthors() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("What is the Author ID?");
            int author_id = input.nextInt();
            System.out.println("What is the Publisher ID?");
            int publisher_id = input.nextInt();
            System.out.println("What is the Author's name?");
            input.nextLine();
            String author_name = input.nextLine();
            System.out.println("What is the Genre?");
            String genre = input.nextLine();

            Connection connection = Database.connection;
            String query = "INSERT INTO Authors VALUES (?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(query);

            stm.setInt(1, author_id);
            stm.setInt(2, publisher_id);
            stm.setString(3, author_name);
            stm.setString(4, genre);
            stm.executeUpdate();
            input.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getAuthors() {
        try {
            Connection connection = Database.connection;
            String query = "SELECT * FROM Authors";
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(query);

            while (result.next()) {
                System.out.println("Author ID: " + result.getInt("author_id"));
                System.out.println("Publisher ID: " + result.getInt("publisher_id"));
                System.out.println("Author Name: " + result.getString("author_name"));
                System.out.println("Genre: " + result.getString("genre"));
                System.out.println("\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void delAuthors() {

    }

    public static void editAuthors() {

    }

    public static void getFromAuthors() {

    }


}
