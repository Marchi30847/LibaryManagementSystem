package data.constants;

import data.entities.*;

/**
 * Enum representing the various tables in the database.
 * Each enum value corresponds to a specific table in the database schema.
 */
public enum Tables {
    /**
     * Represents the "book" table in the database.
     */
    BOOK(Book.class),

    /**
     * Represents the "borrowing" table in the database.
     */
    BORROWING(Borrowing.class),

    /**
     * Represents the "copy" table in the database.
     */
    COPY(Copy.class),

    /**
     * Represents the "librarian" table in the database.
     */
    LIBRARIAN(Librarian.class),

    /**
     * Represents the "member" table in the database.
     */
    MEMBER(Member.class),

    /**
     * Represents the "publisher" table in the database.
     */
    PUBLISHER(Publisher.class);

    private final Class<?> entityClass;
    Tables(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }
}