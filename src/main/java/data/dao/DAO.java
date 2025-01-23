package data.dao;

import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A generic Data Access Object (DAO) class that provides basic CRUD operations for entities.
 * This class uses Hibernate to interact with the database.
 *
 * @param <T> The type of entity that this DAO will manage.
 */
public abstract class DAO<T> {

    /**
     * Saves a new entity to the database.
     *
     * @param entity The entity to be saved.
     * @throws RuntimeException If the transaction fails.
     */
    public void save(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException("Failed to save entity", e);
        }
    }

    /**
     * Retrieves all entities of type T from the database.
     *
     * @return A list of all entities of type T.
     */
    public List<T> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from " + getEntityClass().getName(), getEntityClass()).list();
        }
    }

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to be retrieved.
     * @return The entity with the specified ID, or null if not found.
     */
    public T getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(getEntityClass(), id);
        }
    }

    /**
     * Updates an existing entity in the database.
     *
     * @param entity The entity to be updated.
     * @throws RuntimeException If the transaction fails.
     */
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException("Failed to update entity", e);
        }
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param id The ID of the entity to be deleted.
     * @throws RuntimeException If the transaction fails or the entity is not found.
     */
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            T entity = session.get(getEntityClass(), id);
            if (entity != null) {
                session.delete(entity);
                transaction.commit();
            } else {
                throw new IllegalArgumentException("Entity with ID " + id + " does not exist.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            throw new RuntimeException("Failed to delete entity", e);
        }
    }

    /**
     * Abstract method to get the column names for the entity.
     * Subclasses should provide the column names that are relevant to the entity.
     *
     * @return A list of column names for the entity.
     */
    public abstract List<String> getColumnNames();

    /**
     * Abstract method to get the class type of the entity.
     * Subclasses should return the class type of the entity they are managing.
     *
     * @return The class type of the entity.
     */
    protected abstract Class<T> getEntityClass();
}