package co.selim.jflappybird.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageLoader {
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageLoader.class.getResource('/' + path));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}