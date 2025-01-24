package data.dependencies;

import data.constants.Tables;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Contract defining the functionality for managing librarian actions within the application.
 * This interface contains the views, presenters, and model methods related to handling librarian operations,
 * such as interacting with books, borrowings, copies, librarians, members, and publishers.
 */
public interface LibrarianContract {

    /**
     * View interface for the Librarian's actions.
     * Handles user interface interactions such as adding action listeners, updating table data, and retrieving user input.
     */
    interface View {

        /**
         * Adds an action listener to the insert button. Triggered when the user clicks the insert button.
         *
         * @param listener The action listener to be added.
         */
        void addInsertButtonListener(ActionListener listener);

        /**
         * Adds an action listener to the delete button. Triggered when the user clicks the delete button.
         *
         * @param listener The action listener to be added.
         */
        void addDeleteButtonListener(ActionListener listener);

        /**
         * Adds an action listener to the update button. Triggered when the user clicks the update button.
         *
         * @param listener The action listener to be added.
         */
        void addUpdateButtonListener(ActionListener listener);

        /**
         * Adds a listener to handle tab changes in the UI.
         */
        void addTabChangedListener();

        /**
         * Retrieves the currently selected row index in the table.
         *
         * @return The row index of the selected row.
         */
        int getCurrentSelectedRow();

        /**
         * Retrieves the currently selected column index in the table.
         *
         * @return The column index of the selected column.
         */
        int getCurrentSelectedColumn();

        /**
         * Retrieves the name of the column at the given index.
         *
         * @param columnIndex The index of the column.
         * @return The name of the column.
         */
        String getColumnName(int columnIndex);

        /**
         * Retrieves the number of rows in the current table.
         *
         * @return The row count of the table.
         */
        int getRowCount();

        /**
         * Retrieves the number of columns in the current table.
         *
         * @return The column count of the table.
         */
        int getColumnCount();

        /**
         * Retrieves the current table type being displayed.
         *
         * @return The current table type as a {@link Tables} enum.
         */
        Tables getCurrentTable();

        /**
         * Retrieves the value at a specific row and column in the table.
         *
         * @param row    The row index.
         * @param column The column index.
         * @return The value in the specified cell.
         */
        String getValueAt(int row, int column);

        /**
         * Updates the table model with new data.
         *
         * @param tableType The table type (Books, Borrowings, etc.).
         * @param columns   The column headers for the table.
         * @param data      The data to be displayed in the table.
         */
        void updateTableModel(Tables tableType, String[] columns, String[][] data);

        /**
         * Creates an input dialog where the user can input a value.
         *
         * @param title        The title of the input dialog.
         * @param initialValue The initial value to be displayed in the dialog.
         * @return The value entered by the user.
         */
        String createInputDialog(String title, String initialValue);

        /**
         * Creates an error dialog to display an error message to the user.
         *
         * @param title   The title of the error dialog.
         * @param message The error message to be displayed.
         */
        void createErrorDialog(String title, String message);
    }

    /**
     * Presenter interface for the Librarian functionality.
     * Handles the initialization of listeners, manages interactions between the view and model,
     * and refreshes the data displayed in the UI.
     */
    interface Presenter {

        /**
         * Initializes listeners for all UI components in the Librarian interface.
         */
        void initListeners();

        /**
         * Refreshes and fills the tables with the data required for display.
         */
        void refreshAll();
    }

    /**
     * Model interface for the Librarian functionality.
     * Defines methods for accessing and manipulating the data related to books, borrowings, copies, librarians,
     * members, and publishers in the application.
     */
    interface Model {

        /**
         * Retrieves data for the specified entity class and returns it in a two-dimensional array format.
         *
         * @param <T> The type of the entity class.
         * @param entityClass The class of the entity.
         * @return The data for the specified entity class in a 2D string array format.
         */
        <T> String[][] getData(Class<T> entityClass);

        /**
         * Inserts data for the specified entity class.
         *
         * @param fieldData A map containing the field names and their corresponding values for the entity.
         * @param entityClass The class of the entity to be inserted.
         * @return The ID of the inserted entity.
         */
        int insert(Map<String, Object> fieldData, Class<?> entityClass);

        /**
         * Deletes the entity with the specified ID.
         *
         * @param id The ID of the entity to be deleted.
         * @param entityClass The class of the entity to be deleted.
         * @return The ID of the deleted entity.
         */
        int delete(int id, Class<?> entityClass);

        /**
         * Updates the entity with the specified ID using the provided field data.
         *
         * @param id The ID of the entity to be updated.
         * @param fieldData A map containing the field names and their corresponding updated values.
         * @param entityClass The class of the entity to be updated.
         * @return The ID of the updated entity.
         */
        int update(int id, Map<String, Object> fieldData, Class<?> entityClass);
    }
}