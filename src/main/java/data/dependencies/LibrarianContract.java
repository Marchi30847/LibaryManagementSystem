package data.dependencies;

import data.constants.Tables;
import data.entities.*;

import java.awt.event.ActionListener;

public interface LibrarianContract {
    interface View {
        void addInsertButtonListener(ActionListener listener);
        void addDeleteButtonListener(ActionListener listener);
        void addUpdateButtonListener(ActionListener listener);
        void addTabChangedListener();
        int getCurrentSelectedRow();
        Tables getCurrentTable();
        String getValueAt(int row, int column);
        void updateTableModel(Tables tableType, String[] columns, String[][] data);
        String createInputDialog(String title, String initialValue);
        void createErrorDialog(String title, String message);
    }

    interface Presenter {
        void initListeners();
        void fillTables();
    }

    interface Model {
        String[][] getBooksData();
        String[][] getBorrowingsData();
        String[][] getCopiesData();
        String[][] getLibrariansData();
        String[][] getMembersData();
        String[][] getPublishersData();

        void insertBook(String title , String author, String publisherId, String year, String isbn);
        void insertBorrowing(String memberId, String copyId);
        void insertCopy(String bookId, String copyNumber, String condition);
        void insertLibrarian(String memberId, String position);
        void insertMember(String name, String email, String phone, String address);
        void insertPublisher(String name, String address, String phone);

        void deleteBook(int bookRow);
        void deleteBorrowing(int borrowingRow);
        void deleteCopy(int copyRow);
        void deleteLibrarian(int librarianRow);
        void deleteMember(int memberRow);
        void deletePublisher(int publisherRow);

        void updateBook(int id, String title , String author, String publisherId, String year, String isbn);
        void updateBorrowing(int id, String memberId, String copyId, String borrowDate, String returnDate);
        void updateCopy(int id, String bookId, String copyNumber, String condition);
        void updateLibrarian(int id, String memberId, String employmentDate, String position);
        void updateMember(int id, String name, String email, String phone, String address);
        void updatePublisher(int id, String name, String address, String phone);
    }
}
