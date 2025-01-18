package domain;

import data.dependencies.LibrarianContract;

public class LibrarianModel implements LibrarianContract.Model {

    @Override
    public String[][] getBooksData() {
        return new String[0][];
    }

    @Override
    public String[][] getMembersData() {
        return new String[0][];
    }

    @Override
    public String[][] getBorrowingsData() {
        return new String[0][];
    }
}
