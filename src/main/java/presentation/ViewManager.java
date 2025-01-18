package presentation;

import domain.LibrarianModel;
import domain.UserModel;
import ui.LibrarianView;
import ui.MainFrame;
import ui.ModeSelectionView;
import ui.UserView;

import javax.swing.*;

public class ViewManager {
    private final MainFrame mainFrame = new MainFrame();
    private JPanel currentView;

    private UserPresenter userPresenter;
    private LibrarianPresenter librarianPresenter;

    public ViewManager() {
        switchToSelectionView();
    }

    private void switchToSelectionView() {
        ModeSelectionView selection = new ModeSelectionView();
        selection.addLibrarianModeChangeListener(e -> switchToLibrarianView());
        selection.addUserModeChangeListener(e -> switchToUserView());
        setCurrentView(selection);
    }

    private void switchToUserView() {
        UserView userView = new UserView();
        userPresenter = new UserPresenter(userView, new UserModel());

        setCurrentView(userView);
    }

    private void switchToLibrarianView() {
        LibrarianView librarianView = new LibrarianView();
        librarianPresenter = new LibrarianPresenter(librarianView, new LibrarianModel());

        setCurrentView(librarianView);
    }

    private void setCurrentView(JPanel newView) {
        if (currentView != null) {
            mainFrame.remove(currentView);
        }
        currentView = newView;
        mainFrame.add(currentView);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public UserPresenter getUserPresenter() {
        return userPresenter;
    }

    public LibrarianPresenter getLibrarianPresenter() {
        return librarianPresenter;
    }
}