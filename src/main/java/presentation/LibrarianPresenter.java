package presentation;

import data.dependencies.LibrarianContract;

import java.awt.event.ActionListener;

public class LibrarianPresenter implements LibrarianContract.Presenter {
    private final LibrarianContract.View view;
    private final LibrarianContract.Model model;

    public LibrarianPresenter(LibrarianContract.View view, LibrarianContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void initListeners() {
        view.addInsertButtonListener(createInsertTitleButtonListener());
        view.addDeleteButtonListener(createDeleteTitleButtonListener());
        view.addUpdateButtonListener(createUpdateTitleButtonListener());
    }

    @Override
    public void fillTables() {

    }

    private ActionListener createInsertTitleButtonListener() {
        return null;
    }

    private ActionListener createDeleteTitleButtonListener() {
        return null;
    }

    private ActionListener createUpdateTitleButtonListener() {
        return null;
    }
}
