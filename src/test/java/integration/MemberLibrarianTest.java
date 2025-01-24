package integration;

import data.dao.LibrarianDAO;
import data.dao.MemberDAO;
import data.entities.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.HibernateUtil;
import utils.TableCleaner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MemberLibrarianTest {
    static {
        HibernateUtil.setTestMode(true);
    }

    private final MemberDAO memberDAO = new MemberDAO();
    private final LibrarianDAO librarianDAO = new LibrarianDAO();

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
    void testUserLibrarianRelationship() {
        Member member = new Member();
        member.setName("Jane Doe");
        member.setEmail("jane.doe@example.com");
        member.setPhoneNumber("+1234567890");
        member.setAddress("123 Main St, Anytown, NY");
        memberDAO.save(member);

        Librarian librarian = new Librarian();
        librarian.setMember(member);
        librarian.setEmploymentDate(new Date());
        librarian.setPosition("Head Librarian");
        librarianDAO.save(librarian);

        Librarian retrievedLibrarian = librarianDAO.getById(librarian.getId());
        assertNotNull(retrievedLibrarian);
        assertEquals(member.getId(), retrievedLibrarian.getMember().getId());
        assertEquals("Head Librarian", retrievedLibrarian.getPosition());
    }
}
