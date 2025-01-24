package data.dao;

import data.entities.*;

/**
 * Factory class responsible for providing the appropriate DAO (Data Access Object) instance
 * based on the given entity class type.
 *
 * <p>This class provides a static method, <code>getDAO(Class&lt;?&gt; entityClass)</code>,
 * which returns the corresponding DAO object based on the provided entity class.</p>
 *
 * <p>If no matching entity class is found, an <code>IllegalStateException</code> is thrown.</p>
 */
public class DAOFactory {

    // Private constructor to prevent instantiation of this utility class
    private DAOFactory() {
    }

    /**
     * Returns the appropriate DAO instance based on the provided entity class.
     *
     * @param entityClass the class of the entity for which the DAO is requested
     * @return the corresponding DAO instance for the specified entity class
     * @throws IllegalStateException if the entity class is not recognized
     */
    public static DAO<?> getDAO(Class<?> entityClass) {
        if (entityClass == Book.class) {
            return new BookDAO();
        }
        else if (entityClass == Borrowing.class) {
            return new BorrowingDAO();
        }
        else if (entityClass == Copy.class) {
            return new CopyDAO();
        }
        else if (entityClass == Librarian.class) {
            return new LibrarianDAO();
        }
        else if (entityClass == Member.class) {
            return new MemberDAO();
        }
        else if (entityClass == Publisher.class) {
            return new PublisherDAO();
        }
        else {
            throw new IllegalStateException("Unexpected entity class: " + entityClass.getSimpleName());
        }
    }
}