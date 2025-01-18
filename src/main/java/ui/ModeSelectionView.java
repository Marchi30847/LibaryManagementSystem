package ui;

import data.constants.Fonts;
import data.dependencies.SelectionView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ModeSelectionView extends JPanel implements SelectionView {
    private final JButton option1 = new JButton();
    private final JButton option2 = new JButton();

    public ModeSelectionView() {
        configure();
        configureOption1();
        configureOption2();
        addAll();
    }

    private void configure() {
        setLayout(new GridLayout(2, 1, 0, 20));
        setBorder(BorderFactory.createEmptyBorder(
                200,
                400,
                200,
                400
        ));
    }

    private void configureOption1() {
        option1.setText("LIBRARIAN");
        Fonts.applyToComponent(
                option1,
                Fonts.BUTTON_FONT
        );
    }

    private void configureOption2() {
        option2.setText("USER");
        Fonts.applyToComponent(
                option2,
                Fonts.BUTTON_FONT
        );
    }

    private void addAll() {
        add(option1);
        add(option2);
    }

    @Override
    public void addLibrarianModeChangeListener(ActionListener listener) {
        option1.addActionListener(listener);
        System.out.println();
    }

    @Override
    public void addUserModeChangeListener(ActionListener listener) {
        option2.addActionListener(listener);
    }
}
