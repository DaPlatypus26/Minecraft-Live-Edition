package org.live_edition.components;

import org.live_edition.Main;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        setTitle(Main.name + " | " + Main.version);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setContentPane(new GamePanel());
        getContentPane().setFocusable(true);
        getContentPane().requestFocusInWindow();
        pack();
        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
