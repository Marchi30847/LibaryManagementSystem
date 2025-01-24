package integration;

import data.dao.*;
import data.entities.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import utils.HibernateUtil;
import utils.TableCleaner;

import static org.junit.jupiter.api.Assertions.*;

public class MemberBorrowingTest {
    static {
        HibernateUtil.setTestMode(true);
    }

    private final MemberDAO memberDAO = new MemberDAO();
    private final PublisherDAO publisherDAO = new PublisherDAO();
    private final BookDAO bookDAO = new BookDAO();
    private final BorrowingDAO borrowingDAO = new BorrowingDAO();
    private final CopyDAO copyDAO = new CopyDAO();

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
    void testUserBorrowingRelationship() {
        Member member = new Member();
        member.setName("Jane Doe");
        member.setEmail("jane.doe@example.com");
        member.setPhoneNumber("+1234567890");
        member.setAddress("123 Main St, Anytown, NY");
        memberDAO.save(member);

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

        Borrowing borrowing = new Borrowing();
        borrowing.setMember(member);
        borrowing.setCopy(copy);
        borrowing.setBorrowDate(new Date());
        borrowingDAO.save(borrowing);

        Borrowing retrievedBorrowing = borrowingDAO.getById(borrowing.getId());
        assertNotNull(retrievedBorrowing);
        assertEquals(member.getId(), retrievedBorrowing.getMember().getId());
        assertEquals(copy.getId(), retrievedBorrowing.getCopy().getId());

    }
}

