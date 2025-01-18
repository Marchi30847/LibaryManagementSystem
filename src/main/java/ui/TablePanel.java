package ui;

import data.constants.Fonts;

import javax.swing.*;
import java.awt.*;

/**
 * The TablePanel class is a custom JPanel that provides a labeled table
 * with a scrollable view. It is used to display tabular data with a header label.
 */
public class TablePanel extends JPanel {
    private final JLabel label = new JLabel(); // The label displayed above the table
    private final JScrollPane scrollPane = new JScrollPane(); // Scrollable view for the table
    private final JTable table = new JTable(); // The table component to display data

    /**
     * Constructs a TablePanel with the specified header text.
     *
     * @param headerText the text to display in the header label
     */
    public TablePanel(String headerText) {
        configure();
        configureLabel(headerText);
        configureTable();
        configureScrollPane();
        addAll();
    }

    /**
     * Configures the layout of the TablePanel to use BorderLayout.
     */
    private void configure() {
        this.setLayout(new BorderLayout());
    }

    /**
     * Configures the label with the provided text and styling.
     *
     * @param text the text to set for the label
     */
    private void configureLabel(String text) {
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        Fonts.applyToComponent(
                label,
                Fonts.HEADER_FONT
        );
    }

    /**
     * Configures the table to fill the viewport height, ensuring it resizes properly.
     */
    private void configureTable() {
        table.setFillsViewportHeight(true);
    }

    /**
     * Configures the scroll pane to use the table as its viewport view.
     */
    private void configureScrollPane() {
        scrollPane.setViewportView(table);
    }

    /**
     * Adds the label and scroll pane to the panel layout.
     * The label is positioned at the top, and the scroll pane is in the center.
     */
    private void addAll() {
        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Gets the JTable component for this TablePanel.
     * This allows external classes to access and manipulate the table directly.
     *
     * @return the JTable used in this TablePanel
     */
    public JTable getTable() {
        return table;
    }
}