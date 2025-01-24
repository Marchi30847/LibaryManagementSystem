package integration;

import data.dao.*;
import data.entities.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.HibernateUtil;
import utils.TableCleaner;

import static org.junit.jupiter.api.Assertions.*;

public class PublisherBookTest {
    static {
        HibernateUtil.setTestMode(true);
    }

    private final PublisherDAO publisherDAO = new PublisherDAO();
    private final BookDAO bookDAO = new BookDAO();

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
    void testPublisherBookRelationship() {
        Publisher publisher = new Publisher();
        publisher.setName("Example Publishing");
        publisher.setAddress("123 Main St, Anytown, NY");
        publisher.setPhoneNumber("+1234567890");

        try {
            publisherDAO.save(publisher);
            assertNotEquals(0, publisher.getId(), "Publisher ID should be generated and not 0.");
        } catch (Exception e) {
            fail("Failed to save publisher: " + e.getMessage());
        }

        Book book = new Book();
        book.setTitle("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        book.setPublisher(publisher);
        book.setPublicationYear(1925);
        book.setIsbn("123-1231231234");

        try {
            bookDAO.save(book);
        } catch (Exception e) {
            fail("Failed to save book: " + e.getMessage());
        }

        Book retrievedBook = bookDAO.getById(book.getId());
        assertNotNull(retrievedBook);
        assertEquals(publisher.getId(), retrievedBook.getPublisher().getId());
        assertEquals("Example Publishing", retrievedBook.getPublisher().getName());
    }

}
