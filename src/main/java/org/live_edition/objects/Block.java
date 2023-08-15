package org.live_edition.objects;

public class Block extends GameObject {
    Material material;

    public Block(String name, Material material) {
        super(name);
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public Block setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
