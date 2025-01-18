package ui;

import data.constants.Fonts;
import data.constants.Palette;
import data.dependencies.UserContract;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.function.Consumer;

/**
 * The UserView class represents a user interface panel for library users.
 * It displays three tabs: all library titles, available titles for borrowing, and the user's borrowing history.
 * This class extends JPanel and implements the UserContract.View interface.
 */
public class UserView extends JPanel implements UserContract.View {

    private final TablePanel allTiles = new TablePanel("All Library Titles");
    private final TablePanel availableTiles = new TablePanel("Available Titles for Borrowing");
    private final TablePanel borrowingHistory = new TablePanel("Your Borrowing History");

    private final JTabbedPane tabbedPane = new JTabbedPane();

    /**
     * Constructs the UserView panel.
     * Initializes and configures the tabbed pane and table panels for the user interface.
     */
    public UserView() {
        configure();
        configureTabbedPane();
        configureTablePanel(allTiles);
        configureTablePanel(availableTiles);
        configureTablePanel(borrowingHistory);
        addAll();
    }

    /**
     * Configures the layout for the UserView panel.
     */
    private void configure() {
        setLayout(new BorderLayout());
    }

    /**
     * Configures the appearance and behavior of a given TablePanel.
     *
     * @param tablePanel the TablePanel to configure
     */
    private void configureTablePanel(TablePanel tablePanel) {
        tablePanel.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tablePanel.getTable().setDefaultEditor(Object.class, null);
        tablePanel.getTable().getTableHeader().setReorderingAllowed(false);

        tablePanel.getTable().setRowHeight(35);

        tablePanel.getTable().getTableHeader().setBackground(Palette.HEADER_TABLE_ROW.getColor());
        Fonts.applyToComponent(
                tablePanel.getTable().getTableHeader(),
                Fonts.BODY_FONT
        );

        tablePanel.getTable().setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (row % 2 == 0) {
                    component.setBackground(Palette.EVEN_TABLE_ROW.getColor());
                } else {
                    component.setBackground(Palette.ODD_TABLE_ROW.getColor());
                }

                Fonts.applyToComponent(
                        component,
                        Fonts.BODY_FONT
                );

                if (column > 0) {
                    ((JLabel) component).setHorizontalAlignment(SwingConstants.RIGHT);
                } else {
                    ((JLabel) component).setHorizontalAlignment(SwingConstants.LEFT);
                }

                if (isSelected) {
                    component.setBackground(Palette.SELECTED.getColor());
                    Fonts.applyToComponent(
                            component,
                            Fonts.SELECTED_BODY_FONT
                    );
                }

                return component;
            }
        });
    }

    /**
     * Configures the tabbed pane by adding tabs for all titles, available titles, and borrowing history.
     */
    private void configureTabbedPane() {
        tabbedPane.addTab("All Titles", allTiles);
        tabbedPane.addTab("Available Titles", availableTiles);
        tabbedPane.addTab("My Borrowing History", borrowingHistory);

        tabbedPane.setSelectedIndex(0);

        Fonts.applyToComponent(
                tabbedPane,
                Fonts.BODY_FONT
        );

        tabbedPane.addChangeListener(null);
    }

    /**
     * Adds the tabbed pane to the main panel layout.
     */
    private void addAll() {
        add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Creates a dialog box to prompt the user for their user ID.
     *
     * @param consumer a Consumer callback to handle the entered user ID
     */
    @Override
    public void createUserIdInputPane(Consumer<String> consumer) {
        String inputText = "";
        while (inputText == null || inputText.isEmpty()) {
            inputText = JOptionPane.showInputDialog(
                    null,
                    "Enter your user Id:",
                    "Id Input",
                    JOptionPane.PLAIN_MESSAGE
            );
        }
        consumer.accept(inputText);
    }

    /**
     * Fills the "All Titles" table with data.
     *
     * @param columns the column headers for the table
     * @param data    the data to populate the table
     */
    @Override
    public void fillAllTitles(String[] columns, String[][] data) {
        allTiles.getTable().setModel(new DefaultTableModel(data, columns));
    }

    /**
     * Fills the "Available Titles" table with data.
     *
     * @param columns the column headers for the table
     * @param data    the data to populate the table
     */
    @Override
    public void fillAvailableTitles(String[] columns, String[][] data) {
        availableTiles.getTable().setModel(new DefaultTableModel(data, columns));
    }

    /**
     * Fills the "Borrowing History" table with data.
     *
     * @param columns the column headers for the table
     * @param data    the data to populate the table
     */
    @Override
    public void fillBorrowingHistory(String[] columns, String[][] data) {
        borrowingHistory.getTable().setModel(new DefaultTableModel(data, columns));
    }
}