package org.live_edition.objects;

public enum Material {
    AIR("air", false, false, "src/main/resources/assets/textures/blocks/air.png", false,false, true),
    DIRT("dirt", true, false, "src/main/resources/assets/textures/blocks/dirt.png", true, false, false),
    GRASS_BLOCK("grass_block", true, false, "src/main/resources/assets/textures/blocks/grass_block.png", true, false, false),
    STONE("stone", true, false, "src/main/resources/assets/textures/blocks/stone.png", true, false, false),
    SAND("sand", true, true, "", true, false, false),
    GLASS("glass", true, false, "", false, false, false);

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

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public boolean isGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public boolean isRotateHorizontal() {
        return rotateHorizontal;
    }

    public void setRotateHorizontal(boolean rotateHorizontal) {
        this.rotateHorizontal = rotateHorizontal;
    }

    public boolean isGlow() {
        return glow;
    }

    public void setGlow(boolean glow) {
        this.glow = glow;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }
}
