package data.dao;

import data.annotations.Display;
import data.dependencies.DAO;
import data.entities.Copy;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Data Access Object (DAO) for managing {@link Copy} entities.
 * Extends the generic {@link DAO} class to perform CRUD operations on Copy entities.
 */
public class CopyDAO extends DAO<Copy> {

    /**
     * Retrieves the column names for the Copy entity.
     * This method uses reflection to find fields that are annotated with {@link Display}.
     * Only the fields with the {@link Display} annotation will be returned as column names.
     *
     * @return A list of column names for the Copy entity.
     */
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Copy.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Display.class))
                .map(Field::getName)
                .toList();
    }

    /**
     * Returns the class type of the Copy entity.
     *
     * @return The class type of the Copy entity.
     */
    @Override
    protected Class<Copy> getEntityClass() {
        return Copy.class;
    }
}