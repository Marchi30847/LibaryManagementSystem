package data.dao;

import data.annotations.Display;
import data.entities.Book;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Data Access Object (DAO) for managing {@link Book} entities.
 * Extends the generic {@link DAO} class to perform CRUD operations on Book entities.
 */
public class BookDAO extends DAO<Book> {

    /**
     * Retrieves the column names for the Book entity.
     * This method uses reflection to find fields that are annotated with {@link Display}.
     * Only the fields with the {@link Display} annotation will be returned as column names.
     *
     * @return A list of column names for the Book entity.
     */
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Book.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Display.class))
                .map(Field::getName)
                .toList();
    }

    /**
     * Returns the class type of the Book entity.
     *
     * @return The class type of the Book entity.
     */
    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
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