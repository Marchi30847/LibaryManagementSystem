package domain;

import data.dao.*;
import data.dependencies.UserContract;
import data.entities.Book;
import data.entities.Borrowing;

import java.lang.reflect.Field;
import java.util.List;

public class UserModel implements UserContract.Model {
    private int userId;
    private final BookDAO bookDAO = new BookDAO();
    private final BorrowingDAO borrowingDAO = new BorrowingDAO();
    private final CopyDAO copyDAO = new CopyDAO();
    private final LibrarianModel librarianModel = new LibrarianModel();
    private final MemberDAO memberDAO = new MemberDAO();
    private final PublisherDAO publisherDAO = new PublisherDAO();

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    private <T> String[][] mapEntitiesToColumns(List<String> columnNames, List<T> entities, Class<T> entityClass) {
        String[][] result = new String[entities.size() + 1][columnNames.size()];

        for (int i = 0; i < columnNames.size(); i++) {
            result[0][i] = columnNames.get(i);
        }

        for (int j = 0; j < entities.size(); j++) {
            T entity = entities.get(j);

            for (int i = 0; i < columnNames.size(); i++) {
                String columnName = columnNames.get(i);

                try {
                    Field field = entityClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    Object value = field.get(entity);
                    result[j + 1][i] = value != null ? value.toString() : "null";
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Error accessing field: " + columnName, e);
                }
            }
        }

        return result;
    }

    @Override
    public String[][] getAllTitlesData() {
        List<String> columnNames = bookDAO.getColumnNames();
        List<Book> books = bookDAO.getAll();
        return mapEntitiesToColumns(columnNames, books, Book.class);
    }

    @Override
    public String[][] getAvailableTitlesData() {
        List<String> columnNames = bookDAO.getColumnNames();

        List<String> availableBookTitles = copyDAO.getAll().stream()
                .filter(copy -> "Available".equals(copy.getStatus()))
                .map(copy -> copy.getBook().getTitle())
                .distinct()
                .toList();

        List<Book> availableBooks = bookDAO.getAll().stream()
                .filter(book -> availableBookTitles.contains(book.getTitle()))
                .toList();

        return mapEntitiesToColumns(columnNames, availableBooks, Book.class);
    }

    @Override
    public String[][] getBorrowingHistoryData() {
        List<String> columnNames = borrowingDAO.getColumnNames();
        List<Borrowing> borrowings = borrowingDAO.getAll().stream()
                .filter(borrowing -> borrowing.getMember().getId() == userId)
                .toList();
        return mapEntitiesToColumns(columnNames, borrowings, Borrowing.class);
    }
}