package org.live_edition.world.biomes;

public class BiomesGenerator {
    public static BiomeType[][] singleBiome(int width, int height, BiomeType biome) {
        BiomeType[][] biomeGrid = new BiomeType[width][height];

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                biomeGrid[x][y] = biome;
            }
        }

        return biomeGrid;
    }

    public static BiomeType[][] lineBiomes(int width, int height, BiomeType[] biomes) {
        BiomeType[][] biomeGrid = new BiomeType[width][height];

        int biomeCount = biomes.length;
        int biomeLength = width / biomeCount;
        int index = 0;

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                biomeGrid[x][y] = biomes[0];
            }
        }



        return biomeGrid;
    }

    public static BiomeType[][] fullBiomes(int width, int height, BiomeType[] biomes) {
        BiomeType[][] biomeGrid = new BiomeType[width][height];

        return biomeGrid;
    }
}
