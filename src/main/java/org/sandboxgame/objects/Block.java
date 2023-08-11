package org.sandboxgame.objects;

public class Block extends GameObject {
    Material material;

    public Block(String name, int x, int y, Material material) {
        super(name, x, y);
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
