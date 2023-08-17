package org.live_edition.components;

import org.live_edition.Main;
import org.live_edition.objects.Block;
import org.live_edition.objects.Material;
import org.live_edition.util.ImageTool;
import org.live_edition.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Thread thread;
    private int fps;

    private World world;
    private int blockSize;

    private Map<Material, Image> textureCache = new HashMap<>();

    public GamePanel() {
        thread = new Thread(this);
        fps = 60;

        world = Main.world;
        blockSize = 20;

        Material[] materials = Material.values();
        for(Material material : materials) {
            textureCache.put(material, ImageTool.getImageFromResources(material.getTexture()));
        }

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
        Graphics2D g2 = (Graphics2D) g;

        System.out.println(LocalTime.now());

        Block[][] blockGrid = world.getBlockGrid();
        Block[][] wallgrid = world.getWallGrid();

        setBackground(world.getSkyColor());

        int blocksPerFramesWidth = this.getWidth() / blockSize + 1;
        int blocksPerFramesHeight = this.getHeight() / blockSize + 1;

        // Renders blockGrid and wallGrid
        for(int x = 0; x < blocksPerFramesWidth; x++) {
            for(int y = 0; y < blocksPerFramesHeight; y++) {
                if(x < blockGrid.length && y < blockGrid[0].length) {
                    Block block = blockGrid[x][y];
                    Image blockTexture = textureCache.get(block.getMaterial());

                    Block wall = wallgrid[x][y];
                    Image wallTexture = textureCache.get(wall.getMaterial());
                    // Makes non-transparent wall blocks darker
                    if(!wall.getMaterial().isTransparent()) {
                        wallTexture = ImageTool.darkerImage(wallTexture, 0.5);
                    }

                    int xPos = x * blockSize;
                    int yPos = y * blockSize;

                    // First render wall blocks
                    g2.drawImage(wallTexture, xPos, yPos, blockSize, blockSize, this);
                    g2.drawImage(blockTexture, xPos, yPos, blockSize, blockSize, this);
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
                    //System.out.println(LocalTime.now());
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
