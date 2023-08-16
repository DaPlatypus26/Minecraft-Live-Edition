package org.live_edition.world.biomes;

import org.live_edition.objects.Block;
import org.live_edition.objects.Material;

public enum BiomeType {
    // Green Biomes
    PLAINS(new Block("Grass Block", Material.GRASS_BLOCK),
            new Block("Dirt", Material.DIRT),
            new Block("Stone", Material.STONE)
    ),
    FOREST(new Block("Grass Block", Material.GRASS_BLOCK),
            new Block("Dirt", Material.DIRT),
            new Block("Stone", Material.STONE)
    ),
    DESERT(new Block("Sand", Material.SAND),
            new Block("Sand", Material.SAND),
            new Block("Stone", Material.STONE)
    );

    Block topLayer;
    Block secondLayer;
    Block stoneLayer;

    BiomeType(Block topLayer, Block secondLayer, Block stoneLayer) {
        this.topLayer = topLayer;
        this.secondLayer = secondLayer;
        this.stoneLayer = stoneLayer;
    }

    public Block getTopLayer() {
        return topLayer;
    }

    public Block getSecondLayer() {
        return secondLayer;
    }

    public Block getStoneLayer() {
        return stoneLayer;
    }
}
