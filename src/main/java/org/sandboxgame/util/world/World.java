package org.sandboxgame.util.world;

import org.sandboxgame.objects.Block;
import org.sandboxgame.objects.Material;

public class World {
    private String name;
    private int width;
    private int height;
    private Block[][] overworldGrid;
    private Block[][] netherGrid;
    private Block[][] endGrid;
    private boolean[][] rotationGrid;

    Block[] overworldFiller = new Block[]{
            new Block("Air", 0, 0, Material.AIR),
            new Block("Grass Block", 0, 0, Material.GRASS_BLOCK),
            new Block("Dirt", 0, 0, Material.DIRT),
            new Block("Stone", 0, 0, Material.STONE)
    };

    public World(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;

        createLevel();

        rotationGrid = WorldGen.rotation(width, height);
    }

    public void createLevel() {
        overworldGrid = WorldGen.normal(width, height, overworldFiller);
        netherGrid = WorldGen.cave(width, height);
        endGrid = WorldGen.floatingIsland(width, height);
    }

    public void createLevel(LevelType levelType) {
        switch(levelType) {
            case OVERWORLD:
                overworldGrid = WorldGen.normal(width, height, overworldFiller);
                break;
            case NETHER:
                netherGrid = WorldGen.cave(width, height);
                break;
            case END:
                endGrid = WorldGen.floatingIsland(width, height);
        }
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Block getTile(LevelType levelType, int x, int y) {
        Block block = null;
        switch(levelType) {
            case OVERWORLD:
                block = overworldGrid[x][y];
                break;
            case NETHER:
                block = netherGrid[x][y];
                break;
            case END:
                block = endGrid[x][y];
        }
        return block;
    }

    public Block[][] getOverworldGrid() {
        return overworldGrid;
    }

    public Block[][] getNetherGrid() {
        return netherGrid;
    }

    public Block[][] getEndGrid() {
        return endGrid;
    }

    public boolean[][] getRotationGrid() {
        return rotationGrid;
    }
}
