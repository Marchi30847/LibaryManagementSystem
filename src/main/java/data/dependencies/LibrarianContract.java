package data.dependencies;

import java.awt.event.ActionListener;

public interface LibrarianContract {
    interface View {
        void addInsertButtonListener(ActionListener listener);
        void addDeleteButtonListener(ActionListener listener);
        void addUpdateButtonListener(ActionListener listener);
        void addTabChangedListener();
        int getCurrentSelectedRow();
    }

    interface Presenter {
        void initListeners();
        void fillTables();
    }

    interface Model {

        String[][] getBooksData();
        String[][] getMembersData();
        String[][] getBorrowingsData();
    }
}
