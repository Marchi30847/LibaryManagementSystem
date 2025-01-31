package domain;

import data.annotations.MatchPattern;
import data.dao.*;
import data.dao.DAO;
import data.dependencies.LibrarianContract;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Temporal;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The LibrarianModel class implements the LibrarianContract.Model interface.
 * It provides methods to manage and manipulate data related to books, borrowings, copies, librarians,
 * members, and publishers in the library system.
 */
public class LibrarianModel implements LibrarianContract.Model {

    /**
     * A generic method to map entities to column data for display.
     * This method retrieves values of fields from the provided list of entities
     * and organizes them into a 2D array.
     *
     * @param <T>         the type of the entity
     * @param columnNames the column names to be displayed
     * @param entities    the list of entities to be mapped
     * @return a 2D array of String values representing the mapped entities
     */
    private <T> String[][] mapEntitiesToColumns(List<String> columnNames, List<T> entities) {
        List<String[]> result = new ArrayList<>();

        result.add(columnNames.toArray(new String[0]));

        result.addAll(entities.stream()
                .map(entity -> columnNames.stream()
                        .map(columnName -> {
                            try {
                                Field field = entity.getClass().getDeclaredField(columnName);
                                field.setAccessible(true);
                                Object value = field.get(entity);
                                return value != null ? value.toString() : "null";
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                throw new RuntimeException("Error accessing field: " + columnName, e);
                            }
                        })
                        .toArray(String[]::new))
                .toList()
        );

        return result.toArray(new String[0][0]);
    }

    /**
     * Retrieves data for the specified entity class.
     *
     * @param entityClass the class of the entity to retrieve data for
     * @param <T>         the type of the entity
     * @return a 2D array of String values representing the data of the specified entity
     */
    @Override
    public <T> String[][] getData(Class<T> entityClass) {
        DAO<?> dao = DAOFactory.getDAO(entityClass);
        List<?> entities = dao.getAll();
        List<String> columnNames = dao.getColumnNames();
        return mapEntitiesToColumns(columnNames, entities);
    }

    /**
     * Inserts a new entity into the database.
     *
     * @param fieldData   the field data for the new entity
     * @param entityClass the class of the entity to insert
     * @return the ID of the newly inserted entity
     */
    public int insert(Map<String, Object> fieldData, Class<?> entityClass) {
        try {
            Object entity = entityClass.getDeclaredConstructor().newInstance();

            Arrays.stream(entityClass.getDeclaredFields())
                    .filter(field -> fieldData.containsKey(field.getName()))
                    .peek(field -> field.setAccessible(true))
                    .forEach(field -> {
                        Object value = fieldData.get(field.getName());

                        if (!isFieldNullable(field) && (value == null || value.toString().trim().isEmpty())) {
                            throw new IllegalArgumentException("Field " + field.getName() + " cannot be null or empty.");
                        }

                        if (field.isAnnotationPresent(MatchPattern.class) && value != null) {
                            String pattern = field.getAnnotation(MatchPattern.class).value();
                            if (!value.toString().matches(pattern)) {
                                throw new IllegalArgumentException(String.format(
                                        "Field '%s' must match the pattern: %s", field.getName(), pattern
                                ));
                            }
                        }

                        try {
                            if (field.isAnnotationPresent(JoinColumn.class)) {
                                DAO<?> referencedDAO = DAOFactory.getDAO(field.getType());
                                if (value != null && !value.toString().trim().isEmpty()) {
                                    Object referencedEntity = referencedDAO.getById(Integer.parseInt(value.toString()));
                                    field.set(entity, referencedEntity);
                                } else {
                                    field.set(entity, null);
                                }
                            } else {
                                if (field.getType().equals(int.class)) {
                                    field.set(entity, Integer.parseInt(value.toString()));
                                } else if (field.getType().equals(String.class)) {
                                    field.set(entity, value.toString());
                                }
                            }
                        } catch (Exception e) {
                            throw new RuntimeException("Error setting field " + field.getName(), e);
                        }
                    });

            Arrays.stream(entityClass.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(Temporal.class))
                    .peek(field -> field.setAccessible(true))
                    .forEach(field -> {
                        try {
                            if (!isFieldNullable(field)) {
                                if (field.getType().equals(Date.class)) {
                                    field.set(entity, new Date());
                                }
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    });

            DAO<Object> dao = (DAO<Object>) DAOFactory.getDAO(entityClass);
            dao.save(entity);
            Field idField = dao.getAll().getLast().getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return idField.getInt(entity);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to insert entity", e);
        }
    }

    /**
     * Checks if a field is nullable based on its annotations.
     *
     * @param field the field to check
     * @return true if the field is nullable, false otherwise
     */
    private boolean isFieldNullable(Field field) {
        Column columnAnnotation = field.getAnnotation(Column.class);
        return columnAnnotation != null && columnAnnotation.nullable();
    }

    /**
     * Deletes an entity from the database by its ID.
     *
     * @param id          the ID of the entity to delete
     * @param entityClass the class of the entity to delete
     * @return the ID of the deleted entity
     */
    @Override
    public int delete(int id, Class<?> entityClass) {
        DAO<?> dao = DAOFactory.getDAO(entityClass);
        dao.delete(id);
        return id;
    }

    /**
     * Updates an existing entity in the database.
     *
     * @param id          the ID of the entity to update
     * @param fieldData   the field data to update
     * @param entityClass the class of the entity to update
     * @return the ID of the updated entity
     */
    public int update(int id, Map<String, Object> fieldData, Class<?> entityClass) {
        Object entity = DAOFactory.getDAO(entityClass).getById(id);

        Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> fieldData.containsKey(field.getName()))
                .peek(field -> field.setAccessible(true))
                .forEach(field -> {
                    Object value = fieldData.get(field.getName());
                    if (!isFieldNullable(field) && (value == null || value.toString().trim().isEmpty())) {
                        throw new IllegalArgumentException("Field " + field.getName() + " cannot be null or empty.");
                    }

                    if (field.isAnnotationPresent(MatchPattern.class) && value != null) {
                        String pattern = field.getAnnotation(MatchPattern.class).value();
                        if (!value.toString().matches(pattern)) {
                            throw new IllegalArgumentException(String.format(
                                    "Field '%s' must match the pattern: %s", field.getName(), pattern
                            ));
                        }
                    }

                    try {
                        if (field.isAnnotationPresent(JoinColumn.class)) {
                            DAO<?> referencedDAO = DAOFactory.getDAO(field.getType());
                            if (value != null && !value.toString().trim().isEmpty()) {
                                Object referencedEntity = referencedDAO.getById(Integer.parseInt(value.toString()));
                                field.set(entity, referencedEntity);
                            } else {
                                field.set(entity, null);
                            }
                        } else {
                            if (field.getType().equals(int.class)) {
                                field.set(entity, Integer.parseInt(value.toString()));
                            } else if (field.getType().equals(String.class)) {
                                field.set(entity, value.toString());
                            } else if (field.getType().equals(Date.class)) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                field.set(entity, sdf.parse(value.toString()));
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("Error setting field " + field.getName(), e);
                    }
                });

        DAO<Object> dao = (DAO<Object>) DAOFactory.getDAO(entityClass);
        dao.update(entity);
        return id;
    }
}