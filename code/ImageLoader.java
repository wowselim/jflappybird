import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageLoader {
  public static BufferedImage loadImage(String path) {
    try {
      return ImageIO.read(ImageLoader.class.getResource(path));
    } catch(Exception e) {
      System.out.println("Error loading image.");
    }
    return null;
  }
}