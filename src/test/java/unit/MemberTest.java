package unit;


import data.dao.*;

import data.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.HibernateUtil;
import utils.TableCleaner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {
    static {
        HibernateUtil.setTestMode(true);
    }

    private final MemberDAO memberDAO = new MemberDAO();

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
    void testCreateUser() {
        Member member = new Member();
        member.setName("Jane Doe");
        member.setEmail("jane.doe@example.com");
        member.setPhoneNumber("+1234567890");
        member.setAddress("123 Main St, Anytown, NY");
        memberDAO.save(member);

        Member retrievedMember = memberDAO.getById(member.getId());
        assertNotNull(retrievedMember);
        assertEquals("Jane Doe", retrievedMember.getName());
        assertEquals("jane.doe@example.com", retrievedMember.getEmail());
    }

    @Test
    void testRetrieveAllUsers() {
        Member member = new Member();
        member.setName("Jane Doe");
        member.setEmail("jane.doe@example.com");
        member.setPhoneNumber("+1234567890");
        member.setAddress("123 Main St, Anytown, NY");
        memberDAO.save(member);

        List<Member> users = memberDAO.getAll();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    void testUpdateUser() {
        Member member = new Member();
        member.setName("Jane Doe");
        member.setEmail("jane.doe@example.com");
        member.setPhoneNumber("+1234567890");
        member.setAddress("123 Main St, Anytown, NY");
        memberDAO.save(member);

        String newName = "Updated Name";
        member.setName(newName);
        memberDAO.update(member);

        Member updatedMember = memberDAO.getById(member.getId());
        assertEquals(newName, updatedMember.getName());
    }

    @Test
    void testDeleteUser() {
        Member member = new Member();
        member.setName("Jane Doe");
        member.setEmail("jane.doe@example.com");
        member.setPhoneNumber("+1234567890");
        member.setAddress("123 Main St, Anytown, NY");
        memberDAO.save(member);

        memberDAO.delete(member.getId());
        assertNull(memberDAO.getById(member.getId()));
    }
}

