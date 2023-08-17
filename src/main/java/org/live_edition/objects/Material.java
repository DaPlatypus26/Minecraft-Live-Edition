package org.live_edition.objects;

public enum Material {
    AIR("air", false, false, "assets/textures/block/air.png", false,false, true),
    DIRT("dirt", true, false, "assets/textures/block/dirt.png", true, false, false),
    GRASS_BLOCK("grass_block", true, false, "assets/textures/block/grass_block.png", true, false, false),
    STONE("stone", true, false, "assets/textures/block/stone.png", true, false, false),
    SAND("sand", true, true, "assets/textures/block/sand.png", true, false, false),
    GLASS("glass", true, false, "assets/textures/block/glass.png", false, false, false);

    String id;
    boolean solid;
    boolean gravity;
    String texture;
    boolean rotateHorizontal;
    boolean glow;
    boolean transparent;

    Material(String id, boolean solid, boolean gravity, String texture, boolean rotateHorizontal, boolean glow, boolean transparent) {
        this.id = id;
        this.solid = solid;
        this.gravity = gravity;
        this.texture = texture;
        this.rotateHorizontal = rotateHorizontal;
        this.glow = glow;
        this.transparent = transparent;
    }

    public String getId() {
        return id;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isGravity() {
        return gravity;
    }

    public String getTexture() {
        return texture;
    }

    public boolean isRotateHorizontal() {
        return rotateHorizontal;
    }

    public boolean isGlow() {
        return glow;
    }

    public boolean isTransparent() {
        return transparent;
    }
}
