package org.live_edition.world;

import org.live_edition.objects.Block;
import org.live_edition.objects.Material;
import org.live_edition.util.ImageTool;

import java.awt.*;

public class World {
    private int maxHeight;
    private int maxWidth;
    private int seaLevel;

    private int time;
    private int startTime;
    private Color skyColor;
    private String skyPalette;

    private Block[][] blockGrid;
    private Block[][] backgroundBlockGrid;
    private int[][] lightLevelGrid;

    Block[] overworldFiller;

    public World(int maxWidth, int maxHeight, int seaLevel) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.seaLevel = seaLevel;

        startTime = 3600;
        skyPalette = "src/main/resources/assets/textures/sky/sky_palette.png";

        overworldFiller = new Block[]{
                new Block("Air", Material.AIR),
                new Block("Grass Block", Material.GRASS_BLOCK),
                new Block("Dirt", Material.DIRT),
                new Block("Stone", Material.STONE)
        };
    }

    public void update() {
        dayNightCycle();
    }

    public void loadFromFile() {
        //if already created world is loaded
    }

    public void generateLevel() {
        blockGrid = WorldGen.normal(maxWidth, maxHeight, seaLevel, overworldFiller);
        update();
    }

    public void generateLevel(LevelType type) {

    }

    public void dayNightCycle() {
        if(getSkyColor() == null) {
            setTime(startTime);
            setSkyColor(ImageTool.getPixelColor(skyPalette, startTime, 0));
        } else {
            setTime(getTime() + 1);
            if(getTime() > ImageTool.getImageWidth(skyPalette)) {
                setTime(0);
            }
            System.out.println(getTime());
            System.out.println(getSkyColor().toString());
            setSkyColor(ImageTool.getPixelColor(skyPalette, getTime(), 0));
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

    public Block[][] getBlockGrid() {
        return blockGrid;
    }

    public void setBlockGrid(Block[][] blockGrid) {
        this.blockGrid = blockGrid;
    }

    public Block[][] getBackgroundBlockGrid() {
        return backgroundBlockGrid;
    }

    public void setBackgroundBlockGrid(Block[][] backgroundBlockGrid) {
        this.backgroundBlockGrid = backgroundBlockGrid;
    }

    public int[][] getLightLevelGrid() {
        return lightLevelGrid;
    }

    public void setLightLevelGrid(int[][] lightLevelGrid) {
        this.lightLevelGrid = lightLevelGrid;
    }
}
