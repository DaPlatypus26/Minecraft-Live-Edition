package org.live_edition.util.generator;

import java.util.Random;

public class LineGenerator {
    public static int[] singleLine(int width, int max, int min, int length) {
        int[] result = new int[width];
        boolean[] resultCeck = new boolean[width];
        Random random = new Random();

        result[width - 1] = random.nextInt(max - min) + min;
        resultCeck[width - 1] = true;

        for(int i = 0; i < width; i += length) {
            int value = random.nextInt(max - min) + min;
            result[i] = value;
            resultCeck[i] = true;
        }

        for(int i = 0; i < width; i++) {
            if(!resultCeck[i]) {
                int val1 = result[i - 1];
                int val2 = 1;
                for(int j = i; !resultCeck[j]; j++) {
                    val2 = result[j + 1];
                }
                int value;
                if(val1 > val2) {
                    value = random.nextInt(val1 - val2) + val2;
                } else if(val2 > val1) {
                    value = random.nextInt(val2 - val1) + val1;
                } else {
                    value = random.nextInt(1) + val1;
                }
                result[i] = value;
                resultCeck[i] = true;
            }
        }

        return result;
    }

    public static boolean[][] caveLine(int width, int max, int min, int height, int length) {
        boolean[][] result = new boolean[width][width];

        int[] line1 = singleLine(width, max, min, length);
        int[] line2 = singleLine(width, max + height, min + height, length);

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < width; y++) {
                if(line1[x] == y || line2[x] == y) {
                    result[x][y] = true;
                }
            }
        }

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < width; y++) {
                if(result[x][y] && !result[x][y + 1] && y != line2[x]) {
                    result[x][y + 1] = true;
                }
            }
        }

        return result;
    }
}
