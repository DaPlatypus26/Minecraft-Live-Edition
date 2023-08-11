package org.sandboxgame.objects;

public class GameObject {
    String name;
    int x;
    int y;

    public GameObject(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public GameObject setName(String name) {
        this.name = name;
        return this;
    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public GameObject setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public int getX() {
        return x;
    }

    public GameObject setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public GameObject setY(int y) {
        this.y = y;
        return this;
    }
}
