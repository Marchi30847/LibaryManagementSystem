package data.CRUD;


import utils.HibernateUtil;
import data.entities.Copy;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CopyCRUD {

    // Save a new copy
    public static void saveCopy(Copy copy) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(copy);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Retrieve all copies
    public List<Copy> getAllCopies() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Copy", Copy.class).list();
        }
    }

    // Retrieve a copy by ID
    public Copy getCopyById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Copy.class, id);
        }
    }

    // Update a copy
    public void updateCopy(Copy copy) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(copy);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Delete a copy
    public void deleteCopy(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Copy copy = session.get(Copy.class, id);
            if (copy != null) {
                session.delete(copy);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
