package ui;

import data.constants.Fonts;
import data.constants.Palette;
import data.constants.Tables;
import data.dependencies.LibrarianContract;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.Map;

/**
 * The LibrarianView class is a JPanel that provides a graphical interface for the librarian
 * to manage different entities such as books, members, and publishers in the library system.
 * It provides views for managing various tables and handles actions like inserting, deleting,
 * and updating records.
 */
public class LibrarianView extends JPanel implements LibrarianContract.View {
    // A map to hold table panels for managing different tables (Books, Borrowing, etc.)
    private final Map<Tables, TablePanel> tablePanels = new EnumMap<>(Tables.class);
    private Tables currentTable;

    private final JTabbedPane tabbedPane = new JTabbedPane();  // Tabbed pane for managing different tables
    private final JButton insertButton = new JButton();        // Button to insert a new record
    private final JButton deleteButton = new JButton();        // Button to delete a record
    private final JButton updateButton = new JButton();        // Button to update a record
    private final JPanel buttonPanel = new JPanel();           // Panel to hold the buttons

    /**
     * Constructor that initializes the LibrarianView.
     * Configures the layout, buttons, table panels, and tabbed pane.
     */
    public LibrarianView() {
        configure();               // Configure the layout and table panels
        configureButtons();        // Configure the buttons (Insert, Delete, Update)
        configureButtonPanel();    // Configure the button panel layout
        configureTabbedPane();     // Configure the tabbed pane to switch between tables
        addAll();                  // Add all components to the main panel
    }

    /**
     * Configures the layout and initializes the table panels for all available tables.
     */
    private void configure() {
        setLayout(new BorderLayout());
        for (Tables tableType : Tables.values()) {
            TablePanel tablePanel = new TablePanel("Manage " + tableType.name());
            configureTablePanel(tablePanel);  // Configure the table within each table panel
            tablePanels.put(tableType, tablePanel);  // Store the table panel for each table type
        }
        currentTable = Tables.BOOK;  // Set the default table to "BOOK"
    }

    /**
     * Configures the table inside the given table panel.
     * Sets properties like row height, column resizing, header background, and font styles.
     *
     * @param tablePanel The table panel containing the JTable to configure
     */
    private void configureTablePanel(TablePanel tablePanel) {
        JTable table = tablePanel.getTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(35);

        // Set table header style
        table.getTableHeader().setBackground(Palette.HEADER_TABLE_ROW.getColor());
        Fonts.applyToComponent(table.getTableHeader(), Fonts.BODY_FONT);

        // Set row rendering style
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Set alternating row colors
                if (row % 2 == 0) {
                    component.setBackground(Palette.EVEN_TABLE_ROW.getColor());
                } else {
                    component.setBackground(Palette.ODD_TABLE_ROW.getColor());
                }

                // Apply font style to each row
                Fonts.applyToComponent(component, Fonts.BODY_FONT);

                // Align text for each column
                if (column > 0) {
                    ((JLabel) component).setHorizontalAlignment(SwingConstants.RIGHT);
                } else {
                    ((JLabel) component).setHorizontalAlignment(SwingConstants.LEFT);
                }

                // Change background color for selected rows
                if (isSelected) {
                    component.setBackground(Palette.SELECTED.getColor());
                    Fonts.applyToComponent(component, Fonts.SELECTED_BODY_FONT);
                }

                return component;
            }
        });
    }

    /**
     * Configures the Insert, Delete, and Update buttons.
     * Applies fonts and sets text for each button.
     */
    private void configureButtons() {
        insertButton.setText("Insert");
        Fonts.applyToComponent(insertButton, Fonts.BUTTON_FONT);

        deleteButton.setText("Delete");
        Fonts.applyToComponent(deleteButton, Fonts.BUTTON_FONT);

        updateButton.setText("Update");
        Fonts.applyToComponent(updateButton, Fonts.BUTTON_FONT);
    }

    /**
     * Configures the layout of the button panel, which contains the Insert, Delete, and Update buttons.
     */
    private void configureButtonPanel() {
        buttonPanel.setLayout(new GridLayout(1, 3, 50, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        buttonPanel.add(insertButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
    }

    /**
     * Configures the tabbed pane with tabs for managing different tables like Books, Borrowing, etc.
     */
    private void configureTabbedPane() {
        for (Tables tableType : Tables.values()) {
            tabbedPane.addTab("Manage " + tableType.name(), tablePanels.get(tableType));
        }
        tabbedPane.setSelectedIndex(0);
        Fonts.applyToComponent(tabbedPane, Fonts.BODY_FONT);

        addTabChangedListener();  // Add a listener to handle tab changes
    }

    /**
     * Adds all components (tabbed pane and button panel) to the main LibrarianView panel.
     */
    private void addAll() {
        add(tabbedPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void addTabChangedListener() {
        // Listener to detect when the tab has changed
        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            switch (selectedIndex) {
                case 0 -> currentTable = Tables.BOOK;
                case 1 -> currentTable = Tables.BORROWING;
                case 2 -> currentTable = Tables.COPY;
                case 3 -> currentTable = Tables.LIBRARIAN;
                case 4 -> currentTable = Tables.MEMBER;
                case 5 -> currentTable = Tables.PUBLISHER;
                default -> throw new IllegalArgumentException("Unexpected tab index: " + selectedIndex);
            }
        });
    }

    @Override
    public void updateTableModel(Tables tableType, String[] columns, String[][] rows) {
        TablePanel tablePanel = tablePanels.get(tableType);
        if (tablePanel != null) {
            tablePanel.getTable().setModel(new DefaultTableModel(rows, columns));
        } else {
            throw new IllegalArgumentException("Table type not found: " + tableType);
        }
    }

    @Override
    public String createInputDialog(String title, String initialValue) {
        return JOptionPane.showInputDialog(title, initialValue);
    }

    @Override
    public void createErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public Tables getCurrentTable() {
        return currentTable;
    }

    @Override
    public String getValueAt(int row, int column) {
        return tablePanels.get(currentTable).getTable().getValueAt(row, column).toString();
    }

    @Override
    public int getCurrentSelectedRow() {
        return tablePanels.get(currentTable).getTable().getSelectedRow();
    }

    @Override
    public int getCurrentSelectedColumn() {
        return tablePanels.get(currentTable).getTable().getSelectedColumn();
    }

    @Override
    public int getColumnCount() {
        return tablePanels.get(currentTable).getTable().getColumnCount();
    }

    @Override
    public int getRowCount() {
        return tablePanels.get(currentTable).getTable().getRowCount();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return tablePanels.get(currentTable).getTable().getColumnName(columnIndex);
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
}