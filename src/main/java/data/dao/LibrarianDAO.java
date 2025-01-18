package data.dao;

import data.annotations.Display;
import data.dependencies.DAO;
import data.entities.Librarian;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Data Access Object (DAO) for managing {@link Librarian} entities.
 * Extends the generic {@link DAO} class to perform CRUD operations on Librarian entities.
 */
public class LibrarianDAO extends DAO<Librarian> {

    /**
     * Retrieves the column names for the Librarian entity.
     * This method uses reflection to find fields that are annotated with {@link Display}.
     * Only the fields with the {@link Display} annotation will be returned as column names.
     *
     * @return A list of column names for the Librarian entity.
     */
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Librarian.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Display.class))
                .map(Field::getName)
                .toList();
    }

    /**
     * Returns the class type of the Librarian entity.
     *
     * @return The class type of the Librarian entity.
     */
    @Override
    protected Class<Librarian> getEntityClass() {
        return Librarian.class;
    }
}