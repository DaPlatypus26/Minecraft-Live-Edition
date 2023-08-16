package org.live_edition.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageTool {
    public static Color getPixelColor(BufferedImage bufferedImage, int x, int y) {
        try {
            if(x >= 0 && x < bufferedImage.getWidth() && y >= 0 && y < bufferedImage.getHeight()) {
                return new Color(bufferedImage.getRGB(x, y));
            } else {
                throw new IllegalArgumentException();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage darkerImage(BufferedImage originalImage, double darknessFactor) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage darkerImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color originalColor = new Color(originalImage.getRGB(x, y));

                int red = originalColor.getRed() - (int) (originalColor.getRed() * darknessFactor);
                int green = originalColor.getGreen() - (int) (originalColor.getGreen() * darknessFactor);
                int blue = originalColor.getBlue() - (int) (originalColor.getBlue() * darknessFactor);

                Color newColor = new Color(red, green, blue, originalColor.getAlpha());
                darkerImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return darkerImage;
    }

    public static Image convertBufferedImageToImage(BufferedImage bufferedImage) {
        return bufferedImage.getScaledInstance(bufferedImage.getWidth(), bufferedImage.getHeight(), Image.SCALE_DEFAULT);
    }

    public static BufferedImage convertImageToBufferedImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Creates a graphics context and draws the image onto the BufferedImage
        bufferedImage.getGraphics().drawImage(image, 0, 0, null);

        return bufferedImage;
    }
}
