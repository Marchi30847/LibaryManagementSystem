import data.dependencies.LibrarianContract;
import data.entities.*;
import domain.LibrarianModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.HibernateUtil;
import utils.TableCleaner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EdgeCaseTests {
    static {
        HibernateUtil.setTestMode(true);
    }
    private final LibrarianContract.Model model = new LibrarianModel();

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
    void testEmptyMemberName() {
        Map<String, Object> values = new HashMap<>();
        values.put("name", "");
        values.put("email", "mail1@example.com");
        values.put("phoneNumber", "1234567890");
        values.put("address", "random address");

        assertThrows(
                Exception.class,
                () -> model.insert(values, Member.class)
        );
    }

    @Test
    void testInvalidEmailFormat() {
        Map<String, Object> values = new HashMap<>();
        values.put("name", "test name");
        values.put("email", "bad.email.format");
        values.put("phoneNumber", "1234567890");
        values.put("address", "random address");

        assertThrows(
                Exception.class,
                () -> model.insert(values, Member.class)
        );
    }

    @Test
    void testDuplicateEmail() {
        Map<String, Object> values1 = new HashMap<>();
        values1.put("name", "test name");
        values1.put("email", "mail@example.com");
        values1.put("phoneNumber", "1234567890");
        values1.put("address", "random address");

        model.insert(values1, Member.class);

        Map<String, Object> values2 = new HashMap<>();
        values2.put("name", "test name");
        values2.put("email", "mail@example.com");
        values2.put("phoneNumber", "1234567890");
        values2.put("address", "random address");

        assertThrows(
                Exception.class,
                () -> model.insert(values2, Member.class)
        );
    }

    @Test
    void testBoundaryConditionsForCopyNumber() {
        Map<String, Object> valuesPublisher = new HashMap<>();
        valuesPublisher.put("name", "Example Publishing");
        valuesPublisher.put("address", "123 Main St, Anytown, NY");
        valuesPublisher.put("phoneNumber", "+1234567890");

        int publisherId = model.insert(valuesPublisher, Publisher.class);

        Map<String, Object> valuesBook = new HashMap<>();
        valuesBook.put("title", "The Great Gatsby");
        valuesBook.put("author", "F. Scott Fitzgerald");
        valuesBook.put("publisher", String.valueOf(publisherId));
        valuesBook.put("publicationYear", String.valueOf(1925));
        valuesBook.put("isbn", ("123-1231231234"));

        int bookId = model.insert(valuesBook, Book.class);

        Map<String, Object> valuesCopy1 = new HashMap<>();
        valuesCopy1.put("book", String.valueOf(bookId));
        valuesCopy1.put("copyNumber", "3");
        valuesCopy1.put("status", "Available");

        model.insert(valuesCopy1, Copy.class);

        Map<String, Object> values2 = new HashMap<>();
        values2.put("book", bookId);
        values2.put("copyNumber", "-1");
        values2.put("status", "Available");

        assertThrows(Exception.class, () -> model.insert(values2, Copy.class));
    }

    @Test
    void testDeletingReferencedData() {
        Map<String, Object> valuesMember = new HashMap<>();
        valuesMember.put("name", "test name");
        valuesMember.put("email", "mail@example.com");
        valuesMember.put("phoneNumber", "1234567890");
        valuesMember.put("address", "random address");

        int memberId = model.insert(valuesMember, Member.class);

        Map<String, Object> valuesPublisher = new HashMap<>();
        valuesPublisher.put("name", "Example Publishing");
        valuesPublisher.put("address", "123 Main St, Anytown, NY");
        valuesPublisher.put("phoneNumber", "+1234567890");

        int publisherId = model.insert(valuesPublisher, Publisher.class);

        Map<String, Object> valuesBook = new HashMap<>();
        valuesBook.put("title", "The Great Gatsby");
        valuesBook.put("author", "F. Scott Fitzgerald");
        valuesBook.put("publisher", String.valueOf(publisherId));
        valuesBook.put("publicationYear", String.valueOf(1925));
        valuesBook.put("isbn", ("123-1231231234"));

        int bookId = model.insert(valuesBook, Book.class);

        Map<String, Object> valuesCopy = new HashMap<>();
        valuesCopy.put("book", String.valueOf(bookId));
        valuesCopy.put("copyNumber", "3");
        valuesCopy.put("status", "Available");

        int copyId = model.insert(valuesCopy, Copy.class);

        Map<String, Object> valuesBorrowing = new HashMap<>();
        valuesBorrowing.put("member", String.valueOf(memberId));
        valuesBorrowing.put("copy", String.valueOf(copyId));

        model.insert(valuesBorrowing, Borrowing.class);

        assertThrows(
                Exception.class,
                () ->  model.delete(memberId, Member.class)
        );
    }
}