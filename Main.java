package com.CSCI300;
import java.util.*;

import static com.CSCI300.Librarian.*;
import static com.CSCI300.Publisher.addPublisher;
import static com.CSCI300.Publisher.getPublisher;

public class Main {

    public static void main(String[] args) {
        Database.connect();
        Database.setupClosingDBConnection();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Are you a (1) borrower, (2) a librarian, or (3) an Admin?: ");
        int ans;
        do {
            ans = keyboard.nextInt();
            switch (ans) {
                case 1:
                    // Borrower
                    borrowerBookSearch();
                    break;
                case 2:
                    // Librarian
                    librarianBookSearch();
                    break;
                case 3:
                    admin();
                    break;
                default:
                    System.out.println("Incorrect response. Please try again.");
                    break;
            }
        } while (ans != 1 || ans != 2);

    }

    public static void borrowerBookSearch() {
        //This is where the borrower will look through the catalog and select a book based on what subject they choose

    }

    public static void librarianBookSearch() {
        // Print all books
        getBorrowedBooks();
        // Show all books currently being borrowed
        Librarian.getBorrowedBooks();
        // Ask them if they would like to add a new book to the books table
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Would you like to add a new book to the library?");
        String response = keyboard.next();
        while (response.equalsIgnoreCase("yes")) {
            System.out.println("Please enter the unique id number for this book.");
            int bookID = keyboard.nextInt();
            System.out.println("Please enter the name for this book.");
            String bookName = keyboard.next();
            System.out.println("Please enter the author id for this book.");
            int authorID = keyboard.nextInt();
            System.out.println("Please enter the genre of the book.");
            String genre = keyboard.next();
            System.out.println("Please enter the publisher id for this book.");
            int publisherID = keyboard.nextInt();
            System.out.println("Please enter the book type of this book.");
            String bookType = keyboard.next();
            System.out.println("Please enter the status of this book.");
            String status = keyboard.nextLine();
            Librarian.addBook(bookID, bookName, authorID, genre, publisherID, bookType, status);
            System.out.println("Would you like to add another book to the library?");
            response = keyboard.next();
        }
    }

    public static void admin() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Would you like to see the list of publishers from the database");
        // Ask them if they would like to see a list of publishers from the database or add a new librarian to the database
        String whyen = keyboard.next();
        while (whyen.equalsIgnoreCase("yes")) {
            getPublisher();
            System.out.println("Would you like to add a publisher?: ");
            whyen = keyboard.next();
            addPublisher();
            if (whyen.equalsIgnoreCase("no")){
                break;
            }
        }
        String why_en = keyboard.next();
        while (why_en.equalsIgnoreCase("yes")){
                getLibrarian();
            System.out.println("Would you like to add a librarian?: ");
            why_en = keyboard.next();
            addLibrarian();
            if (why_en.equalsIgnoreCase("no")){
                break;
            }
        }
        // Allow them to enter a borrower's ID and print a list of recommended books for them based on their borrowed books

    }

}
