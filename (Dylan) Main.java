package com.CSCI300;

import java.sql.*;
import java.util.Scanner;

/* MySQL table creation query
CREATE TABLE library.Users (
	user_id int AUTO_INCREMENT,
    username char(255),
    password char(255),
    admin bool,
    PRIMARY KEY (user_id)
);
*/

public class Main {

    public static void main(String[] args) {
        Database.connect();
        setupClosingDBConnection();
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Welcome to the Library Database");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.println("0. Exit");
            System.out.println("Enter selection: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    signup();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }

        } while (choice != 0);





    }

    public static void setupClosingDBConnection() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try { Database.connection.close(); System.out.println("Application Closed - DB Connection Closed");
                } catch (SQLException e) { e.printStackTrace(); }
            }
        }, "Shutdown-thread"));
    }

    public static void login() { // Login function
        try {
            String query = "SELECT Count(*) FROM Users"; // First query to find out how many users are in table
            Connection connection = Database.connection;
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet result = stm.executeQuery();
            result.next();
            int numUsers = result.getInt("COUNT(*)"); // Saves the number of users into numUsers


            String[] usernames = new String[numUsers]; // Array made the size of numUsers to store all usernames
            Scanner input = new Scanner(System.in);
            query = "SELECT username FROM Users"; // Second query to get all usernames in table
            stm = connection.prepareStatement(query);
            result = stm.executeQuery();
            int i = 0;
            while (result.next()) {
                usernames[i] = result.getString("username"); // Saves every username into usernames array
                i++;
            }
            boolean usernameMatch = false; // Boolean to check match logic
            boolean passwordMatch = false; // Boolean to check match logic
            boolean adminStatus = false; // Boolean to check admin status
            String decryptedPassword = "";
            System.out.println("Enter username:");
            String loginusername = input.nextLine(); // Get username from user
            System.out.println("Enter password:");
            String loginpassword = input.nextLine(); // Get password from user

            for (i = 0; i < numUsers; i++) {
                if (loginusername.equals(usernames[i])) {
                    usernameMatch = true; // sets usernameMatch boolean to true if the username the user entered is in the usernames array
                }
            }
            if (usernameMatch) { // Runs query only if a username was found that matches one in the database
                query = "SELECT password, admin FROM Users WHERE username = ?"; // Gets the password and admin status from specific username entered
                stm = connection.prepareStatement(query);
                stm.setString(1, loginusername);
                result = stm.executeQuery();
                while (result.next()) {
                        decryptedPassword = result.getString("password");
                        adminStatus = result.getBoolean("admin");
                    }
                }
            decryptedPassword = EncryptDecrypt.decrypt(decryptedPassword);
            if (decryptedPassword.equals(loginpassword)) {
                passwordMatch = true;
            }

            if (usernameMatch && passwordMatch) {
                System.out.println("Login Successful");
                System.out.println(adminStatus);
            } else {
                System.out.println("Incorrect username or password");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void signup() {
        try {
            Scanner input = new Scanner(System.in);
            Connection connection = Database.connection;
            boolean invalidUser = false;

            System.out.println("Enter your desired username");
            String username = input.nextLine();
            System.out.println("Enter your desired password");
            String password = input.nextLine();

            String query = "SELECT Count(*) FROM Users"; // First query to find out how many users are in table
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet result = stm.executeQuery();
            result.next();
            int numUsers = result.getInt("COUNT(*)"); // Saves the number of users into numUsers


            String[] usernames = new String[numUsers]; // Array made the size of numUsers to store all usernames
            query = "SELECT username FROM Users"; // Second query to get all usernames in table
            stm = connection.prepareStatement(query);
            result = stm.executeQuery();
            int i = 0;
            while (result.next()) {
                usernames[i] = result.getString("username"); // Saves every username into usernames array
                i++;
            }

            for (i = 0; i < numUsers; i++) {
                if (username.equals(usernames[i])) {
                    invalidUser = true; // sets invalidUser boolean to true if the username the user entered is in the usernames array
                }
            }

            if (!invalidUser) {
                query = "INSERT INTO Users VALUES (?, ?, ?, ?)";
                stm = connection.prepareStatement(query);
                stm.setInt(1, 0);
                stm.setString(2, username);
                stm.setString(3, EncryptDecrypt.encrypt(password));
                stm.setBoolean(4, false);
                stm.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }


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
