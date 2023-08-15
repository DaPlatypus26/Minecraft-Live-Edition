package org.live_edition.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageTool {
    public static int getImageWidth(String imagePath) {
        try {
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            return image.getWidth();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getImageHeight(String imagePath) {
        try {
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            return image.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Color getPixelColor(String imagePath, int x, int y) {
        try {
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);

            if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
                return new Color(image.getRGB(x, y));
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
