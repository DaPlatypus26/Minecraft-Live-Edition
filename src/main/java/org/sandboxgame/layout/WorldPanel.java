package org.sandboxgame.layout;

import org.sandboxgame.Main;
import org.sandboxgame.util.world.World;

import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel {
    private JButton world1;
    private JButton world2;
    private JButton world3;

    public static World testWorld = new World("Test World", 150, 50);
    public static World testWorld2 = new World("Test World 2", 150, 50);
    public static World testWorld3 = new World("Test World 2", 150, 50);


    public WorldPanel() {
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        world1 = new JButton("World 1");
        world2 = new JButton("World 2");
        world3 = new JButton("World 3");

        world1.addActionListener(e -> {
            Main.game.start(testWorld);
        });

        world2.addActionListener(e -> {
            Main.game.start(testWorld2);
        });

        world3.addActionListener(e -> {
            Main.game.start(testWorld3);
        });
    }

    private void setupLayout() {
        setLayout(new GridBagLayout());

        JPanel buttons = new JPanel();
        buttons.setLayout(new BorderLayout(0, 10));
        buttons.add(world1, BorderLayout.NORTH);
        buttons.add(world2, BorderLayout.CENTER);
        buttons.add(world3, BorderLayout.SOUTH);

        add(buttons);
    }
}
