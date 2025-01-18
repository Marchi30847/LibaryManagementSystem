package ui;

import data.constants.Fonts;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {
    private final JLabel      label      = new JLabel();
    private final JScrollPane scrollPane = new JScrollPane();
    private final JTable      table      = new JTable();

    public TablePanel(String headerText) {
        configure();
        configureLabel(headerText);
        configureTable();
        configureScrollPane();
        addAll();
    }

    private void configure() {
        this.setLayout(new BorderLayout());
    }

    private void configureLabel(String text) {
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        Fonts.applyToComponent(
                label,
                Fonts.HEADER_FONT
        );
    }

    private void configureTable() {
        table.setFillsViewportHeight(true);
    }

    private void configureScrollPane() {
        scrollPane.setViewportView(table);
    }

    private void addAll() {
        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTable getTable() {
        return table;
    }
}
