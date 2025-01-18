package domain;

import data.dao.*;
import data.dependencies.UserContract;
import data.entities.Book;
import data.entities.Borrowing;

import java.lang.reflect.Field;
import java.util.List;

/**
 * The UserModel class is a part of the domain layer and implements the UserContract.Model interface.
 * It is responsible for handling the data access logic related to the user, including fetching titles,
 * available titles, and borrowing history.
 */
public class UserModel implements UserContract.Model {
    private int userId;
    private final BookDAO bookDAO = new BookDAO();
    private final BorrowingDAO borrowingDAO = new BorrowingDAO();
    private final CopyDAO copyDAO = new CopyDAO();
    private final LibrarianModel librarianModel = new LibrarianModel();
    private final MemberDAO memberDAO = new MemberDAO();
    private final PublisherDAO publisherDAO = new PublisherDAO();

    /**
     * Sets the userId for the UserModel.
     *
     * @param userId the ID of the user
     */
    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * A generic method to map entities to column data for display.
     * This method retrieves the values of fields from the provided list of entities
     * and organizes them into a 2D array.
     *
     * @param <T> the type of the entity
     * @param columnNames the column names to be displayed
     * @param entities the list of entities to be mapped
     * @param entityClass the class of the entity type
     * @return a 2D array of String values representing the mapped entities
     */
    private <T> String[][] mapEntitiesToColumns(List<String> columnNames, List<T> entities, Class<T> entityClass) {
        String[][] result = new String[entities.size() + 1][columnNames.size()];

        // Setting column names in the first row
        for (int i = 0; i < columnNames.size(); i++) {
            result[0][i] = columnNames.get(i);
        }

        // Populating the entity values in subsequent rows
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

    /**
     * Retrieves data for all book titles in the library.
     * This method fetches all books and returns them as a 2D array of strings.
     * Each row represents a book with its corresponding details.
     *
     * @return a 2D array of strings representing all book titles and their details
     */
    @Override
    public String[][] getAllTitlesData() {
        List<String> columnNames = bookDAO.getColumnNames();
        List<Book> books = bookDAO.getAll();
        return mapEntitiesToColumns(columnNames, books, Book.class);
    }

    /**
     * Retrieves data for books that are currently available in the library.
     * This method filters out books that are not available and returns the available books as a 2D array.
     *
     * @return a 2D array of strings representing the available books and their details
     */
    @Override
    public String[][] getAvailableTitlesData() {
        List<String> columnNames = bookDAO.getColumnNames();

        // Fetch available book titles by checking the status of each copy
        List<String> availableBookTitles = copyDAO.getAll().stream()
                .filter(copy -> "Available".equals(copy.getStatus()))
                .map(copy -> copy.getBook().getTitle())
                .distinct()
                .toList();

        // Filter books to include only those with available copies
        List<Book> availableBooks = bookDAO.getAll().stream()
                .filter(book -> availableBookTitles.contains(book.getTitle()))
                .toList();

        return mapEntitiesToColumns(columnNames, availableBooks, Book.class);
    }

    /**
     * Retrieves the borrowing history of a user.
     * This method filters borrowings associated with the user's ID and returns the borrowing data
     * as a 2D array of strings.
     *
     * @return a 2D array of strings representing the user's borrowing history
     */
    @Override
    public String[][] getBorrowingHistoryData() {
        List<String> columnNames = borrowingDAO.getColumnNames();

        // Get borrowings related to the user
        List<Borrowing> borrowings = borrowingDAO.getAll().stream()
                .filter(borrowing -> borrowing.getMember().getId() == userId)
                .toList();

        return mapEntitiesToColumns(columnNames, borrowings, Borrowing.class);
    }
}