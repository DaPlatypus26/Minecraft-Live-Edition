package org.live_edition.world;

import org.live_edition.objects.Block;
import org.live_edition.util.generator.LineGenerator;

import java.util.Random;

public class WorldGen {
    public static Block[][] normal(int width, int height, int seaLevel, Block[] filler) {
        Block[][] blockGrid = new Block[width][height];

        Block air = filler[0]; //new Block("Air", x, y, Material.AIR)
        Block grassBlock = filler[1]; //new Block("Grass Block", x, y, Material.GRASS_BLOCK)
        Block dirt = filler[2]; //new Block("Dirt", x, y, Material.DIRT)
        Block stone = filler[3]; //new Block("Stone", x, y, Material.STONE)

        //fills the whole world with air blocks
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                blockGrid[x][y] = air;
            }
        }

        //places the grass and dirt blocks
        int[] grassLine = LineGenerator.singleLine(width, seaLevel, seaLevel - 5, 9);
        for(int x = 0; x < width; x++) {
            blockGrid[x][grassLine[x]] = grassBlock;
            for(int fillY = grassLine[x] + 1; fillY < height; fillY++) {
                blockGrid[x][fillY] = dirt;
            }
        }

        //places the stone blocks
        int[] stoneLine = LineGenerator.singleLine(width, seaLevel + 7, seaLevel + 2, 7);
        for(int x = 0; x < width; x++) {
            blockGrid[x][stoneLine[x]] = stone;
            for(int fillY = stoneLine[x] + 1; fillY < height; fillY++) {
                blockGrid[x][fillY] = stone;
            }
        }

        return blockGrid;
    }

    public static Block[][] flat(int width, int height, int seaLevel, Block[] filler) {
        Block[][] blockGrid = new Block[width][height];

        Block air = filler[0]; //new Block("Air", x, y, Material.AIR)
        Block grassBlock = filler[1]; //new Block("Grass Block", x, y, Material.GRASS_BLOCK)
        Block dirt = filler[2]; //new Block("Dirt", x, y, Material.DIRT)
        Block stone = filler[3]; //new Block("Stone", x, y, Material.STONE)

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                blockGrid[x][y] = air;
            }
        }

        int grassY = seaLevel - 5;
        for(int x = 0; x < width; x++) {
            blockGrid[x][grassY] = grassBlock;
            for(int fillY = grassY + 1; fillY < height; fillY++) {
                blockGrid[x][fillY] = dirt;
            }
        }

        int stoneY = seaLevel;
        for(int x = 0; x < width; x++) {
            blockGrid[x][stoneY] = stone;
            for(int fillY = stoneY + 1; fillY < height; fillY++) {
                blockGrid[x][fillY] = stone;
            }
        }

        return blockGrid;
    }

    public static Block[][] cave(int width, int height) {
        Block[][] blockGrid = new Block[width][height];

        return blockGrid;
    }

    public static Block[][] floatingIsland(int width, int height) {
        Block[][] blockGrid = new Block[width][height];

        return blockGrid;
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
