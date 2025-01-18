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

public class LibrarianView extends JPanel implements LibrarianContract.View {
    private final Map<Tables, TablePanel> tablePanels = new EnumMap<>(Tables.class);
    private Tables currentTable;

    private final JTabbedPane tabbedPane = new JTabbedPane();

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
        for (Tables tableType : Tables.values()) {
            TablePanel tablePanel = new TablePanel("Manage " + tableType.name());
            configureTablePanel(tablePanel);
            tablePanels.put(tableType, tablePanel);
        }
        currentTable = Tables.BOOK;
    }

    private void configureTablePanel(TablePanel tablePanel) {
        JTable table = tablePanel.getTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setDefaultEditor(Object.class, null);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(35);

        table.getTableHeader().setBackground(Palette.HEADER_TABLE_ROW.getColor());
        Fonts.applyToComponent(table.getTableHeader(), Fonts.BODY_FONT);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (row % 2 == 0) {
                    component.setBackground(Palette.EVEN_TABLE_ROW.getColor());
                } else {
                    component.setBackground(Palette.ODD_TABLE_ROW.getColor());
                }

                Fonts.applyToComponent(component, Fonts.BODY_FONT);

                if (column > 0) {
                    ((JLabel) component).setHorizontalAlignment(SwingConstants.RIGHT);
                } else {
                    ((JLabel) component).setHorizontalAlignment(SwingConstants.LEFT);
                }

                if (isSelected) {
                    component.setBackground(Palette.SELECTED.getColor());
                    Fonts.applyToComponent(component, Fonts.SELECTED_BODY_FONT);
                }

                return component;
            }
        });
    }

    private void configureButtons() {
        insertButton.setText("Insert");
        Fonts.applyToComponent(insertButton, Fonts.BUTTON_FONT);

        deleteButton.setText("Delete");
        Fonts.applyToComponent(deleteButton, Fonts.BUTTON_FONT);

        updateButton.setText("Update");
        Fonts.applyToComponent(updateButton, Fonts.BUTTON_FONT);
    }

    private void configureButtonPanel() {
        buttonPanel.setLayout(new GridLayout(1, 3, 50, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
        buttonPanel.add(insertButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
    }

    private void configureTabbedPane() {
        for (Tables tableType : Tables.values()) {
            tabbedPane.addTab("Manage " + tableType.name(), tablePanels.get(tableType));
        }
        tabbedPane.setSelectedIndex(0);
        Fonts.applyToComponent(tabbedPane, Fonts.BODY_FONT);

        addTabChangedListener();
    }

    private void addAll() {
        add(tabbedPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void addTabChangedListener() {
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