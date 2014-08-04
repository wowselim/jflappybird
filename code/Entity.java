import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface Entity {
  public void update();
  public void render(Graphics2D g);
  public boolean collides(Entity other);
  public Rectangle[] getHitbox();
  public boolean notOnScreen();
}