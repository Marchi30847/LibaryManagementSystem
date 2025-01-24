package data.dao;

import data.annotations.Display;
import data.entities.Borrowing;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Data Access Object (DAO) for managing {@link Borrowing} entities.
 * Extends the generic {@link DAO} class to perform CRUD operations on Borrowing entities.
 */
public class BorrowingDAO extends DAO<Borrowing> {

    /**
     * Retrieves the column names for the Borrowing entity.
     * This method uses reflection to find fields that are annotated with {@link Display}.
     * Only the fields with the {@link Display} annotation will be returned as column names.
     *
     * @return A list of column names for the Borrowing entity.
     */
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Borrowing.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Display.class))
                .map(Field::getName)
                .toList();
    }

    /**
     * Returns the class type of the Borrowing entity.
     *
     * @return The class type of the Borrowing entity.
     */
    @Override
    protected Class<Borrowing> getEntityClass() {
        return Borrowing.class;
    }

    public List<Borrowing> getBorrowingsByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Borrowing WHERE member.id = :userId", Borrowing.class)
                    .setParameter("userId", userId)
                    .list();
        }
    }
}