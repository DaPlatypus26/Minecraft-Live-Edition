package org.sandboxgame;

import org.sandboxgame.layout.GamePanel;
import org.sandboxgame.layout.MenuPanel;
import org.sandboxgame.layout.WorldPanel;

import javax.swing.*;

public class Main {
    public static Game game;

    public static JFrame frame;
    public static MenuPanel menuPanel;
    public static WorldPanel worldPanel;
    public static GamePanel gamePanel;

    public static void main(String[] args) {
        game = new Game();

        menuPanel = new MenuPanel();
        worldPanel = new WorldPanel();
        gamePanel = new GamePanel();

        createFrame();
    }

    //Method for changing panels on the frame
    public static void changePanel(JPanel panel) {
        int width = frame.getWidth();
        int height = frame.getHeight();
        frame.setContentPane(panel);
        frame.getContentPane().setFocusable(true);
        frame.getContentPane().requestFocusInWindow();
        frame.pack();
        frame.setSize(width, height);
    }

    //Method for creating the frame
    public static void createFrame() {
        frame = new JFrame();
        frame.setTitle("Minecraft Live Edition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(menuPanel);

        frame.setFocusable(true);
        frame.pack();
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
