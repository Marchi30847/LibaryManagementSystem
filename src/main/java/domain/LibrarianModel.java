package domain;

import data.dao.BookDAO;
import data.dao.BorrowingDAO;
import data.dao.CopyDAO;
import data.dao.LibrarianDAO;
import data.dao.MemberDAO;
import data.dao.PublisherDAO;
import data.dependencies.LibrarianContract;
import data.entities.Book;
import data.entities.Borrowing;
import data.entities.Copy;
import data.entities.Librarian;
import data.entities.Member;
import data.entities.Publisher;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The LibrarianModel class implements the LibrarianContract.Model interface.
 * It provides methods to manage and manipulate data related to books, borrowings, copies, librarians,
 * members, and publishers in the library system.
 */
public class LibrarianModel implements LibrarianContract.Model {

    private final BookDAO bookDAO = new BookDAO();
    private final BorrowingDAO borrowingDAO = new BorrowingDAO();
    private final CopyDAO copyDAO = new CopyDAO();
    private final LibrarianDAO librarianDAO = new LibrarianDAO();
    private final MemberDAO memberDAO = new MemberDAO();
    private final PublisherDAO publisherDAO = new PublisherDAO();

    /**
     * A generic method to map entities to column data for display.
     * This method retrieves values of fields from the provided list of entities
     * and organizes them into a 2D array.
     *
     * @param <T> the type of the entity
     * @param columnNames the column names to be displayed
     * @param entities the list of entities to be mapped
     * @param entityClass the class of the entity type
     * @return a 2D array of String values representing the mapped entities
     */
    private <T> String[][] mapEntitiesToColumns(List<String> columnNames, List<T> entities, Class<T> entityClass) {
        String[][] result = new String[entities.size() + 1][columnNames.size()];

        // Setting column names in the first row
        for (int i = 0; i < columnNames.size(); i++) {
            result[0][i] = columnNames.get(i);
        }

        // Populating the entity values in subsequent rows
        for (int j = 0; j < entities.size(); j++) {
            T entity = entities.get(j);

            for (int i = 0; i < columnNames.size(); i++) {
                String columnName = columnNames.get(i);

                try {
                    Field field = entityClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    Object value = field.get(entity);
                    result[j + 1][i] = value != null ? value.toString() : "null";
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Error accessing field: " + columnName, e);
                }
            }
        }

        return result;
    }

    /**
     * Retrieves data for all members in the library.
     * This method fetches all members and returns them as a 2D array of strings.
     * Each row represents a member with their corresponding details.
     *
     * @return a 2D array of strings representing all members and their details
     */
    @Override
    public String[][] getMembersData() {
        List<String> columnNames = memberDAO.getColumnNames();
        List<Member> members = memberDAO.getAll();
        return mapEntitiesToColumns(columnNames, members, Member.class);
    }

    /**
     * Retrieves data for all books in the library.
     * This method fetches all books and returns them as a 2D array of strings.
     * Each row represents a book with its corresponding details.
     *
     * @return a 2D array of strings representing all books and their details
     */
    @Override
    public String[][] getBooksData() {
        List<String> columnNames = bookDAO.getColumnNames();
        List<Book> books = bookDAO.getAll();
        return mapEntitiesToColumns(columnNames, books, Book.class);
    }

    /**
     * Retrieves data for all borrowings in the library.
     * This method fetches all borrowings and returns them as a 2D array of strings.
     * Each row represents a borrowing with its corresponding details.
     *
     * @return a 2D array of strings representing all borrowings and their details
     */
    @Override
    public String[][] getBorrowingsData() {
        List<String> columnNames = borrowingDAO.getColumnNames();
        List<Borrowing> borrowings = borrowingDAO.getAll();
        return mapEntitiesToColumns(columnNames, borrowings, Borrowing.class);
    }

    /**
     * Retrieves data for all copies in the library.
     * This method fetches all copies and returns them as a 2D array of strings.
     * Each row represents a copy with its corresponding details.
     *
     * @return a 2D array of strings representing all copies and their details
     */
    @Override
    public String[][] getCopiesData() {
        List<String> columnNames = copyDAO.getColumnNames();
        List<Copy> copies = copyDAO.getAll();
        return mapEntitiesToColumns(columnNames, copies, Copy.class);
    }

    /**
     * Retrieves data for all librarians in the library.
     * This method fetches all librarians and returns them as a 2D array of strings.
     * Each row represents a librarian with their corresponding details.
     *
     * @return a 2D array of strings representing all librarians and their details
     */
    @Override
    public String[][] getLibrariansData() {
        List<String> columnNames = librarianDAO.getColumnNames();
        List<Librarian> librarians = librarianDAO.getAll();
        return mapEntitiesToColumns(columnNames, librarians, Librarian.class);
    }

    /**
     * Retrieves data for all publishers in the library.
     * This method fetches all publishers and returns them as a 2D array of strings.
     * Each row represents a publisher with their corresponding details.
     *
     * @return a 2D array of strings representing all publishers and their details
     */
    @Override
    public String[][] getPublishersData() {
        List<String> columnNames = publisherDAO.getColumnNames();
        List<Publisher> publishers = publisherDAO.getAll();
        return mapEntitiesToColumns(columnNames, publishers, Publisher.class);
    }

    // Methods to insert new data into the system.

    /**
     * Inserts a new book into the library system.
     *
     * @param title the title of the book
     * @param author the author of the book
     * @param publisherId the publisher's ID
     * @param year the publication year
     * @param isbn the ISBN of the book
     */
    @Override
    public void insertBook(String title , String author, String publisherId, String year, String isbn) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisherDAO.getById(Integer.parseInt(publisherId)));
        book.setPublicationYear(Integer.parseInt(year));
        book.setIsbn(isbn);

        bookDAO.save(book);
    }

    /**
     * Inserts a new borrowing record into the library system.
     *
     * @param memberId the member's ID
     * @param copyId the copy's ID
     */
    @Override
    public void insertBorrowing(String memberId, String copyId) {
        Borrowing borrowing = new Borrowing();
        borrowing.setMember(memberDAO.getById(Integer.parseInt(memberId)));
        borrowing.setCopy(copyDAO.getById(Integer.parseInt(copyId)));
        borrowing.setBorrowDate(new Date());

        borrowingDAO.save(borrowing);
    }

    /**
     * Inserts a new copy into the library system.
     *
     * @param bookId the book's ID
     * @param copyNumber the copy number
     * @param condition the condition of the copy
     */
    @Override
    public void insertCopy(String bookId, String copyNumber, String condition) {
        Copy copy = new Copy();
        copy.setBook(bookDAO.getById(Integer.parseInt(bookId)));
        copy.setCopyNumber(Integer.parseInt(copyNumber));
        copy.setStatus(condition);

        copyDAO.save(copy);
    }

    /**
     * Inserts a new librarian into the library system.
     *
     * @param memberId the member's ID
     * @param position the librarian's position
     */
    @Override
    public void insertLibrarian(String memberId, String position) {
        Librarian librarian = new Librarian();
        librarian.setMember(memberDAO.getById(Integer.parseInt(memberId)));
        librarian.setPosition(position);

        librarianDAO.save(librarian);
    }

    /**
     * Inserts a new member into the library system.
     *
     * @param name the member's name
     * @param email the member's email
     * @param phone the member's phone number
     * @param address the member's address
     */
    @Override
    public void insertMember(String name, String email, String phone, String address) {
        Member user = new Member();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setAddress(address);

        memberDAO.save(user);
    }

    /**
     * Inserts a new publisher into the library system.
     *
     * @param name the publisher's name
     * @param address the publisher's address
     * @param phone the publisher's phone number
     */
    @Override
    public void insertPublisher(String name, String address, String phone) {
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisher.setAddress(address);
        publisher.setPhoneNumber(phone);

        publisherDAO.save(publisher);
    }

    // Methods to delete records from the system.

    /**
     * Deletes a book from the library system.
     *
     * @param bookRow the row number of the book to be deleted
     */
    @Override
    public void deleteBook(int bookRow) {
        int bookId = bookDAO.getByRow(bookRow).getId();
        bookDAO.delete(bookId);
    }

    /**
     * Deletes a borrowing from the library system.
     *
     * @param borrowingRow the row number of the borrowing to be deleted
     */
    @Override
    public void deleteBorrowing(int borrowingRow) {
        int borrowingId = borrowingDAO.getByRow(borrowingRow).getId();
        borrowingDAO.delete(borrowingId);
    }

    /**
     * Deletes a copy from the library system.
     *
     * @param copyRow the row number of the copy to be deleted
     */
    @Override
    public void deleteCopy(int copyRow) {
        int copyId = copyDAO.getByRow(copyRow).getId();
        copyDAO.delete(copyId);
    }

    /**
     * Deletes a librarian from the library system.
     *
     * @param librarianRow the row number of the librarian to be deleted
     */
    @Override
    public void deleteLibrarian(int librarianRow) {
        int librarianId = librarianDAO.getByRow(librarianRow).getId();
        librarianDAO.delete(librarianId);
    }

    /**
     * Deletes a member from the library system.
     *
     * @param memberRow the row number of the member to be deleted
     */
    @Override
    public void deleteMember(int memberRow) {
        int memberId = memberDAO.getByRow(memberRow).getId();
        memberDAO.delete(memberId);
    }

    /**
     * Deletes a publisher from the library system.
     *
     * @param publisherRow the row number of the publisher to be deleted
     */
    @Override
    public void deletePublisher(int publisherRow) {
        int publisherId = publisherDAO.getByRow(publisherRow).getId();
        publisherDAO.delete(publisherId);
    }

    /**
     * Updates the details of an existing book in the system.
     *
     * @param id The ID of the book to be updated.
     * @param title The new title of the book.
     * @param author The new author of the book.
     * @param publisherId The ID of the publisher for the book.
     * @param year The publication year of the book.
     * @param isbn The ISBN number of the book.
     * @throws IllegalArgumentException if the book with the specified ID does not exist.
     */
    @Override
    public void updateBook(int id, String title , String author, String publisherId, String year, String isbn) {
        Book book = bookDAO.getById(id);

        if (book == null) {
            throw new IllegalArgumentException("Book with ID " + id + " does not exist.");
        }

        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisherDAO.getById(Integer.parseInt(publisherId)));
        book.setPublicationYear(Integer.parseInt(year));
        book.setIsbn(isbn);

        bookDAO.update(book);
    }

    /**
     * Updates the details of an existing borrowing record in the system.
     *
     * @param id The ID of the borrowing record to be updated.
     * @param memberId The ID of the member who borrowed the book.
     * @param copyId The ID of the copy of the book that was borrowed.
     * @param borrowDate The new borrow date in the format "yyyy-MM-dd".
     * @param returnDate The new return date in the format "yyyy-MM-dd".
     * @throws IllegalArgumentException if the borrowing record with the specified ID does not exist.
     * @throws IllegalArgumentException if the date format is invalid.
     */
    @Override
    public void updateBorrowing(int id, String memberId, String copyId, String borrowDate, String returnDate) {
        Borrowing borrowing = borrowingDAO.getById(id);

        if (borrowing == null) {
            throw new IllegalArgumentException("Borrowing with ID " + id + " does not exist.");
        }

        borrowing.setMember(memberDAO.getById(Integer.parseInt(memberId)));
        borrowing.setCopy(copyDAO.getById(Integer.parseInt(copyId)));
        borrowing.setBorrowDate(parseDate(borrowDate));
        borrowing.setReturnDate(parseDate(returnDate));

        borrowingDAO.update(borrowing);
    }

    /**
     * Updates the details of an existing copy of a book in the system.
     *
     * @param id The ID of the copy to be updated.
     * @param bookId The ID of the book associated with the copy.
     * @param copyNumber The copy number of the book.
     * @param condition The condition of the copy (e.g., "Good", "Damaged").
     * @throws IllegalArgumentException if the copy with the specified ID does not exist.
     */
    @Override
    public void updateCopy(int id, String bookId, String copyNumber, String condition) {
        Copy copy = copyDAO.getById(id);

        if (copy == null) {
            throw new IllegalArgumentException("Copy with ID " + id + " does not exist.");
        }

        copy.setBook(bookDAO.getById(Integer.parseInt(bookId)));
        copy.setCopyNumber(Integer.parseInt(copyNumber));
        copy.setStatus(condition);

        copyDAO.update(copy);
    }

    /**
     * Updates the details of an existing librarian in the system.
     *
     * @param id The ID of the librarian to be updated.
     * @param memberId The ID of the librarian's associated member.
     * @param employmentDate The new employment date of the librarian in the format "yyyy-MM-dd".
     * @param position The new position of the librarian (e.g., "Senior Librarian").
     * @throws IllegalArgumentException if the librarian with the specified ID does not exist.
     */
    @Override
    public void updateLibrarian(int id, String memberId, String employmentDate, String position) {
        Librarian librarian = librarianDAO.getById(id);

        if (librarian == null) {
            throw new IllegalArgumentException("Librarian with ID " + id + " does not exist.");
        }

        librarian.setMember(memberDAO.getById(Integer.parseInt(memberId)));
        librarian.setPosition(position);
        librarian.setEmploymentDate(parseDate(employmentDate));

        librarianDAO.update(librarian);
    }

    /**
     * Updates the details of an existing member in the system.
     *
     * @param id The ID of the member to be updated.
     * @param name The new name of the member.
     * @param email The new email address of the member.
     * @param phone The new phone number of the member.
     * @param address The new address of the member.
     * @throws IllegalArgumentException if the member with the specified ID does not exist.
     */
    @Override
    public void updateMember(int id, String name, String email, String phone, String address) {
        Member user = memberDAO.getById(id);

        if (user == null) {
            throw new IllegalArgumentException("Member with ID " + id + " does not exist.");
        }

        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setAddress(address);

        memberDAO.update(user);
    }

    /**
     * Updates the details of an existing publisher in the system.
     *
     * @param id The ID of the publisher to be updated.
     * @param name The new name of the publisher.
     * @param address The new address of the publisher.
     * @param phone The new phone number of the publisher.
     * @throws IllegalArgumentException if the publisher with the specified ID does not exist.
     */
    @Override
    public void updatePublisher(int id, String name, String address, String phone) {
        Publisher publisher = publisherDAO.getById(id);

        if (publisher == null) {
            throw new IllegalArgumentException("Publisher with ID " + id + " does not exist.");
        }

        publisher.setName(name);
        publisher.setAddress(address);
        publisher.setPhoneNumber(phone);

        publisherDAO.update(publisher);
    }

    /**
     * Parses a date string into a Date object.
     *
     * @param date The date string in the format "yyyy-MM-dd".
     * @return The corresponding Date object, or null if the date is invalid.
     */
    private Date parseDate(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(date);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}