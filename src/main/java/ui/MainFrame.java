package ui;

import javax.swing.*;
import java.awt.*;

/**
 * The MainFrame class represents the main application window for the Library system.
 * It extends the JFrame class to provide a full-screen window with a card layout,
 * designed to manage and display different UI panels.
 */
public class MainFrame extends JFrame {

    /**
     * Constructs a MainFrame and initializes its settings.
     * The frame is set to a full HD resolution, uses a CardLayout for managing panels,
     * and has a default title of "Library".
     */
    public MainFrame() {
        // Sets the default operation to exit the application when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets the size of the window to Full HD resolution
        setSize(1920, 1080);

        // Uses a CardLayout to allow switching between different panels
        setLayout(new CardLayout());

        // Sets the title of the application window
        setTitle("Library");

        // Makes the window visible
        setVisible(true);
    }
}