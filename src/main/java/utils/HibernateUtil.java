package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Utility class for managing the Hibernate SessionFactory.
 * <p>
 * This class encapsulates the configuration and lifecycle management of Hibernate's
 * SessionFactory, which is used to create and manage Hibernate sessions for database
 * interaction. It ensures that the SessionFactory is initialized once and reused throughout
 * the application. It also provides a method to gracefully shut down the SessionFactory.
 * </p>
 */
public class HibernateUtil {

    // A singleton SessionFactory instance that is initialized once.
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final SessionFactory testSessionFactory = buildTestSessionFactory();

    private static boolean testMode = false;

    private HibernateUtil() {}
    /**
     * Builds the SessionFactory from the Hibernate configuration file.
     * <p>
     * This method reads the Hibernate configuration file (hibernate.cfg.xml) and uses
     * it to create the SessionFactory. If the configuration or SessionFactory creation
     * fails, it throws an ExceptionInInitializerError.
     * </p>
     *
     * @return the SessionFactory instance
     * @throws ExceptionInInitializerError if the SessionFactory creation fails
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Configures and builds the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Logs the error and throws an exception to indicate failure
            System.err.println("SessionFactory creation failed: " + ex.getMessage());
            throw new ExceptionInInitializerError("Failed to create SessionFactory: " + ex);
        }
    }

    private static SessionFactory buildTestSessionFactory() {
        try {
            // Configures and builds the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure("hibernate-test.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Logs the error and throws an exception to indicate failure
            System.err.println("SessionFactory creation failed: " + ex.getMessage());
            throw new ExceptionInInitializerError("Failed to create SessionFactory: " + ex);
        }
    }

    /**
     * Provides access to the singleton SessionFactory instance.
     * <p>
     * This method returns the initialized SessionFactory, which can be used to open
     * sessions for interacting with the database.
     * </p>
     *
     * @return the SessionFactory instance
     */
    public static SessionFactory getSessionFactory() {
        return testMode ? testSessionFactory : sessionFactory;
    }

    public static void setTestMode(boolean testMode) {
        HibernateUtil.testMode = testMode;
    }
    /**
     * Shuts down the SessionFactory and releases resources.
     * <p>
     * This method should be called when the application is shutting down to properly
     * close the SessionFactory and release any resources it is holding.
     * </p>
     */
    public static void shutdown() {
        // Closes the SessionFactory and releases any resources
        getSessionFactory().close();
    }
}