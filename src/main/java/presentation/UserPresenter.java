package presentation;

import data.dependencies.UserContract;

import java.util.Arrays;

public class UserPresenter implements UserContract.Presenter {
    private final UserContract.View view;
    private final UserContract.Model model;

    private int userID;

    public UserPresenter(UserContract.View view, UserContract.Model model) {
        this.view = view;
        this.model = model;
        view.createUserIdInputPane(
                input -> userID = Integer.parseInt(input)
        );
        initListeners();
        model.setUserId(userID);
        fillTables();
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void fillTables() {
        String[][] allTitlesData = model.getAllTitlesData();
        String[] allTitlesColumns = allTitlesData[0];
        String[][] allTitlesRows = getDataRows(allTitlesData);
        view.fillAllTitles(allTitlesColumns, allTitlesRows);

        String[][] availableTitlesData = model.getAvailableTitlesData();
        String[] availableTitlesColumns = availableTitlesData[0];
        String[][] availableTitlesRows = getDataRows(availableTitlesData);
        view.fillAvailableTitles(availableTitlesColumns, availableTitlesRows);

        String[][] borrowingHistoryData = model.getBorrowingHistoryData();
        String[] borrowingHistoryColumns = borrowingHistoryData[0];
        String[][] borrowingHistoryRows = getDataRows(borrowingHistoryData);
        view.fillBorrowingHistory(borrowingHistoryColumns, borrowingHistoryRows);
    }

    private String[][] getDataRows(String[][] data) {
        return Arrays.stream(data)
                .skip(1)
                .toArray(String[][]::new);
    }
}
