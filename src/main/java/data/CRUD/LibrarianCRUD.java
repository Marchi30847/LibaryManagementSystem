package data.CRUD;


import utils.HibernateUtil;
import data.entities.Librarian;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LibrarianCRUD {

    // Save a new librarian
    public void saveLibrarian(Librarian librarian) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(librarian);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Retrieve all librarians
    public List<Librarian> getAllLibrarians() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Librarian", Librarian.class).list();
        }
    }

    // Retrieve a librarian by ID
    public Librarian getLibrarianById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Librarian.class, id);
        }
    }

    // Update a librarian
    public void updateLibrarian(Librarian librarian) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(librarian);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Delete a librarian
    public void deleteLibrarian(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Librarian librarian = session.get(Librarian.class, id);
            if (librarian != null) {
                session.delete(librarian);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}

