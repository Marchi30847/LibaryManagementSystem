package data.dependencies;

import java.awt.event.ActionListener;

public interface SelectionView {
    void addLibrarianModeChangeListener(ActionListener listener);
    void addUserModeChangeListener(ActionListener listener);
}
