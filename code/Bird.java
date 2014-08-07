import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.Objects;

public class Bird implements Entity {
  public double x, y, width, height, dy;
  private boolean falling = true, flapping = false;
  private double fallSpeed, flapSpeed, maxFallSpeed, currentFallSpeed, currentFlap;
  private BufferedImage sprite = ImageLoader.loadImage("res/img/bird.png");
  
  public Bird() {
    width = 30;
    height = 20;
    fallSpeed = 0.1D;
    maxFallSpeed = 10D;
    flapSpeed = -15D;
  }
  
  public void update() {
    
    if(y < -30) {
      y = -30;
    }
    
    if(flapping) {
      currentFlap += flapSpeed;
            
      if(currentFlap < -40) {
        currentFlap = 0;
        currentFallSpeed = 0;
        setFalling();
        flapping = false;
      }
      y += currentFlap;
    }
    if(falling) {
      currentFallSpeed += fallSpeed;
      if(currentFallSpeed > maxFallSpeed) {
        currentFallSpeed = maxFallSpeed;
      }
      y += currentFallSpeed;
    }               
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
    flapping = true;          
  }
  
  public int getY() {
    return (int) y;
  }
  
  public int getHeight() {
    return (int) height;
  }
}
