package unit;

import data.dao.*;
import data.entities.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.HibernateUtil;
import utils.TableCleaner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    static {
        HibernateUtil.setTestMode(true);
    }

    private final BookDAO bookDAO = new BookDAO();
    private final PublisherDAO publisherDAO = new PublisherDAO();

    @BeforeEach
    void setUp() {
        TableCleaner.clearTable(Borrowing.class);
        TableCleaner.clearTable(Copy.class);
        TableCleaner.clearTable(Librarian.class);
        TableCleaner.clearTable(Member.class);
        TableCleaner.clearTable(Book.class);
        TableCleaner.clearTable(Publisher.class);
    }

    @Test
    void testCreateBook() {
        Publisher publisher = new Publisher();
        publisher.setName("Example Publishing");
        publisher.setAddress("123 Main St, Anytown, NY");
        publisher.setPhoneNumber("+1234567890");
        publisherDAO.save(publisher);

        Book book = new Book();
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        book.setPublisher(publisher);
        book.setPublicationYear(1925);
        book.setIsbn("123-1231231234");
        bookDAO.save(book);

        Book retrievedBook = bookDAO.getById(book.getId());
        assertNotNull(retrievedBook);
        assertEquals("The Great Gatsby", retrievedBook.getTitle());
    }

    @Test
    void testRetrieveAllBooks() {
        Publisher publisher = new Publisher();
        publisher.setName("Example Publishing");
        publisher.setAddress("123 Main St, Anytown, NY");
        publisher.setPhoneNumber("+1234567890");
        publisherDAO.save(publisher);

        Book book = new Book();
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        book.setPublisher(publisher);
        book.setPublicationYear(1925);
        book.setIsbn("123-1231231234");
        bookDAO.save(book);

        List<Book> books = bookDAO.getAll();
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    @Test
    void testUpdateBook() {
        Publisher publisher = new Publisher();
        publisher.setName("Example Publishing");
        publisher.setAddress("123 Main St, Anytown, NY");
        publisher.setPhoneNumber("+1234567890");
        publisherDAO.save(publisher);

        Book book = new Book();
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        book.setPublisher(publisher);
        book.setPublicationYear(1925);
        book.setIsbn("123-1231231234");
        bookDAO.save(book);

        String newTitle = "Updated Title";
        book.setTitle(newTitle);
        bookDAO.update(book);

        Book updatedBook = bookDAO.getById(book.getId());
        assertEquals(newTitle, updatedBook.getTitle());
    }

    @Test
    void testDeleteBook() {
        Publisher publisher = new Publisher();
        publisher.setName("Example Publishing");
        publisher.setAddress("123 Main St, Anytown, NY");
        publisher.setPhoneNumber("+1234567890");
        publisherDAO.save(publisher);

        Book book = new Book();
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        book.setPublisher(publisher);
        book.setPublicationYear(1925);
        book.setIsbn("123-1231231234");
        bookDAO.save(book);

        bookDAO.delete(book.getId());
        assertNull(bookDAO.getById(book.getId()));
    }
}

