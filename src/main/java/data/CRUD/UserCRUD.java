package data.CRUD;


import utils.HibernateUtil;
import data.entities.Member;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserCRUD {
    public void saveUser(Member user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (user.getEmail() == null || user.getEmail().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be null or empty.");
            }
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Member> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Member ", Member.class).list();
        }
    }

    public Member getUserById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Member.class, id);
        }
    }

    public void updateUser(Member user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Check for dependencies
            long borrowingCount = session.createQuery(
                            "SELECT COUNT(b) FROM Borrowing b WHERE b.user.id = :userId", Long.class)
                    .setParameter("userId", userId)
                    .getSingleResult();

            if (borrowingCount > 0) {
                throw new IllegalStateException("Cannot delete user with ID " + userId +
                        " as they have " + borrowingCount + " active borrowing(s).");
            }

            // Proceed with deletion if no dependencies
            Member user = session.get(Member.class, userId);
            if (user != null) {
                session.delete(user);
                transaction.commit();
            } else {
                throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new IllegalStateException("Failed to delete user", e);
        }
    }



    public long getActiveBorrowingsCount(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT COUNT(b) FROM Borrowing b WHERE b.user.id = :userId AND b.returnDate IS NULL", Long.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
        }
    }


}

