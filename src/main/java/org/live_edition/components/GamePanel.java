package org.live_edition.components;

import org.live_edition.Main;
import org.live_edition.objects.Block;
import org.live_edition.util.ImageTool;
import org.live_edition.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Thread thread;
    private int fps;

    private World world;
    private int blockSize;

    public GamePanel() {
        thread = new Thread(this);
        fps = 60;

        world = Main.world;
        blockSize = 20;

        addKeyListener(this);
        setLayout(null);

        start();
    }

    public void update() {
        world.update();
        repaint();
    }

    public void start() {
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Block[][] blockGrid = world.getBlockGrid();
        Block[][] wallgrid = world.getWallGrid();

        setBackground(world.getSkyColor());

        // Renders blockGrid and wallGrid
        for(int y = 0; y < blockGrid[0].length; y++) {
            for(int x = 0; x < blockGrid.length; x++) {
                Block block = blockGrid[x][y];
                String blockTexture = block.getMaterial().getTexture();

                Block wall = wallgrid[x][y];
                String wallTexture = wall.getMaterial().getTexture();

                if(!blockTexture.isEmpty()) {
                    ImageIcon blockImageIcon = new ImageIcon(blockTexture);
                    Image blockImage = blockImageIcon.getImage();

                    ImageIcon wallImageIcon = new ImageIcon(wallTexture);
                    BufferedImage wallBufferedImage = ImageTool.convertImageToBufferedImage(wallImageIcon.getImage());
                    // Makes non-transparent wall blocks darker
                    if(!wall.getMaterial().isTransparent()) {
                        wallBufferedImage = ImageTool.darkerImage(wallBufferedImage, 0.5);
                    }
                    Image wallImage = wallBufferedImage;

                    int xPos = x * blockSize;
                    int yPos = y * blockSize;

                    // First render wall blocks
                    g.drawImage(wallImage, xPos, yPos, blockSize, blockSize, this);
                    g.drawImage(blockImage, xPos, yPos, blockSize, blockSize, this);
                }
            }
        }
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        int secTest = 0;

        while(thread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();

                // Seconds counter test
                if(secTest%fps == 0) {
                    System.out.println(secTest/fps);
                }
                secTest++;

                delta--;
            }

            if(timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    public void stop() {
        thread.stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
