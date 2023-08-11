package org.sandboxgame.util.world;

import org.sandboxgame.objects.Block;
import org.sandboxgame.util.generator.LineGenerator;

import java.util.Random;

public class WorldGen {
    public static Block[][] normal(int width, int height, Block[] filler) {
        Block[][] levelGrid = new Block[width][height];

        Block air = filler[0]; //new Block("Air", x, y, Material.AIR)
        Block grassBlock = filler[1]; //new Block("Grass Block", x, y, Material.GRASS_BLOCK)
        Block dirt = filler[2]; //new Block("Dirt", x, y, Material.DIRT)
        Block stone = filler[3]; //new Block("Stone", x, y, Material.STONE)

        //fills the whole world with air blocks
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                levelGrid[x][y] = (Block) air.setPosition(x, y);
            }
        }

        //places the grass and dirt blocks
        int[] grassLine = LineGenerator.singleLine(width, height/2, height/2 - 5, 9);
        for(int x = 0; x < width; x++) {
            levelGrid[x][grassLine[x]] = (Block) grassBlock.setPosition(x, grassLine[x]);
            for(int fillY = grassLine[x] + 1; fillY < height; fillY++) {
                levelGrid[x][fillY] = (Block) dirt.setPosition(x, fillY);
            }
        }

        //places the stone blocks
        int[] stoneLine = LineGenerator.singleLine(width, height/2 + 7, height/2 + 2, 7);
        for(int x = 0; x < width; x++) {
            levelGrid[x][stoneLine[x]] = (Block) stone.setPosition(x, stoneLine[x]);
            for(int fillY = stoneLine[x] + 1; fillY < height; fillY++) {
                levelGrid[x][fillY] = (Block) stone.setPosition(x, fillY);
            }
        }

        return levelGrid;
    }

    public static Block[][] flat(int width, int height, Block[] filler) {
        Block[][] levelGrid = new Block[width][height];

        Block air = filler[0]; //new Block("Air", x, y, Material.AIR)
        Block grassBlock = filler[1]; //new Block("Grass Block", x, y, Material.GRASS_BLOCK)
        Block dirt = filler[2]; //new Block("Dirt", x, y, Material.DIRT)
        Block stone = filler[3]; //new Block("Stone", x, y, Material.STONE)

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                levelGrid[x][y] = (Block) air.setPosition(x, y);
            }
        }

        int grassY = height/2 - 5;
        for(int x = 0; x < width; x++) {
            levelGrid[x][grassY] = (Block) grassBlock.setPosition(x, grassY);
            for(int fillY = grassY + 1; fillY < height; fillY++) {
                levelGrid[x][fillY] = (Block) dirt.setPosition(x, fillY);
            }
        }

        int stoneY = height/2;
        for(int x = 0; x < width; x++) {
            levelGrid[x][stoneY] = (Block) stone.setPosition(x, stoneY);
            for(int fillY = stoneY + 1; fillY < height; fillY++) {
                levelGrid[x][fillY] = (Block) stone.setPosition(x, fillY);
            }
        }

        return levelGrid;
    }

    public static Block[][] cave(int width, int height) {
        Block[][] levelGrid = new Block[width][height];

        return levelGrid;
    }

    public static Block[][] floatingIsland(int width, int height) {
        Block[][] levelGrid = new Block[width][height];

        return levelGrid;
    }

    //creates the rotation grid for indicating which blocks should be rotated
    public static boolean[][] rotation(int width, int height) {
        boolean[][] rotationGrid = new boolean[width][height];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Random random = new Random();
                rotationGrid[x][y] = random.nextBoolean();
            }
        }
        return rotationGrid;
    }
}
