package presentation;

import data.dependencies.UserContract;

import java.util.Arrays;

/**
 * The UserPresenter class is responsible for managing the interaction between the UserView and UserModel.
 * It handles input from the user, updates the view with data, and performs necessary actions related to user management.
 */
public class UserPresenter implements UserContract.Presenter {
    private final UserContract.View view;  // The view associated with this presenter
    private final UserContract.Model model;  // The model associated with this presenter

    private int userID;  // The ID of the user

    /**
     * Constructor that initializes the UserPresenter with the specified view and model.
     * It also handles user input for the user ID and sets up the necessary listeners.
     *
     * @param view The view associated with this presenter
     * @param model The model associated with this presenter
     */
    public UserPresenter(UserContract.View view, UserContract.Model model) {
        this.view = view;
        this.model = model;

        // Prompt the user to input their user ID and store it
        view.createUserIdInputPane(
                input -> userID = Integer.parseInt(input)  // Parse the user input as an integer
        );

        // Initialize any listeners (currently empty)
        initListeners();

        // Set the user ID in the model
        model.setUserId(userID);

        // Fill the tables with relevant data from the model
        fillTables();
    }

    /**
     * Initializes the listeners for the view. Currently, this method is empty but can be used to add listeners
     * for various user interactions in the future.
     */
    @Override
    public void initListeners() {
        // No listeners are currently initialized in this method.
    }

    /**
     * Fills the tables in the view with data retrieved from the model.
     * This method retrieves the data for all titles, available titles, and borrowing history,
     * and then populates the corresponding tables in the view.
     */
    @Override
    public void fillTables() {
        // Get the data for all titles from the model
        String[][] allTitlesData = model.getAllTitlesData();
        String[] allTitlesColumns = allTitlesData[0];  // Extract the column names
        String[][] allTitlesRows = getDataRows(allTitlesData);  // Extract the data rows (excluding header)
        view.fillAllTitles(allTitlesColumns, allTitlesRows);  // Fill the all titles table in the view

        // Get the data for available titles from the model
        String[][] availableTitlesData = model.getAvailableTitlesData();
        String[] availableTitlesColumns = availableTitlesData[0];  // Extract the column names
        String[][] availableTitlesRows = getDataRows(availableTitlesData);  // Extract the data rows (excluding header)
        view.fillAvailableTitles(availableTitlesColumns, availableTitlesRows);  // Fill the available titles table in the view

        // Get the data for borrowing history from the model
        String[][] borrowingHistoryData = model.getBorrowingHistoryData();
        String[] borrowingHistoryColumns = borrowingHistoryData[0];  // Extract the column names
        String[][] borrowingHistoryRows = getDataRows(borrowingHistoryData);  // Extract the data rows (excluding header)
        view.fillBorrowingHistory(borrowingHistoryColumns, borrowingHistoryRows);  // Fill the borrowing history table in the view
    }

    /**
     * Helper method to extract the data rows from the provided 2D data array.
     * This method skips the first row (header row) and returns the remaining rows of data.
     *
     * @param data The 2D array of data, where the first row contains the column headers
     * @return A 2D array containing only the data rows (excluding the header)
     */
    private String[][] getDataRows(String[][] data) {
        return Arrays.stream(data)
                .skip(1)  // Skip the first row (header)
                .toArray(String[][]::new);  // Convert the stream back to a 2D array
    }
}