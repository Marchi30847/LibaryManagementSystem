package data.CRUD;

import utils.HibernateUtil;
import data.entities.Borrowing;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BorrowingCRUD {
    public static void saveBorrowing(Borrowing borrowing) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (borrowing.getMember() == null || borrowing.getCopy() == null) {
                throw new IllegalArgumentException("Borrowing must have a user and a copy.");
            }
            session.save(borrowing);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Borrowing> getAllBorrowings() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Borrowing", Borrowing.class).list();
        }
    }

    public Borrowing getBorrowingById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Borrowing.class, id);
        }
    }

    public void updateBorrowing(Borrowing borrowing) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(borrowing);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteBorrowing(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Borrowing borrowing = session.get(Borrowing.class, id);
            if (borrowing != null) {
                session.delete(borrowing);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Borrowing> getBorrowingsByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Borrowing WHERE user.id = :userId", Borrowing.class)
                    .setParameter("userId", userId)
                    .list();
        }
    }

}

