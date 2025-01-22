package presentation;

import data.constants.Tables;
import data.dependencies.LibrarianContract;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Temporal;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LibrarianPresenter is the controller class that handles the logic for managing library entities
 * such as books, borrowings, copies, librarians, members, and publishers.
 * It interacts with the view and model to perform actions like adding, updating, and deleting entities.
 * It listens for actions performed by the user (such as button clicks) and updates the view accordingly.
 * <p>
 * The class implements the Presenter interface from the LibrarianContract and provides methods to handle various
 * actions related to the management of library data.
 */
public class LibrarianPresenter implements LibrarianContract.Presenter {

    private final LibrarianContract.View view;
    private final LibrarianContract.Model model;

    /**
     * Constructs a new LibrarianPresenter with the specified view and model.
     * Initializes listeners for button actions and populates tables with existing data.
     *
     * @param view  the view instance that interacts with the user
     * @param model the model instance that interacts with the data
     */
    public LibrarianPresenter(LibrarianContract.View view, LibrarianContract.Model model) {
        this.view = view;
        this.model = model;
        initListeners();
        refreshAll();
    }

    /**
     * Initializes the listeners for the insert, delete, and update buttons.
     * These listeners respond to user actions and trigger the corresponding actions.
     */
    @Override
    public void initListeners() {
        view.addInsertButtonListener(createInsertTitleButtonListener());
        view.addDeleteButtonListener(createDeleteTitleButtonListener());
        view.addUpdateButtonListener(createUpdateTitleButtonListener());
    }

    /**
     * Creates an ActionListener for the insert button.
     * This listener triggers the appropriate method for adding an entity (book, borrowing, copy, librarian, member, or publisher).
     *
     * @return the ActionListener for the insert button
     */
    private ActionListener createInsertTitleButtonListener() {
        return _ -> {
            Class<?> entityClass = view.getCurrentTable().getEntityClass();
            Map<String, Object> fieldValues = new HashMap<>();

            Arrays.stream(entityClass.getDeclaredFields())
                    .filter(field ->
                            !field.isAnnotationPresent(GeneratedValue.class) &&
                            !field.isAnnotationPresent(Temporal.class))

                    .peek(field -> field.setAccessible(true))
                    .forEach(field -> {
                        try {
                            String input = view.createInputDialog("Enter " + field.getName() + ":", "");
                            fieldValues.put(field.getName(), input);
                        } catch (NumberFormatException e) {
                            view.createErrorDialog("Error", "Invalid input for field: " + field.getName());
                            throw new RuntimeException("Invalid input for field: " + field.getName(), e);
                        }
                    });
            model.insert(fieldValues, entityClass);

            refreshTable(view.getCurrentTable());
        };
    }

    /**
     * Creates an ActionListener for the delete button.
     * This listener triggers the appropriate method for deleting an entity (book, borrowing, copy, librarian, member, or publisher).
     *
     * @return the ActionListener for the delete button
     */
    private ActionListener createDeleteTitleButtonListener() {
        return _ -> {
            Class<?> entityClass = view.getCurrentTable().getEntityClass();

            int selectedRow = view.getCurrentSelectedRow();
            if (selectedRow == -1) {
                view.createErrorDialog("Error", "No row selected.");
                return;
            }

            int idColumnIndex = -1;
            for (int i = 0; i < view.getColumnCount(); i++) {
                if ("id".equalsIgnoreCase(view.getColumnName(i))) {
                    idColumnIndex = i;
                    break;
                }
            }

            if (idColumnIndex == -1) {
                throw new IllegalStateException("ID column not found.");
            }

            int id = Integer.parseInt(view.getValueAt(selectedRow, idColumnIndex));

            model.delete(id, entityClass);
            refreshTable(view.getCurrentTable());
        };
    }

    /**
     * Creates an ActionListener for the update button.
     * This listener triggers the appropriate method for updating an entity (book, borrowing, copy, librarian, member, or publisher).
     *
     * @return the ActionListener for the update button
     */
    private ActionListener createUpdateTitleButtonListener() {
        return _ -> {
           Class<?> entityClass = view.getCurrentTable().getEntityClass();
            int selectedRow = view.getCurrentSelectedRow();
            if (selectedRow == -1) {
                view.createErrorDialog("Error", "No row selected.");
                return;
            }

            int idColumnIndex = -1;
            for (int i = 0; i < view.getColumnCount(); i++) {
                if ("id".equalsIgnoreCase(view.getColumnName(i))) {
                    idColumnIndex = i;
                    break;
                }
            }

            if (idColumnIndex == -1) {
                throw new IllegalStateException("ID column not found.");
            }

            int id = Integer.parseInt(view.getValueAt(selectedRow, idColumnIndex));

            Map<String, Object> fieldValues = new HashMap<>();
            Arrays.stream(entityClass.getDeclaredFields())
                    .filter(field -> !field.isAnnotationPresent(GeneratedValue.class))
                    .peek(field -> field.setAccessible(true))
                    .forEach(field -> {
                        try {
                            String input = view.createInputDialog("Update " + field.getName() + ":",
                                    view.getValueAt(selectedRow, getColumnIndex(field.getName())));
                            fieldValues.put(field.getName(), input);
                        } catch (NumberFormatException e) {
                            view.createErrorDialog("Error", "Invalid input for field: " + field.getName());
                            throw new RuntimeException("Invalid input for field: " + field.getName(), e);
                        }
                    });

            model.update(id, fieldValues, entityClass);
            refreshTable(view.getCurrentTable());
        };
    }

    private int getColumnIndex(String columnName) {
        for (int i = 0; i < view.getColumnCount(); i++) {
            if (view.getColumnName(i).equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void refreshAll() {
        for (Tables table : Tables.values()) {
            refreshTable(table);
        }
    }

    private void refreshTable(Tables table) {
        String[][] data = model.getData(table.getEntityClass());
        String[] columns = data[0];
        String[][] rows = getDataRows(data);
        view.updateTableModel(table, columns, rows);
    }

    /**
     * Extracts data rows from the given 2D array, skipping the first row (which contains the column names).
     *
     * @param data the data array (with first row as column names)
     * @return the data rows excluding the first row
     */
    private String[][] getDataRows(String[][] data) {
        return Arrays.stream(data)
                .skip(1)
                .toArray(String[][]::new);
    }
}