package unit;

import data.dao.*;
import data.entities.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.HibernateUtil;
import utils.TableCleaner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianTest {
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
    void testCreateLibrarian() {
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
        assertEquals("Head Librarian", retrievedLibrarian.getPosition());
        assertEquals(member.getId(), retrievedLibrarian.getMember().getId());
    }

    @Test
    void testRetrieveAllLibrarians() {
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

        List<Librarian> librarians = librarianDAO.getAll();
        assertNotNull(librarians);
        assertFalse(librarians.isEmpty());
    }

    @Test
    void testUpdateLibrarian() {
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

        String newPosition = "Senior Librarian";
        librarian.setPosition(newPosition);
        librarianDAO.update(librarian);

        Librarian updatedLibrarian = librarianDAO.getById(librarian.getId());
        assertEquals(newPosition, updatedLibrarian.getPosition());
    }

    @Test
    void testDeleteLibrarian() {
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

        librarianDAO.delete(librarian.getId());

        assertNull(librarianDAO.getById(librarian.getId()));
    }
}

