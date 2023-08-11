package org.sandboxgame;

import org.sandboxgame.util.world.World;

public class Game implements Runnable {
    private static int FPS = 60;
    private static Thread thread;

    //starts the thread
    public void start(World world) {
        thread = new Thread(this);
        thread.start();

        Main.gamePanel.setWorld(world);

        Main.changePanel(Main.gamePanel);
    }

    //tick update
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /FPS;
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
                Main.gamePanel.update();
                delta--;
            }

            if(timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    //stops the thread
    public void stop() {
        Main.changePanel(Main.menuPanel);
        thread.stop();
    }
}
