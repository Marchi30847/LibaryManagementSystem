package presentation;

import domain.LibrarianModel;
import domain.UserModel;
import ui.LibrarianView;
import ui.MainFrame;
import ui.ModeSelectionView;
import ui.UserView;

import javax.swing.*;

/**
 * The ViewManager class is responsible for switching between different views (user mode, librarian mode)
 * and managing the transitions between them. It initializes and manages the necessary presenters for each view.
 */
public class ViewManager {
    private final MainFrame mainFrame = new MainFrame();  // The main application frame
    private JPanel currentView;                           // The current active view

    private UserPresenter userPresenter;                  // Presenter for the user view
    private LibrarianPresenter librarianPresenter;        // Presenter for the librarian view

    /**
     * Constructor that initializes the ViewManager and switches to the selection view.
     */
    public ViewManager() {
        switchToSelectionView();  // Start with the mode selection view
    }

    /**
     * Switches the current view to the mode selection view, where the user can choose
     * between librarian mode and user mode. It also adds listeners to handle mode changes.
     */
    private void switchToSelectionView() {
        ModeSelectionView selection = new ModeSelectionView();
        selection.addLibrarianModeChangeListener(e -> switchToLibrarianView());  // Switch to librarian view when librarian mode is selected
        selection.addUserModeChangeListener(e -> switchToUserView());  // Switch to user view when user mode is selected
        setCurrentView(selection);  // Set the mode selection view as the current view
    }

    /**
     * Switches the current view to the user view, initializes the user presenter, and sets the user model.
     */
    private void switchToUserView() {
        UserView userView = new UserView();
        userPresenter = new UserPresenter(userView, new UserModel());  // Create and initialize the user presenter

        setCurrentView(userView);  // Set the user view as the current view
    }

    /**
     * Switches the current view to the librarian view, initializes the librarian presenter, and sets the librarian model.
     */
    private void switchToLibrarianView() {
        LibrarianView librarianView = new LibrarianView();
        librarianPresenter = new LibrarianPresenter(librarianView, new LibrarianModel());  // Create and initialize the librarian presenter

        setCurrentView(librarianView);  // Set the librarian view as the current view
    }

    /**
     * Sets the provided view as the current view, replacing the previous view in the main frame.
     * The method ensures that the view is properly revalidated and repainted.
     *
     * @param newView The new view to be displayed in the main frame
     */
    private void setCurrentView(JPanel newView) {
        if (currentView != null) {
            mainFrame.remove(currentView);  // Remove the previous view if it exists
        }
        currentView = newView;  // Set the new view as the current view
        mainFrame.add(currentView);  // Add the new view to the main frame
        mainFrame.revalidate();  // Revalidate the main frame to update its layout
        mainFrame.repaint();  // Repaint the main frame to reflect the changes
    }

    /**
     * Returns the presenter for the user view.
     *
     * @return The UserPresenter instance
     */
    public UserPresenter getUserPresenter() {
        return userPresenter;
    }

    /**
     * Returns the presenter for the librarian view.
     *
     * @return The LibrarianPresenter instance
     */
    public LibrarianPresenter getLibrarianPresenter() {
        return librarianPresenter;
    }
}