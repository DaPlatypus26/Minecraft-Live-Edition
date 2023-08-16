package org.live_edition.world;

import org.live_edition.objects.Block;
import org.live_edition.objects.Material;
import org.live_edition.util.ImageTool;
import org.live_edition.world.biomes.BiomeType;
import org.live_edition.world.biomes.BiomesGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class World {
    private int maxHeight;
    private int maxWidth;
    private int seaLevel;

    private int time;
    private int startTime;

    private Color skyColor;
    private String skyPalettePath;
    private File skyPaletteFile;
    private BufferedImage skyPalette;
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
        skyPalettePath = "src/main/resources/assets/textures/sky/sky_palette.png";
        skyPaletteFile = new File(skyPalettePath);
        try {
            skyPalette = ImageIO.read(skyPaletteFile);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        skyPaletteArray = new Color[skyPalette.getWidth()];

        for(int i = 0; i < skyPaletteArray.length; i++) {
            skyPaletteArray[i] = ImageTool.getPixelColor(skyPalette, i, 0);
        }
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

        blockGrid[0][0] = new Block("Stone", Material.STONE);

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

    public String getSkyPalettePath() {
        return skyPalettePath;
    }

    public void setSkyPalettePath(String skyPalettePath) {
        this.skyPalettePath = skyPalettePath;
    }

    public File getSkyPaletteFile() {
        return skyPaletteFile;
    }

    public void setSkyPaletteFile(File skyPaletteFile) {
        this.skyPaletteFile = skyPaletteFile;
    }

    public BufferedImage getSkyPalette() {
        return skyPalette;
    }

    public void setSkyPalette(BufferedImage skyPalette) {
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
