import presentation.ViewManager;

import javax.swing.*;

/**
 * The entry point of the application that launches the GUI.
 * <p>
 * This class contains the `main` method, which serves as the entry point for the program. It is responsible for
 * launching the application by invoking the `ViewManager` class in the Event Dispatch Thread (EDT) using `SwingUtilities.invokeLater()`.
 * This ensures that the GUI components are created and managed on the correct thread, as recommended by Swing.
 * </p>
 */
public class Main {

    /**
     * The main entry point of the application.
     * <p>
     * This method initializes the user interface by creating an instance of `ViewManager`.
     * The `ViewManager` is responsible for managing the view and coordinating with other components in the application.
     * </p>
     * <p>
     * The method is executed in the Event Dispatch Thread (EDT) to ensure thread-safety when working with Swing components.
     * </p>
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Launch the ViewManager on the EDT thread
        SwingUtilities.invokeLater(ViewManager::new);
    }
}