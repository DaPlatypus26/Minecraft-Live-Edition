package org.sandboxgame.layout;

import org.sandboxgame.Main;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JButton singlePlayerButton;
    private JButton optionsButton;
    private JButton quitButton;

    public MenuPanel() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        singlePlayerButton = new JButton("Single Player");
        optionsButton = new JButton("Options");
        quitButton = new JButton("Quit");

        singlePlayerButton.addActionListener(e -> {
            Main.changePanel(Main.worldPanel);
        });

        optionsButton.addActionListener(e -> {

        });

        quitButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    private void setupLayout() {
        setLayout(new GridBagLayout());

        JPanel buttons = new JPanel();
        buttons.setLayout(new BorderLayout(0, 10));
        buttons.add(singlePlayerButton, BorderLayout.NORTH);
        buttons.add(optionsButton, BorderLayout.CENTER);
        buttons.add(quitButton, BorderLayout.SOUTH);

        add(buttons);
    }
}
