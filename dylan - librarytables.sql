# Creates Borrowers Table
CREATE TABLE library.Borrowers (
	borrower_id int,
    borrower_name varchar(255),
    major varchar(255),
    return_date varchar(255),
    PRIMARY KEY (borrower_id)
);

# Creates Publishers Table
CREATE TABLE library.Publishers (
	publisher_id int,
    publisher_name varchar(255),
    publisher_focus varchar(255),
    publish_date varchar(255), # probably works better under Books table
    isbn varchar(255), # might be better in Books/redundant (book_id is already a unique identifier)
    PRIMARY KEY (publisher_id)
);

# Creates Availability Table
CREATE TABLE library.Availability (
	availability_id int,
    borrower_id int,
	availability_status int,
	book_id int,
    PRIMARY KEY (availability_id),
	FOREIGN KEY (borrower_id) REFERENCES borrower(borrower_id),
	FOREIGN KEY (book_id) REFERENCES Books(book_id)
);

# Creates Librarians table
CREATE TABLE library.Librarians (
	librarian_id int,
    librarian_name varchar(255),
    shift varchar(255),
    work_days varchar(255),
    librarian_status varchar(255), # could be called "student_status" if status is explicitly for students
    PRIMARY KEY (librarian_id)
);

# Creates Authors table (Needs to be run after Publishers table)
CREATE TABLE library.Authors (
	author_id int,
    publisher_id int,
    author_name varchar(255),
    genre varchar(255),
    PRIMARY KEY (author_id),
    FOREIGN KEY (publisher_id) REFERENCES Publishers(publisher_id)
);

# Creates Books table (Needs to be run after Authors and Publishers tables) 
CREATE TABLE library.Books (
	book_id int,
    author_id int,
    book_title varchar(255),
    genre varchar(255),
    publisher_id int,
    book_type varchar(255),
    status varchar(255),
    PRIMARY KEY (book_id),
    FOREIGN KEY (author_id) REFERENCES Authors(author_id),
    FOREIGN KEY (publisher_id) REFERENCES Publishers(publisher_id)
);

INSERT INTO library.Publishers 
VALUES (421, 'Books INC.', 'Children\'s Books', '03/10/2002', '934-3-5-123');

INSERT INTO library.Authors
VALUES (13, 421, 'John Doe', 'Children\'s books');

INSERT INTO library.Books 
VALUES (1, 13, 'Give A Mouse A Cookie', 'Children\'s books', 421, 'Paperback');

INSERT INTO library.Borrowers 
VALUES (4, 'Patrick Star', 'Business', '03/12/2014');

SELECT borrower_name FROM library.Borrowers;
