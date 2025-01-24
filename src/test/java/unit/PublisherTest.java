package unit;

import data.dao.*;
import data.entities.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.HibernateUtil;
import utils.TableCleaner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PublisherTest {
    static {
        HibernateUtil.setTestMode(true);
    }

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
    void testCreatePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Example Publishing");
        publisher.setAddress("123 Main St, Anytown, NY");
        publisher.setPhoneNumber("+1234567890");
        publisherDAO.save(publisher);

        Publisher retrievedPublisher = publisherDAO.getById(publisher.getId());
        assertNotNull(retrievedPublisher);
        assertEquals("Example Publishing", retrievedPublisher.getName());
        assertEquals("123 Main St, Anytown, NY", retrievedPublisher.getAddress());
    }

    @Test
    void testRetrieveAllPublishers() {
        Publisher publisher = new Publisher();
        publisher.setName("Example Publishing");
        publisher.setAddress("123 Main St, Anytown, NY");
        publisher.setPhoneNumber("+1234567890");
        publisherDAO.save(publisher);

        List<Publisher> publishers = publisherDAO.getAll();
        assertNotNull(publishers);
        assertFalse(publishers.isEmpty());
    }

    @Test
    void testUpdatePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Example Publishing");
        publisher.setAddress("123 Main St, Anytown, NY");
        publisher.setPhoneNumber("+1234567890");
        publisherDAO.save(publisher);

        String newAddress = "456 Updated Address, Boston, MA";
        publisher.setAddress(newAddress);
        publisherDAO.update(publisher);

        Publisher updatedPublisher = publisherDAO.getById(publisher.getId());
        assertEquals(newAddress, updatedPublisher.getAddress());
    }

    @Test
    void testDeletePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Example Publishing");
        publisher.setAddress("123 Main St, Anytown, NY");
        publisher.setPhoneNumber("+1234567890");
        publisherDAO.save(publisher);

        publisherDAO.delete(publisher.getId());

        assertNull(publisherDAO.getById(publisher.getId()));
    }
}

