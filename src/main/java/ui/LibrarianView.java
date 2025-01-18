package ui;

import data.constants.Fonts;
import data.dependencies.LibrarianContract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LibrarianView extends JPanel implements LibrarianContract.View {

    private final TablePanel members = new TablePanel("Manage Members");
    private final TablePanel books = new TablePanel("Manage Books");
    private final TablePanel borrowings = new TablePanel("Manage Borrowings");

    private final JTabbedPane tabbedPane = new JTabbedPane();

    private TablePanel currentTablePanel = members;

    private final JButton insertButton = new JButton();
    private final JButton deleteButton = new JButton();
    private final JButton updateButton = new JButton();

    private final JPanel buttonPanel = new JPanel();


    public LibrarianView() {
        configure();
        configureButtons();
        configureButtonPanel();
        configureTabbedPane();
        addAll();
    }

    private void configure() {
        setLayout(new BorderLayout());
    }


    private void configureButtons() {
        insertButton.setText("Insert");
        Fonts.applyToComponent(
                insertButton,
                Fonts.BUTTON_FONT
        );

        deleteButton.setText("Delete");
        Fonts.applyToComponent(
                deleteButton,
                Fonts.BUTTON_FONT
        );

        updateButton.setText("Update");
        Fonts.applyToComponent(
                updateButton,
                Fonts.BUTTON_FONT
        );
    }

    private void configureButtonPanel() {
        buttonPanel.setLayout(new GridLayout(
                1,
                3,
                50,
                0
        ));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                100,
                10,
                100
        ));
        buttonPanel.add(insertButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
    }

    private void configureTabbedPane() {
        tabbedPane.addTab("Manage Members", members);
        tabbedPane.addTab("Manage Books", books);
        tabbedPane.addTab("Manage Borrowings", borrowings);

        tabbedPane.setSelectedIndex(0);

        Fonts.applyToComponent(
                tabbedPane,
                Fonts.BODY_FONT
        );

        tabbedPane.addChangeListener(null);
    }

    private void addAll() {
        add(tabbedPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void addTabChangedListener() {
        tabbedPane.addChangeListener(_ -> {
            switch (tabbedPane.getSelectedIndex()) {
                case 0:
                    currentTablePanel = members;
                    break;
                case 1:
                    currentTablePanel = books;
                    break;
                case 2:
                    currentTablePanel = borrowings;
                    break;
            }
        });
    }

    @Override
    public void addInsertButtonListener(ActionListener listener) {
        insertButton.addActionListener(listener);
    }

    @Override
    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    @Override
    public void addUpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    @Override
    public int getCurrentSelectedRow() {
        return currentTablePanel.getTable().getSelectedRow();
    }
}