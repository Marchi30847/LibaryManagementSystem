package data.dependencies;

import javax.swing.event.ChangeListener;
import java.util.Map;
import java.util.function.Consumer;

public interface UserContract {
    interface View {
        void createUserIdInputPane(Consumer<String> idSetter);
        void fillAllTitles(String[] columns, String[][] data);
        void fillAvailableTitles(String[] columns, String[][] data);
        void fillBorrowingHistory(String[] columns, String[][] data);
    }

    interface Presenter {
        void initListeners();
        void fillTables();
    }

    interface Model {
        void setUserId(int userId);
        String[][] getAllTitlesData();
        String[][] getAvailableTitlesData();
        String[][] getBorrowingHistoryData();
    }
}
