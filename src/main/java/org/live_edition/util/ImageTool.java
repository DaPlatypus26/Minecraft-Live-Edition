package org.live_edition.util;

import org.live_edition.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.IOException;
import java.io.InputStream;

public class ImageTool {
    public static Image getImageFromResources(String path) {
        try {
            InputStream stream = Main.class.getClassLoader().getResourceAsStream(path);
            return ImageIO.read(stream);
        } catch(IOException e) {
            throw new RuntimeException("Error loading image from resources: " + path, e);
        }
    }

    public static Color getPixelColor(Image image, int x, int y) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        if(x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Invalid coordinates.");
        }

        int[] pixels = new int[1];
        PixelGrabber pixelGrabber = new PixelGrabber(image, x, y, 1, 1, pixels, 0, 1);

        try {
            if(pixelGrabber.grabPixels()) {
                return new Color(pixels[0]);
            } else {
                throw new RuntimeException("Pixel grab failed.");
            }
        } catch(InterruptedException e) {
            throw new RuntimeException("Pixel grab interrupted.");
        }
    }

    public static Color[] getRowColors(Image image, int rowIndex) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        if(rowIndex < 0 || rowIndex >= height) {
            throw new IllegalArgumentException("Invalid row index.");
        }

        int[] pixels = new int[width];
        PixelGrabber pixelGrabber = new PixelGrabber(image, 0, rowIndex, width, 1, pixels, 0, width);

        try {
            if(pixelGrabber.grabPixels()) {
                Color[] colors = new Color[width];
                for(int i = 0; i < width; i++) {
                    colors[i] = new Color(pixels[i]);
                }
                return colors;
            } else {
                throw new RuntimeException("Pixel grab failed.");
            }
        } catch(InterruptedException e) {
            throw new RuntimeException("Pixel grab interrupted.");
        }
    }

    public static Color[][] getImageColors(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        int[] pixels = new int[width * height];
        PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);

        try {
            if(pixelGrabber.grabPixels()) {
                Color[][] colors = new Color[height][width];
                for(int y = 0; y < height; y++) {
                    for(int x = 0; x < width; x++) {
                        colors[y][x] = new Color(pixels[y * width + x]);
                    }
                }
                return colors;
            } else {
                throw new RuntimeException("Pixel grab failed.");
            }
        } catch(InterruptedException e) {
            throw new RuntimeException("Pixel grab interrupted.");
        }
    }

    public static Image darkerImage(Image originalImage, double darknessFactor) {
        BufferedImage bufferedOriginalImage = toBufferedImage(originalImage);
        int width = bufferedOriginalImage.getWidth();
        int height = bufferedOriginalImage.getHeight();

        BufferedImage darkerImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color originalColor = new Color(bufferedOriginalImage.getRGB(x, y));

                int red = originalColor.getRed() - (int) (originalColor.getRed() * darknessFactor);
                int green = originalColor.getGreen() - (int) (originalColor.getGreen() * darknessFactor);
                int blue = originalColor.getBlue() - (int) (originalColor.getBlue() * darknessFactor);

                Color newColor = new Color(red, green, blue, originalColor.getAlpha());
                darkerImage.setRGB(x, y, newColor.getRGB());
            }
        }

        return darkerImage;
    }

    public static Image fromBufferedImage(BufferedImage bufferedImage) {
        return bufferedImage.getScaledInstance(bufferedImage.getWidth(), bufferedImage.getHeight(), Image.SCALE_DEFAULT);
    }

    public static BufferedImage toBufferedImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Creates a graphics context and draws the image onto the BufferedImage
        bufferedImage.getGraphics().drawImage(image, 0, 0, null);

        return bufferedImage;
    }
}
