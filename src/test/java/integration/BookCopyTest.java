package integration;

import data.dao.*;
import data.entities.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.HibernateUtil;
import utils.TableCleaner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookCopyTest {
    static {
        HibernateUtil.setTestMode(true);
    }

    private final BookDAO bookDAO = new BookDAO();
    private final CopyDAO copyDAO = new CopyDAO();
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
    void testBookCopyRelationship() {
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

        Copy copy = new Copy();
        copy.setBook(book);
        copy.setCopyNumber(1);
        copy.setStatus("Available");
        copyDAO.save(copy);

        Copy retrievedCopy = copyDAO.getById(copy.getId());
        assertNotNull(retrievedCopy);
        assertEquals(book.getId(), retrievedCopy.getBook().getId());
    }
}
