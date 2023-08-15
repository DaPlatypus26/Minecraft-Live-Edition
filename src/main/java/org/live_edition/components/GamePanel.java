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

        //blockGrid render
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
                    if(!wall.getMaterial().isTransparent()) {
                        wallBufferedImage = ImageTool.darkerImage(wallBufferedImage, 0.5);
                    }
                    Image wallImage = wallBufferedImage;

                    //rotate Blocks (Looks more natural)
                    /*if(block.getMaterial().isRotateHorizontal() && world.getRotationGrid()[x][y]) {
                        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2d = bufferedImage.createGraphics();
                        g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), image.getWidth(null), 0, 0, image.getHeight(null), null);
                        g2d.dispose();

                        image = bufferedImage;
                    }*/

                    int xPos = x * blockSize;
                    int yPos = y * blockSize;

                    //first render wall blocks
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

        while(thread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
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
