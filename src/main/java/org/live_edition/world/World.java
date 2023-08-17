package org.live_edition.world;

import org.live_edition.objects.Block;
import org.live_edition.util.ImageTool;
import org.live_edition.world.biomes.BiomeType;
import org.live_edition.world.biomes.BiomesGenerator;

import java.awt.*;
import java.util.List;

public class World {
    private int maxHeight;
    private int maxWidth;
    private int seaLevel;

    private int time;
    private int startTime;

    private Color skyColor;
    private Image skyPalette;
    private Color[] skyPaletteArray;

    private Block[][] blockGrid;
    private Block[][] wallGrid;
    private int[][] lightLevelGrid;
    private BiomeType[][] biomeGrid;
    BiomeType[] overworldBiomeFiller;

    public World(int maxWidth, int maxHeight, int seaLevel) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.seaLevel = seaLevel;

        startTime = 1500;

        skyPalette = ImageTool.getImageFromResources("assets/textures/sky/sky_palette.png");
        skyPaletteArray = ImageTool.getRowColors(skyPalette, 0);
    }

    public void update() {
        dayNightCycle();
    }

    public void loadFromFile() {
        // If already created world is loaded from world file
    }

    public void generateLevel() {
        biomeGrid = BiomesGenerator.singleBiome(maxWidth, maxHeight, BiomeType.DESERT);

        List<Block[][]> layers = WorldGen.normal(maxWidth, maxHeight, seaLevel, biomeGrid);
        blockGrid = layers.get(0);
        wallGrid = layers.get(1);

        update();
    }

    public void generateLevel(LevelType type) {

    }

    public void dayNightCycle() {
        if(getSkyColor() == null) {
            setTime(startTime);
            setSkyColor(skyPaletteArray[startTime]);
        } else {
            setTime(getTime() + 1);
            if(getTime() == skyPaletteArray.length) {
                setTime(0);
            }
            setSkyColor(skyPaletteArray[time]);
        }
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(int seaLevel) {
        this.seaLevel = seaLevel;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Color getSkyColor() {
        return skyColor;
    }

    public void setSkyColor(Color skyColor) {
        this.skyColor = skyColor;
    }

    public Image getSkyPalette() {
        return skyPalette;
    }

    public void setSkyPalette(Image skyPalette) {
        this.skyPalette = skyPalette;
    }

    public Color[] getSkyPaletteArray() {
        return skyPaletteArray;
    }

    public void setSkyPaletteArray(Color[] skyPaletteArray) {
        this.skyPaletteArray = skyPaletteArray;
    }

    public Block[][] getBlockGrid() {
        return blockGrid;
    }

    public void setBlockGrid(Block[][] blockGrid) {
        this.blockGrid = blockGrid;
    }

    public Block getBlockAt(int x, int y) {
        return blockGrid[x][y];
    }

    public void setBlockAt(int x, int y, Block block) {
        blockGrid[x][y] = block;
    }

    public Block getWallBlockAt(int x, int y) {
        return wallGrid[x][y];
    }

    public void setWallBlockAt(int x, int y, Block block) {
        wallGrid[x][y] = block;
    }

    public Block[][] getWallGrid() {
        return wallGrid;
    }

    public void setWallGrid(Block[][] wallGrid) {
        this.wallGrid = wallGrid;
    }

    public int[][] getLightLevelGrid() {
        return lightLevelGrid;
    }

    public void setLightLevelGrid(int[][] lightLevelGrid) {
        this.lightLevelGrid = lightLevelGrid;
    }

    public BiomeType[][] getBiomeGrid() {
        return biomeGrid;
    }

    public void setBiomeGrid(BiomeType[][] biomeGrid) {
        this.biomeGrid = biomeGrid;
    }
}
