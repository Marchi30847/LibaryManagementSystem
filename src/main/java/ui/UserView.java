package ui;

import data.constants.Fonts;
import data.constants.Palette;
import data.dependencies.UserContract;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.function.Consumer;

public class UserView extends JPanel implements UserContract.View {
    private final TablePanel allTiles = new TablePanel("All Library Titles");
    private final TablePanel availableTiles = new TablePanel("Available Titles for Borrowing");
    private final TablePanel borrowingHistory = new TablePanel("Your Borrowing History");

    private final JTabbedPane tabbedPane = new JTabbedPane();

    public UserView() {
        configure();
        configureTabbedPane();
        configureTablePanel(allTiles);
        configureTablePanel(availableTiles);
        configureTablePanel(borrowingHistory);
        addAll();
    }

    private void configure() {
        setLayout(new BorderLayout());
    }

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

    private void addAll() {
        add(tabbedPane, BorderLayout.CENTER);
    }

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

    @Override
    public void fillAllTitles(String[] columns, String[][] data) {
        allTiles.getTable().setModel(new DefaultTableModel(data, columns));
    }

    @Override
    public void fillAvailableTitles(String[] columns, String[][] data) {
        availableTiles.getTable().setModel(new DefaultTableModel(data, columns));
    }

    @Override
    public void fillBorrowingHistory(String[] columns, String[][] data) {
        borrowingHistory.getTable().setModel(new DefaultTableModel(data, columns));
    }
}