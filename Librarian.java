package com.CSCI300;
import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Librarian {
    public static void getBooks() {
        try {
            Connection connection = Database.connection; // Connect to database
            String query = "SELECT * FROM books"; // Enter the query
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

    public static void getBorrowedBooks() {
        try {
            Connection connection = Database.connection; // Connect to database
            String query = "SELECT * FROM books WHERE status <> 0"; // Enter the query
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

    public static void addBook(int bookID, String bookName, int authorID, String genre, int publisherID, String bookType, String status) {
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
    public static void deleteBook(int bookID) {
        try {
            Connection connection = Database.connection;
            String query = "DELETE FROM books WHERE book_id = ?";
            PreparedStatement stm = connection.prepareStatement(query);

            stm.setInt(1, bookID); // doctor_id (only question mark)
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addCustomBook() {
        Scanner getInput = new Scanner(System.in);
        System.out.println("Enter Book ID: ");
        int bookID = getInput.nextInt();
        System.out.println("Enter Book Name: ");
        String bookName = getInput.next();
        System.out.println("Enter Author ID: ");
        int authorID = getInput.nextInt();
        System.out.println("Enter genre: ");
        String genre = getInput.next();
        System.out.println("Enter Publisher ID: ");
        int publisherID = getInput.nextInt();
        System.out.println("Enter Book Type (Hardback or softcover): ");
        String bookType = getInput.next();
        System.out.println("Is the book taken by a borrower or is it available?: ");
        String status = getInput.next();
        getInput.close();
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
            System.out.println("The new book was added to the database!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void getLibrarian(){
        try {
            Connection connection = Database.connection; // Connect to database
            String query = "SELECT * FROM librarian"; // Enter the query
            Statement stm = connection.createStatement(); // Create statement
            ResultSet result = stm.executeQuery(query); // Execute the query

            while (result.next()) {
                System.out.println("Librarian ID: " + result.getInt("librarian_id"));
                System.out.println("Librarian Name: " + result.getString("librarian_name"));
                System.out.println("Shift: " + result.getString("shift"));
                System.out.println("Work Days: " + result.getString("work_days"));
                System.out.println("Librarian Status: " + result.getString("librarian_status"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addLibrarian(){
        Scanner getInput = new Scanner(System.in);
        System.out.println("Enter Librarian ID: ");
        int librarianID = getInput.nextInt();
        System.out.println("Enter Librarian Name: ");
        getInput.nextLine(); // We need to have this before the first 'nextLine()' so it doesn't skip any of the later '.nextLine()'
        String librarianName = getInput.next();
        System.out.println("What time does the librarian start working?: ");
        String shift = getInput.next();
        System.out.println("What days of the week does the librarian work?: ");
        String workDays = getInput.next();
        System.out.println("Is the librarian a student, an admin, or a full-time employee?: ");
        String librarianStatus = getInput.next();
        getInput.close();

        try {
            Connection connection = Database.connection;
            String query = "INSERT INTO librarians VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, librarianID);
            stm.setString(2, librarianName);
            stm.setString(3, shift);
            stm.setString(4, workDays);
            stm.setString(5, librarianStatus);
            stm.executeUpdate();
            System.out.println("The new librarian is added to the database!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
