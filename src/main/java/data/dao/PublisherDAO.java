package data.dao;

import data.annotations.Display;
import data.dependencies.DAO;
import data.entities.Publisher;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Data Access Object (DAO) for managing {@link Publisher} entities.
 * Extends the generic {@link DAO} class to perform CRUD operations on Publisher entities.
 */
public class PublisherDAO extends DAO<Publisher> {

    /**
     * Retrieves the column names for the Publisher entity.
     * This method uses reflection to find fields that are annotated with {@link Display}.
     * Only the fields with the {@link Display} annotation will be returned as column names.
     *
     * @return A list of column names for the Publisher entity.
     */
    @Override
    public List<String> getColumnNames() {
        return Arrays.stream(Publisher.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Display.class))
                .map(Field::getName)
                .toList();
    }

    /**
     * Returns the class type of the Publisher entity.
     *
     * @return The class type of the Publisher entity.
     */
    @Override
    protected Class<Publisher> getEntityClass() {
        return Publisher.class;
    }
}