package org.live_edition.world;

import org.live_edition.objects.Block;
import org.live_edition.objects.Material;
import org.live_edition.util.generator.LineGenerator;
import org.live_edition.world.biomes.BiomeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGen {
    public static List<Block[][]> normal(int width, int height, int seaLevel, BiomeType[][] biomeGrid) {
        List<Block[][]> layers = new ArrayList<>();
        Block[][] blockGrid = new Block[width][height];
        Block[][] wallGrid = new Block[width][height];

        Block air = new Block("Air", Material.AIR);

        // Fills the whole world with air blocks
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                blockGrid[x][y] = air;
                wallGrid[x][y] = air;
            }
        }

        int[] topLayer = LineGenerator.singleLine(width, seaLevel, seaLevel - 5, 9);
        int[] stoneLayer = LineGenerator.singleLine(width, seaLevel + 7, seaLevel + 2, 7);

        // Places the grass and dirt blocks
        for(int x = 0; x < width; x++) {
            blockGrid[x][topLayer[x]] = biomeGrid[x][topLayer[x]].getTopLayer();
            wallGrid[x][topLayer[x]] = biomeGrid[x][topLayer[x]].getTopLayer();
            for(int fillY = topLayer[x] + 1; fillY < stoneLayer[x]; fillY++) {
                blockGrid[x][fillY] = biomeGrid[x][fillY].getSecondLayer();
                wallGrid[x][fillY] = biomeGrid[x][fillY].getSecondLayer();
            }
        }

        // Places the stone blocks
        for(int x = 0; x < width; x++) {
            blockGrid[x][stoneLayer[x]] = biomeGrid[x][stoneLayer[x]].getStoneLayer();
            wallGrid[x][stoneLayer[x]] = biomeGrid[x][stoneLayer[x]].getStoneLayer();
            for(int fillY = stoneLayer[x] + 1; fillY < height; fillY++) {
                blockGrid[x][fillY] = biomeGrid[x][fillY].getStoneLayer();
                wallGrid[x][fillY] = biomeGrid[x][fillY].getStoneLayer();
            }
        }

        boolean cave[][] = LineGenerator.caveLine(width, 35, 30, 7, 6);
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(cave[x][y]) {
                    blockGrid[x][y] = air;
                }
            }
        }

        layers.add(blockGrid);
        layers.add(wallGrid);
        return layers;
    }

    // Needs to be reworked
    public static List<Block[][]> flat(int width, int height, int seaLevel, Block[] filler) {
        List<Block[][]> layers = new ArrayList<>();
        Block[][] blockGrid = new Block[width][height];
        Block[][] wallGrid = new Block[width][height];

        Block air = filler[0]; //new Block("Air", x, y, Material.AIR)
        Block grassBlock = filler[1]; //new Block("Grass Block", x, y, Material.GRASS_BLOCK)
        Block dirt = filler[2]; //new Block("Dirt", x, y, Material.DIRT)
        Block stone = filler[3]; //new Block("Stone", x, y, Material.STONE)

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                blockGrid[x][y] = air;
                wallGrid[x][y] = air;
            }
        }

        int grassY = seaLevel - 5;
        for(int x = 0; x < width; x++) {
            blockGrid[x][grassY] = grassBlock;
            wallGrid[x][grassY] = grassBlock;
            for(int fillY = grassY + 1; fillY < height; fillY++) {
                blockGrid[x][fillY] = dirt;
                wallGrid[x][fillY] = dirt;
            }
        }

        int stoneY = seaLevel;
        for(int x = 0; x < width; x++) {
            blockGrid[x][stoneY] = stone;
            wallGrid[x][stoneY] = stone;
            for(int fillY = stoneY + 1; fillY < height; fillY++) {
                blockGrid[x][fillY] = stone;
                wallGrid[x][fillY] = stone;
            }
        }

        layers.add(blockGrid);
        layers.add(wallGrid);
        return layers;
    }

    public static List<Block[][]> cave(int width, int height) {
        List<Block[][]> layers = new ArrayList<>();
        Block[][] blockGrid = new Block[width][height];
        Block[][] wallGrid = new Block[width][height];

        layers.add(blockGrid);
        layers.add(wallGrid);
        return layers;
    }

    public static List<Block[][]> floatingIsland(int width, int height) {
        List<Block[][]> layers = new ArrayList<>();
        Block[][] blockGrid = new Block[width][height];
        Block[][] wallGrid = new Block[width][height];

        layers.add(blockGrid);
        layers.add(wallGrid);
        return layers;
    }

    // Creates the rotation grid for indicating which blocks should be rotated
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
