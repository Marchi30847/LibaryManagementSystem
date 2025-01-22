package data.dependencies;

import data.constants.Tables;

import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Contract for the Librarian functionality in the application.
 * This interface defines the methods for the View, Presenter, and Model related to managing librarian actions.
 * It includes methods for interacting with and managing books, borrowings, copies, librarians, members, and publishers.
 */
public interface LibrarianContract {

    /**
     * View interface for the Librarian's actions.
     * This interface handles interactions with the user interface, such as adding listeners and updating tables.
     */
    interface View {

        /**
         * Adds an action listener to the insert button.
         * The listener will be triggered when the user clicks the insert button.
         *
         * @param listener The action listener to be added.
         */
        void addInsertButtonListener(ActionListener listener);

        /**
         * Adds an action listener to the delete button.
         * The listener will be triggered when the user clicks the delete button.
         *
         * @param listener The action listener to be added.
         */
        void addDeleteButtonListener(ActionListener listener);

        /**
         * Adds an action listener to the update button.
         * The listener will be triggered when the user clicks the update button.
         *
         * @param listener The action listener to be added.
         */
        void addUpdateButtonListener(ActionListener listener);

        /**
         * Adds a listener to handle tab changes in the UI.
         */
        void addTabChangedListener();

        /**
         * Retrieves the currently selected row in the table.
         *
         * @return The row index of the selected row.
         */
        int getCurrentSelectedRow();

        int getCurrentSelectedColumn();

        String getColumnName(int columnIndex);

        int getRowCount();

        int getColumnCount();


        /**
         * Retrieves the current table type being viewed.
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
     * This interface defines methods for handling user interactions and managing the view and model.
     */
    interface Presenter {

        /**
         * Initializes listeners for all UI components in the Librarian interface.
         */
        void initListeners();

        /**
         * Fills the tables with the data required for display.
         */
        void refreshAll();
    }

    /**
     * Model interface for the Librarian functionality.
     * This interface defines methods for accessing and manipulating the data related to books, borrowings, copies, librarians, members, and publishers.
     */
    interface Model {

        <T> String[][] getData(Class<T> entityClass);

        void insert(Map<String, Object> fieldData, Class<?> entityClass);

        void delete(int id, Class<?> entityClass);

        void update(int id, Map<String, Object> fieldData, Class<?> entityClass);
    }
}