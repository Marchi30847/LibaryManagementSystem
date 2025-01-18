INSERT INTO Publisher (name, address, phoneNumber) VALUES ('Penguin Random House', '123 Publisher Lane, NY', '1234567890');
INSERT INTO Publisher (name, address, phoneNumber) VALUES ('HarperCollins', '456 Harper St, NY', '2345678901');
INSERT INTO Publisher (name, address, phoneNumber) VALUES ('Simon & Schuster', '789 Simon Blvd, NY', '3456789012');
INSERT INTO Publisher (name, address, phoneNumber) VALUES ('Macmillan', '101 Macmillan Ave, NY', '4567890123');
INSERT INTO Publisher (name, address, phoneNumber) VALUES ('Hachette', '202 Hachette Rd, NY', '5678901234');

INSERT INTO Member (name, email, phoneNumber, address) VALUES ('John Doe', 'john.doe@example.com', '1234567890', '123 Main St, NY');
INSERT INTO Member (name, email, phoneNumber, address) VALUES ('Jane Smith', 'jane.smith@example.com', '2345678901', '456 Elm St, NY');
INSERT INTO Member (name, email, phoneNumber, address) VALUES ('Alice Johnson', 'alice.johnson@example.com', '3456789012', '789 Oak St, NY');
INSERT INTO Member (name, email, phoneNumber, address) VALUES ('Bob Brown', 'bob.brown@example.com', '4567890123', '101 Pine St, NY');
INSERT INTO Member (name, email, phoneNumber, address) VALUES ('Charlie White', 'charlie.white@example.com', '5678901234', '202 Maple St, NY');

INSERT INTO Book (title, author, publisherId, publicationYear, isbn) VALUES ('Book One', 'Author A', 1, 2001, '978-1234567890');
INSERT INTO Book (title, author, publisherId, publicationYear, isbn) VALUES ('Book Two', 'Author B', 2, 2002, '978-2345678901');
INSERT INTO Book (title, author, publisherId, publicationYear, isbn) VALUES ('Book Three', 'Author C', 3, 2003, '978-3456789012');
INSERT INTO Book (title, author, publisherId, publicationYear, isbn) VALUES ('Book Four', 'Author D', 4, 2004, '978-4567890123');
INSERT INTO Book (title, author, publisherId, publicationYear, isbn) VALUES ('Book Five', 'Author E', 5, 2005, '978-5678901234');

INSERT INTO Copy (bookId, copyNumber, status) VALUES (1, 1, 'Available');
INSERT INTO Copy (bookId, copyNumber, status) VALUES (2, 1, 'Borrowed');
INSERT INTO Copy (bookId, copyNumber, status) VALUES (3, 1, 'Available');
INSERT INTO Copy (bookId, copyNumber, status) VALUES (4, 1, 'Available');
INSERT INTO Copy (bookId, copyNumber, status) VALUES (5, 1, 'Borrowed');

INSERT INTO Librarian (memberId, employmentDate, position) VALUES (1, DATE '2020-01-01', 'Manager');
INSERT INTO Librarian (memberId, employmentDate, position) VALUES (2, DATE '2021-02-01', 'Assistant');
INSERT INTO Librarian (memberId, employmentDate, position) VALUES (3, DATE '2019-03-01', 'Technician');
INSERT INTO Librarian (memberId, employmentDate, position) VALUES (4, DATE '2018-04-01', 'Clerk');
INSERT INTO Librarian (memberId, employmentDate, position) VALUES (5, DATE '2017-05-01', 'Cataloguer');

INSERT INTO Borrowing (memberId, copyId, borrowDate, returnDate) VALUES (1, 2, DATE '2023-01-01', NULL);
INSERT INTO Borrowing (memberId, copyId, borrowDate, returnDate) VALUES (2, 5, DATE '2023-02-01', NULL);
INSERT INTO Borrowing (memberId, copyId, borrowDate, returnDate) VALUES (3, 4, DATE '2023-03-01', DATE '2023-04-01');
INSERT INTO Borrowing (memberId, copyId, borrowDate, returnDate) VALUES (4, 3, DATE '2023-05-01', NULL);
INSERT INTO Borrowing (memberId, copyId, borrowDate, returnDate) VALUES (5, 1, DATE '2023-06-01', DATE '2023-07-01');
COMMIT;