package data.dao;

import data.entities.*;

public class DAOFactory {

    private DAOFactory() {
    }

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
