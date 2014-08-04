import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Pipe implements Entity {
  public int x, y;
  public boolean top, passedBird;
  public int width = 25;
  public int height = 20;
  private int upperEdge;
  private int gap = 50;
  
  public Pipe(int upperEdge) {
    x = GamePanel.WIDTH;
    this.upperEdge = upperEdge;
  }
  
  public void update() {
    x--;  
  }
  
  public void render(Graphics2D g) {
    if (notOnScreen()) {
      return;
    }
    g.setColor(new Color(0x38cf3a));
    g.fillRect(x - 2, upperEdge - 35, width + 4, 5);
    g.fillRect(x, -30, width, GamePanel.HEIGHT - (GamePanel.HEIGHT - upperEdge));
    g.fillRect(x - 2, upperEdge + gap - 5, width + 4, 5);
    g.fillRect(x, upperEdge + gap, width, GamePanel.HEIGHT);
  }
  
  public boolean collides(Entity other) {
    return x < -30;
  }
  
  public Rectangle getHitbox()[] {
    Rectangle[] bounds = new Rectangle[2];
    bounds[0] = new Rectangle(x, -30, width, GamePanel.HEIGHT - (GamePanel.HEIGHT - upperEdge));
    bounds[1] = new Rectangle(x, upperEdge + gap, width, GamePanel.HEIGHT);
    return bounds;
  }
  
  public boolean notOnScreen() {
    return x < -50;
  }
  
  public boolean getPassed() {
    return passedBird;
  }
  
  public void setPassed() {
    passedBird = true;
  }
}