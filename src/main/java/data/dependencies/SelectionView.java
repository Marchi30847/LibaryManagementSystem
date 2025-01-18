package data.dependencies;

import java.awt.event.ActionListener;

/**
 * Interface representing the selection view in the application.
 * The SelectionView allows the user to switch between different modes (e.g., Librarian Mode, User Mode)
 * and handles the actions related to those mode changes.
 */
public interface SelectionView {

    /**
     * Adds an action listener that listens for changes when switching to Librarian Mode.
     *
     * @param listener The action listener to be added. It will be triggered when the user selects Librarian Mode.
     */
    void addLibrarianModeChangeListener(ActionListener listener);

    /**
     * Adds an action listener that listens for changes when switching to User Mode.
     *
     * @param listener The action listener to be added. It will be triggered when the user selects User Mode.
     */
    void addUserModeChangeListener(ActionListener listener);
}