CREATE TABLE library_database.librarian(
librarian_id int,
  librarian_name varchar(255),
  time_shift time,
  work_days date,
  librarian_status varchar(255),
  PRIMARY KEY (librarian_id)
);

CREATE TABLE library_database.publisher(
publisher_id int,
  publisher_name varchar(255),
  focus varchar(255),
  publisher_date date,
  ISBN int,
  PRIMARY KEY (publisher_id)
);

CREATE TABLE library_database.books(
book_id int,
book_title varchar(255),
genre varchar(255),
PRIMARY KEY (book_id),
FOREIGN KEY (publisher_id) REFERENCES publisher(publisher_id),
FOREIGN KEY (librarian_id) REFERENCES librarian(librarian_id)
);
