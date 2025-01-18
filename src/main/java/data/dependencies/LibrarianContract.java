package data.dependencies;

import data.constants.Tables;
import data.entities.*;

import java.awt.event.ActionListener;

/**
 * Contract for the Librarian functionality in the application.
 * This interface defines the methods for the View, Presenter, and Model related to managing librarian actions.
 * It includes methods for interacting with and managing books, borrowings, copies, librarians, members, and publishers.
 */
public interface LibrarianContract {

    /**
     * View interface for the Librarian's actions.
     * This interface handles interactions with the user interface, such as adding listeners and updating tables.
     */
    interface View {

        /**
         * Adds an action listener to the insert button.
         * The listener will be triggered when the user clicks the insert button.
         *
         * @param listener The action listener to be added.
         */
        void addInsertButtonListener(ActionListener listener);

        /**
         * Adds an action listener to the delete button.
         * The listener will be triggered when the user clicks the delete button.
         *
         * @param listener The action listener to be added.
         */
        void addDeleteButtonListener(ActionListener listener);

        /**
         * Adds an action listener to the update button.
         * The listener will be triggered when the user clicks the update button.
         *
         * @param listener The action listener to be added.
         */
        void addUpdateButtonListener(ActionListener listener);

        /**
         * Adds a listener to handle tab changes in the UI.
         */
        void addTabChangedListener();

        /**
         * Retrieves the currently selected row in the table.
         *
         * @return The row index of the selected row.
         */
        int getCurrentSelectedRow();

        /**
         * Retrieves the current table type being viewed.
         *
         * @return The current table type as a {@link Tables} enum.
         */
        Tables getCurrentTable();

        /**
         * Retrieves the value at a specific row and column in the table.
         *
         * @param row    The row index.
         * @param column The column index.
         * @return The value in the specified cell.
         */
        String getValueAt(int row, int column);

        /**
         * Updates the table model with new data.
         *
         * @param tableType The table type (Books, Borrowings, etc.).
         * @param columns   The column headers for the table.
         * @param data      The data to be displayed in the table.
         */
        void updateTableModel(Tables tableType, String[] columns, String[][] data);

        /**
         * Creates an input dialog where the user can input a value.
         *
         * @param title        The title of the input dialog.
         * @param initialValue The initial value to be displayed in the dialog.
         * @return The value entered by the user.
         */
        String createInputDialog(String title, String initialValue);

        /**
         * Creates an error dialog to display an error message to the user.
         *
         * @param title   The title of the error dialog.
         * @param message The error message to be displayed.
         */
        void createErrorDialog(String title, String message);
    }

    /**
     * Presenter interface for the Librarian functionality.
     * This interface defines methods for handling user interactions and managing the view and model.
     */
    interface Presenter {

        /**
         * Initializes listeners for all UI components in the Librarian interface.
         */
        void initListeners();

        /**
         * Fills the tables with the data required for display.
         */
        void fillTables();
    }

    /**
     * Model interface for the Librarian functionality.
     * This interface defines methods for accessing and manipulating the data related to books, borrowings, copies, librarians, members, and publishers.
     */
    interface Model {

        /**
         * Retrieves the data for the books.
         *
         * @return A 2D array representing the data for books.
         */
        String[][] getBooksData();

        /**
         * Retrieves the data for the borrowings.
         *
         * @return A 2D array representing the data for borrowings.
         */
        String[][] getBorrowingsData();

        /**
         * Retrieves the data for the copies.
         *
         * @return A 2D array representing the data for copies.
         */
        String[][] getCopiesData();

        /**
         * Retrieves the data for the librarians.
         *
         * @return A 2D array representing the data for librarians.
         */
        String[][] getLibrariansData();

        /**
         * Retrieves the data for the members.
         *
         * @return A 2D array representing the data for members.
         */
        String[][] getMembersData();

        /**
         * Retrieves the data for the publishers.
         *
         * @return A 2D array representing the data for publishers.
         */
        String[][] getPublishersData();

        /**
         * Inserts a new book into the system.
         *
         * @param title      The title of the book.
         * @param author     The author of the book.
         * @param publisherId The publisher's ID.
         * @param year       The publication year of the book.
         * @param isbn       The ISBN of the book.
         */
        void insertBook(String title, String author, String publisherId, String year, String isbn);

        /**
         * Inserts a new borrowing record into the system.
         *
         * @param memberId The ID of the member borrowing the book.
         * @param copyId   The ID of the copy being borrowed.
         */
        void insertBorrowing(String memberId, String copyId);

        /**
         * Inserts a new copy of a book into the system.
         *
         * @param bookId    The ID of the book.
         * @param copyNumber The number of the copy.
         * @param condition The condition of the copy.
         */
        void insertCopy(String bookId, String copyNumber, String condition);

        /**
         * Inserts a new librarian into the system.
         *
         * @param memberId The ID of the member who is a librarian.
         * @param position The position of the librarian.
         */
        void insertLibrarian(String memberId, String position);

        /**
         * Inserts a new member into the system.
         *
         * @param name    The name of the member.
         * @param email   The email address of the member.
         * @param phone   The phone number of the member.
         * @param address The address of the member.
         */
        void insertMember(String name, String email, String phone, String address);

        /**
         * Inserts a new publisher into the system.
         *
         * @param name    The name of the publisher.
         * @param address The address of the publisher.
         * @param phone   The phone number of the publisher.
         */
        void insertPublisher(String name, String address, String phone);

        /**
         * Deletes a book from the system.
         *
         * @param bookRow The row index of the book to be deleted.
         */
        void deleteBook(int bookRow);

        /**
         * Deletes a borrowing record from the system.
         *
         * @param borrowingRow The row index of the borrowing record to be deleted.
         */
        void deleteBorrowing(int borrowingRow);

        /**
         * Deletes a copy from the system.
         *
         * @param copyRow The row index of the copy to be deleted.
         */
        void deleteCopy(int copyRow);

        /**
         * Deletes a librarian from the system.
         *
         * @param librarianRow The row index of the librarian to be deleted.
         */
        void deleteLibrarian(int librarianRow);

        /**
         * Deletes a member from the system.
         *
         * @param memberRow The row index of the member to be deleted.
         */
        void deleteMember(int memberRow);

        /**
         * Deletes a publisher from the system.
         *
         * @param publisherRow The row index of the publisher to be deleted.
         */
        void deletePublisher(int publisherRow);

        /**
         * Updates the details of a book.
         *
         * @param id          The ID of the book to be updated.
         * @param title       The new title of the book.
         * @param author      The new author of the book.
         * @param publisherId The new publisher's ID.
         * @param year        The new publication year.
         * @param isbn        The new ISBN.
         */
        void updateBook(int id, String title, String author, String publisherId, String year, String isbn);

        /**
         * Updates the details of a borrowing record.
         *
         * @param id        The ID of the borrowing record to be updated.
         * @param memberId  The new member ID.
         * @param copyId    The new copy ID.
         * @param borrowDate The new borrow date.
         * @param returnDate The new return date.
         */
        void updateBorrowing(int id, String memberId, String copyId, String borrowDate, String returnDate);

        /**
         * Updates the details of a copy.
         *
         * @param id         The ID of the copy to be updated.
         * @param bookId     The new book ID.
         * @param copyNumber The new copy number.
         * @param condition  The new condition of the copy.
         */
        void updateCopy(int id, String bookId, String copyNumber, String condition);

        /**
         * Updates the details of a librarian.
         *
         * @param id           The ID of the librarian to be updated.
         * @param memberId     The new member ID.
         * @param employmentDate The new employment date.
         * @param position     The new position of the librarian.
         */
        void updateLibrarian(int id, String memberId, String employmentDate, String position);

        /**
         * Updates the details of a member.
         *
         * @param id          The ID of the member to be updated.
         * @param name        The new name of the member.
         * @param email       The new email address of the member.
         * @param phone       The new phone number of the member.
         * @param address     The new address of the member.
         */
        void updateMember(int id, String name, String email, String phone, String address);

        /**
         * Updates the details of a publisher.
         *
         * @param id          The ID of the publisher to be updated.
         * @param name        The new name of the publisher.
         * @param address     The new address of the publisher.
         * @param phone       The new phone number of the publisher.
         */
        void updatePublisher(int id, String name, String address, String phone);
    }
}