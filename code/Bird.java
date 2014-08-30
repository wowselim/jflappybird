import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.Objects;

public class Bird implements Entity {
  public double x, y, width, height;
  private boolean falling = true;
  private double fallSpeed, maxFallSpeed, currentFallSpeed;
  private BufferedImage sprite = ImageLoader.loadImage("res/img/bird.png");
  
  public Bird() {
    width = 30;
    height = 20;
    fallSpeed = 0.1D;
    maxFallSpeed = 10D;
  }
  
  public void update() {
    
    if(y < -30) {
      y = -30;
    }
    
    currentFallSpeed += fallSpeed;
    if(currentFallSpeed > maxFallSpeed) {
      currentFallSpeed = maxFallSpeed;
    }
    y += currentFallSpeed;
    
  }
  
  public void render(Graphics2D g) {
    if(notOnScreen()) {
      return;
    }
    if(Objects.isNull(sprite)) {
      g.setColor(Color.BLUE);
      g.fillRect((int) x, (int) y, (int) width, (int) height);
    }
    g.drawImage(sprite, (int) x, (int) y, (int) width, (int) height, null);
  }
  
  public boolean collides(Entity other) {
    boolean doesCollide = false;
    for(Rectangle me : getHitbox()) {
      for(Rectangle pipe : other.getHitbox()) {
        if(me.intersects(pipe)) {
          doesCollide = true;
        }
      }
    }
    return doesCollide;
  }
  
  public Rectangle[] getHitbox() {
    Rectangle[] hitbox = new Rectangle[1];
    hitbox[0] = new Rectangle((int) x, (int) y, (int) width, (int) height);
    return hitbox;
  }
  
  public boolean notOnScreen() {
    return y + height < 1;
  }
  
  public void setFalling() {
    falling = true;
  }
  
  public void setFlapping() {
    currentFallSpeed -= 2.5D;         
  }
  
  public int getY() {
    return (int) y;
  }
  
  public int getHeight() {
    return (int) height;
  }
}
