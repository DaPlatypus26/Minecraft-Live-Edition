package org.live_edition.objects;

public class GameObject {
    String name;

    public GameObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public GameObject setName(String name) {
        this.name = name;
        return this;
    }
}
