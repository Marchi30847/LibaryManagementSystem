package data.CRUD;


import utils.HibernateUtil;
import data.entities.Book;
import data.entities.Publisher;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookCRUD {
    public void saveBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Ensure publisher is managed (retrieved or saved)
            if (book.getPublisher() != null && book.getPublisher().getId() != 0) {
                Publisher publisher = session.get(Publisher.class, book.getPublisher().getId());
                book.setPublisher(publisher);
            } else {
                throw new IllegalArgumentException("Book must have a valid publisher.");
            }

            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }


    public List<Book> getAllBooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Book", Book.class).list();
        }
    }

    public Book getBookById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Book.class, id);
        }
    }

    public void updateBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteBook(int bookId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Check for dependencies in the Borrowing and Copy tables
            long borrowingCount = session.createQuery(
                            "SELECT COUNT(b) FROM Borrowing b WHERE b.copy.book.id = :bookId", Long.class)
                    .setParameter("bookId", bookId)
                    .getSingleResult();

            long copyCount = session.createQuery(
                            "SELECT COUNT(c) FROM Copy c WHERE c.book.id = :bookId", Long.class)
                    .setParameter("bookId", bookId)
                    .getSingleResult();

            if (borrowingCount > 0 || copyCount > 0) {
                throw new IllegalStateException("Cannot delete book with ID " + bookId +
                        ". It is associated with " + borrowingCount + " borrowings and " +
                        copyCount + " copies.");
            }

            // Proceed with deletion if no dependencies
            Book book = session.get(Book.class, bookId);
            if (book != null) {
                session.delete(book);
                transaction.commit();
            } else {
                throw new IllegalArgumentException("Book with ID " + bookId + " does not exist.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new IllegalStateException("Failed to delete book", e);
        }
    }


    public long getActiveBorrowingsCount(int bookId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT COUNT(b) FROM Borrowing b WHERE b.copy.book.id = :bookId AND b.returnDate IS NULL",
                            Long.class)
                    .setParameter("bookId", bookId)
                    .getSingleResult();
        }
    }

}

