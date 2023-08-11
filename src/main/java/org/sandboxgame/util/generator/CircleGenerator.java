package org.sandboxgame.util.generator;

public class CircleGenerator {
    public static boolean[][] generate(int radius) {
        int d = radius * 2;
        boolean[][] result = new boolean[d][d];

        for(int x = 0; x < d; x++) {
            for(int y = 0; y < d; y++) {
                int xOffset = x - radius;
                int yOffset = y - radius;
                double distance = Math.sqrt(xOffset * xOffset + yOffset * yOffset);

                result[x][y] = distance <= radius;
            }
        }

        return result;
    }

    public static boolean[][] caves(int width, int height, int radius, int chance, int distance) {
        boolean[][] result = new boolean[width][height];
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {

            }
        }
        return result;
    }
}
