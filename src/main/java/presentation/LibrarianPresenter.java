package presentation;

import data.constants.Tables;
import data.dependencies.LibrarianContract;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LibrarianPresenter implements LibrarianContract.Presenter {
    private final LibrarianContract.View view;
    private final LibrarianContract.Model model;

    public LibrarianPresenter(LibrarianContract.View view, LibrarianContract.Model model) {
        this.view = view;
        this.model = model;
        initListeners();
        fillTables();
    }

    @Override
    public void initListeners() {
        view.addInsertButtonListener(createInsertTitleButtonListener());
        view.addDeleteButtonListener(createDeleteTitleButtonListener());
        view.addUpdateButtonListener(createUpdateTitleButtonListener());
    }

    private ActionListener createInsertTitleButtonListener() {
        return e -> {
                switch (view.getCurrentTable()) {
                    case BOOK -> {
                        handleAddBook();
                        fillBook();
                    }
                    case BORROWING -> {
                        handleAddBorrowing();
                        fillBorrowing();
                    }
                    case COPY -> {
                        handleAddCopy();
                        fillCopy();
                    }
                    case LIBRARIAN -> {
                        handleAddLibrarian();
                        fillLibrarian();
                    }
                    case MEMBER -> {
                        handleAddMember();
                        fillMember();
                    }
                    case PUBLISHER -> {
                        handleAddPublisher();
                        fillPublisher();
                    }
                }
        };
    }

    private ActionListener createDeleteTitleButtonListener() {
       return e -> {
            switch (view.getCurrentTable()) {
                case BOOK -> {
                    handleDeleteBook();
                    fillBook();
                }
                case BORROWING -> {
                    handleDeleteBorrowing();
                    fillBorrowing();
                }
                case COPY -> {
                    handleDeleteCopy();
                    fillCopy();
                }
                case LIBRARIAN -> {
                    handleDeleteLibrarian();
                    fillLibrarian();
                }
                case MEMBER -> {
                    handleDeleteMember();
                    fillMember();
                }
                case PUBLISHER -> {
                    handleDeletePublisher();
                    fillPublisher();
                }
            }
       };
    }

    private ActionListener createUpdateTitleButtonListener() {
        return null;
    }

    @Override
    public void fillTables() {
        fillBook();
        fillBorrowing();
        fillCopy();
        fillLibrarian();
        fillMember();
        fillPublisher();
    }

    private void fillBook() {
        String[][] booksData = model.getBooksData();
        String[] booksColumns = booksData[0];
        String[][] booksRows = getDataRows(booksData);
        view.updateTableModel(Tables.BOOK, booksColumns, booksRows);
    }

    private void fillBorrowing() {
        String[][] borrowingsData = model.getBorrowingsData();
        String[] borrowingsColumns = borrowingsData[0];
        String[][] borrowingRows = getDataRows(borrowingsData);
        view.updateTableModel(Tables.BORROWING, borrowingsColumns, borrowingRows);
    }

    private void fillCopy() {
        String[][] copyData = model.getCopiesData();
        String[] copiesColumns = copyData[0];
        String[][] copiesRows = getDataRows(copyData);
        view.updateTableModel(Tables.COPY, copiesColumns, copiesRows);
    }

    private void fillLibrarian() {
        String[][] librariansData = model.getLibrariansData();
        String[] librariansColumns = librariansData[0];
        String[][] librariansRows = getDataRows(librariansData);
        view.updateTableModel(Tables.LIBRARIAN, librariansColumns, librariansRows);
    }

    private void fillMember() {
        String[][] membersData = model.getMembersData();
        String[] membersColumns = membersData[0];
        String[][] membersRows = getDataRows(membersData);
        view.updateTableModel(Tables.MEMBER, membersColumns, membersRows);
    }

    private void fillPublisher() {
        String[][] publishersData = model.getPublishersData();
        String[] publishersColumns = publishersData[0];
        String[][] publishersRows = getDataRows(publishersData);
        view.updateTableModel(Tables.PUBLISHER, publishersColumns, publishersRows);
    }

    private String[][] getDataRows(String[][] data) {
        return Arrays.stream(data)
                .skip(1)
                .toArray(String[][]::new);
    }

    private void handleAddBook() {
        String title = JOptionPane.showInputDialog("Enter Title:");
        String author = JOptionPane.showInputDialog("Enter Author:");
        String publisherId = JOptionPane.showInputDialog("Enter Publisher ID:");
        String year = JOptionPane.showInputDialog("Enter Year:");
        String isbn = JOptionPane.showInputDialog("Enter ISBN:");

        if (title == null || author == null || publisherId == null || year == null || isbn == null) {
            view.createErrorDialog("Error", "All fields are required.");
            return;
        }

        model.insertBook(title, author, publisherId, year, isbn);
    }

    private void handleAddBorrowing() {
        String memberId = JOptionPane.showInputDialog("Enter Member ID:");
        String copyId = JOptionPane.showInputDialog("Enter Copy ID:");

        if (memberId == null || copyId == null) {
            view.createErrorDialog("Error", "All fields are required.");
            return;
        }

        model.insertBorrowing(memberId, copyId);
    }

    private void handleAddCopy() {
        String bookId = JOptionPane.showInputDialog("Enter Book ID:");
        String copyNumber = JOptionPane.showInputDialog("Enter Copy Number:");
        String condition = JOptionPane.showInputDialog("Enter Condition:");

        if (bookId == null || copyNumber == null || condition == null) {
            view.createErrorDialog("Error", "All fields are required.");
            return;
        }

        model.insertCopy(bookId, copyNumber, condition);
    }

    private void handleAddLibrarian() {
        String memberId = JOptionPane.showInputDialog("Enter Member ID:");
        String position = JOptionPane.showInputDialog("Enter Position:");

        if (memberId == null || position == null) {
            view.createErrorDialog("Error", "All fields are required.");
            return;
        }

        model.insertLibrarian(memberId, position);
    }

    private void handleAddMember() {
        String name = JOptionPane.showInputDialog("Enter Name:");
        String email = JOptionPane.showInputDialog("Enter Email:");
        String phone = JOptionPane.showInputDialog("Enter Phone:");
        String address = JOptionPane.showInputDialog("Enter Address:");

        if (name == null || email == null || phone == null || address == null) {
            view.createErrorDialog("Error", "All fields are required.");
            return;
        }

        model.insertMember(name, email, phone, address);
    }

    private void handleAddPublisher() {
        String name = JOptionPane.showInputDialog("Enter Publisher Name:");
        String address = JOptionPane.showInputDialog("Enter Publisher Address:");
        String phone = JOptionPane.showInputDialog("Enter Publisher Phone:");

        if (name == null || address == null || phone == null) {
            JOptionPane.showMessageDialog(null, "All fields are required.");
            return;
        }

        model.insertPublisher(name, address, phone);
    }

    private void handleDeleteBook() {
        int selectedRow = view.getCurrentSelectedRow();
        if (selectedRow < 0) {
            view.createErrorDialog("Error", "Please select a book to delete.");
            return;
        }

        model.deleteBook(selectedRow);
    }

    private void handleDeleteBorrowing() {
        int selectedRow = view.getCurrentSelectedRow();
        if (selectedRow < 0) {
            view.createErrorDialog("Error", "Please select a borrowing to delete.");
            return;
        }

        model.deleteBorrowing(selectedRow);
    }

    private void handleDeleteCopy() {
        int selectedRow = view.getCurrentSelectedRow();
        if (selectedRow < 0) {
            view.createErrorDialog("Error", "Please select a copy to delete.");
            return;
        }

        model.deleteCopy(selectedRow);
    }

    private void handleDeleteLibrarian() {
        int selectedRow = view.getCurrentSelectedRow();
        if (selectedRow < 0) {
            view.createErrorDialog("Error", "Please select a librarian to delete.");
            return;
        }

        model.deleteLibrarian(selectedRow);
    }

    private void handleDeleteMember() {
        int selectedRow = view.getCurrentSelectedRow();
        if (selectedRow < 0) {
            view.createErrorDialog("Error", "Please select a member to delete.");
            return;
        }

        model.deleteMember(selectedRow);
    }

    private void handleDeletePublisher() {
        int selectedRow = view.getCurrentSelectedRow();
        if (selectedRow < 0) {
            view.createErrorDialog("Error", "Please select a publisher to delete.");
            return;
        }

        model.deletePublisher(selectedRow);
    }
}
