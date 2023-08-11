package org.sandboxgame.layout;

import org.sandboxgame.Main;
import org.sandboxgame.objects.Block;
import org.sandboxgame.util.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    public World world;
    public int tileSize = 20;

    public GamePanel() {
        addKeyListener(this);
        setBackground(Color.decode("#C0FCFA"));
        setLayout(null);
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
        setPreferredSize(new Dimension(world.getWidth() * tileSize, world.getHeight() * tileSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Block[][] levelGrid = world.getOverworldGrid();

        for(int y = 0; y < levelGrid[0].length; y++) {
            for(int x = 0; x < levelGrid.length; x++) {
                Block block = levelGrid[x][y];
                String texture = block.getMaterial().getTexture();

                if(!texture.isEmpty()) {
                    ImageIcon imageIcon = new ImageIcon(texture);
                    Image image = imageIcon.getImage();

                    //rotate Blocks (Looks more natural)
                    /*if(block.getMaterial().isRotateHorizontal() && world.getRotationGrid()[x][y]) {
                        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2d = bufferedImage.createGraphics();
                        g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), image.getWidth(null), 0, 0, image.getHeight(null), null);
                        g2d.dispose();

                        image = bufferedImage;
                    }*/

                    int xPos = x * tileSize;
                    int yPos = y * tileSize;

                    g.drawImage(image, xPos, yPos, tileSize, tileSize, this);
                }
            }
        }
    }

    public void update() {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Main.game.stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
