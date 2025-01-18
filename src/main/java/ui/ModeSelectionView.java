package ui;

import data.constants.Fonts;
import data.dependencies.SelectionView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The ModeSelectionView class represents a JPanel used for mode selection.
 * It provides two buttons for choosing between "LIBRARIAN" and "USER" modes.
 * Implements the SelectionView interface to handle mode change listeners.
 */
public class ModeSelectionView extends JPanel implements SelectionView {
    private final JButton option1 = new JButton(); // Button for selecting the Librarian mode
    private final JButton option2 = new JButton(); // Button for selecting the User mode

    /**
     * Constructs a ModeSelectionView and initializes its components.
     */
    public ModeSelectionView() {
        configure();
        configureOption1();
        configureOption2();
        addAll();
    }

    /**
     * Configures the layout and appearance of the panel.
     * Sets the layout to a GridLayout with two rows and spacing.
     */
    private void configure() {
        setLayout(new GridLayout(2, 1, 0, 20));
        setBorder(BorderFactory.createEmptyBorder(
                200,
                400,
                200,
                400
        ));
    }

    /**
     * Configures the Librarian mode button (option1) with text and styling.
     */
    private void configureOption1() {
        option1.setText("LIBRARIAN");
        Fonts.applyToComponent(
                option1,
                Fonts.BUTTON_FONT
        );
    }

    /**
     * Configures the User mode button (option2) with text and styling.
     */
    private void configureOption2() {
        option2.setText("USER");
        Fonts.applyToComponent(
                option2,
                Fonts.BUTTON_FONT
        );
    }

    /**
     * Adds the Librarian and User buttons to the panel.
     */
    private void addAll() {
        add(option1);
        add(option2);
    }

    /**
     * Adds an ActionListener to the Librarian mode button (option1).
     *
     * @param listener the ActionListener to attach to the Librarian button
     */
    @Override
    public void addLibrarianModeChangeListener(ActionListener listener) {
        option1.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the User mode button (option2).
     *
     * @param listener the ActionListener to attach to the User button
     */
    @Override
    public void addUserModeChangeListener(ActionListener listener) {
        option2.addActionListener(listener);
    }
}