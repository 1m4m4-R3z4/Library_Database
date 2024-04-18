CREATE TABLE library_database.borrower (
borrower_id int,
borrower_name varchar (100),
major varchar (100),
return_date varchar (100),
PRIMARY KEY (patient_id)
);
CREATE TABLE library_database.availability (
availability_id int,
borrower_id int,
number_of_books int, 
number_of_borrows int, 
book_subject varchar(100),
PRIMARY KEY (availability_id),
FOREIGN KEY (borrower_id) REFERENCES borrower(borrower_id)
);