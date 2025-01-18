CREATE TABLE Publisher (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(50) NOT NULL
);

CREATE TABLE Member (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phoneNumber VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE Book (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publisherId INT NOT NULL,
    publicationYear INT NOT NULL,
    isbn VARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT fk_books_publisher FOREIGN KEY (publisherId) REFERENCES Publisher(id)
);

CREATE TABLE Copy (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    bookId INT NOT NULL,
    copyNumber INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_copies_book FOREIGN KEY (bookId) REFERENCES Book(id)
);

CREATE TABLE Librarian (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    memberId INT NOT NULL,
    employmentDate DATE NOT NULL,
    position VARCHAR(255) NOT NULL,
    CONSTRAINT fk_librarians_user FOREIGN KEY (memberId) REFERENCES Member(id)
);

CREATE TABLE Borrowing (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    memberId INT NOT NULL,
    copyId INT NOT NULL,
    borrowDate DATE NOT NULL,
    returnDate DATE,
    CONSTRAINT fk_borrowings_user FOREIGN KEY (memberId) REFERENCES Member(id),
    CONSTRAINT fk_borrowings_copy FOREIGN KEY (copyId) REFERENCES Copy(id)
);