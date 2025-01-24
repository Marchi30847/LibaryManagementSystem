package data.dependencies;

import java.util.function.Consumer;

/**
 * Defines the contract for the User feature in the application.
 * It includes the interfaces for the View, Presenter, and Model layers,
 * allowing the separation of concerns in an MVC (Model-View-Presenter) architecture.
 */
public interface UserContract {

    /**
     * Interface defining the methods that the View must implement.
     * The View is responsible for presenting data and interacting with the user.
     */
    interface View {

        /**
         * Creates an input pane for the user to enter their user ID.
         * The entered ID is passed to the provided setter function.
         *
         * @param idSetter A Consumer function that sets the user ID.
         */
        void createUserIdInputPane(Consumer<String> idSetter);

        /**
         * Fills the table with all available titles and their details.
         *
         * @param columns The column headers for the table.
         * @param data The data to fill in the table, where each row represents a title.
         */
        void fillAllTitles(String[] columns, String[][] data);

        /**
         * Fills the table with available titles and their details.
         *
         * @param columns The column headers for the table.
         * @param data The data to fill in the table, where each row represents an available title.
         */
        void fillAvailableTitles(String[] columns, String[][] data);

        /**
         * Fills the table with the user's borrowing history.
         *
         * @param columns The column headers for the table.
         * @param data The data to fill in the table, where each row represents a borrowing record.
         */
        void fillBorrowingHistory(String[] columns, String[][] data);
    }

    /**
     * Interface defining the methods that the Presenter must implement.
     * The Presenter is responsible for managing the flow of data between the View and Model.
     */
    interface Presenter {

        /**
         * Initializes listeners for user actions, such as input changes or button clicks.
         */
        void initListeners();

        /**
         * Fills the tables with the necessary data.
         */
        void fillTables();
    }

    /**
     * Interface defining the methods that the Model must implement.
     * The Model is responsible for handling the business logic and data retrieval.
     */
    interface Model {

        /**
         * Sets the user ID to fetch relevant data for the user.
         *
         * @param userId The ID of the user to set.
         */
        void setUserId(int userId);

        /**
         * Retrieves all available titles data.
         *
         * @return A 2D array containing the data for all available titles.
         */
        String[][] getAllTitlesData();

        /**
         * Retrieves the available titles data.
         *
         * @return A 2D array containing the data for available titles.
         */
        String[][] getAvailableTitlesData();

        /**
         * Retrieves the user's borrowing history data.
         *
         * @return A 2D array containing the data for the user's borrowing history.
         */
        String[][] getBorrowingHistoryData();
    }
}