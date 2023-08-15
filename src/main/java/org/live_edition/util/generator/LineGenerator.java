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

    public static int[][] caveLine(int width, int max1, int min1, int max2, int min2, int length, boolean filled) {
        int[][] result = new int[width][width];


        return result;
    }
}
