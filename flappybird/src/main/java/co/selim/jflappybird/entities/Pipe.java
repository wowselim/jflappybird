package co.selim.jflappybird.entities;

import co.selim.jflappybird.GameConstants;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pipe implements Entity {
  private int x;
  private boolean passedBird;
  private int width = 25;
  private int upperEdge;
  private int gap = 50;
  
  public Pipe(int upperEdge) {
    x = GameConstants.WIDTH;
    this.upperEdge = upperEdge;
  }
  
  public void update() {
    x--;  
  }
  
  public void render(Graphics2D g) {
    if (notOnScreen()) {
      return;
    }
    g.setColor(new Color(0x1DBC3A));
    g.fillRect(x - 2, upperEdge - 35, width + 4, 5);
    g.fillRect(x - 2, upperEdge + gap - 5, width + 4, 5);

    g.fillRect(x, -30, width, GameConstants.HEIGHT - (GameConstants.HEIGHT - upperEdge));
    g.fillRect(x, upperEdge + gap, width, GameConstants.HEIGHT);
  }
  
  public boolean collides(Entity other) {
    return x < -30;
  }
  
  public Rectangle getHitboxes()[] {
    Rectangle[] bounds = new Rectangle[2];
    bounds[0] = new Rectangle(x, -30, width, GameConstants.HEIGHT - (GameConstants.HEIGHT - upperEdge));
    bounds[1] = new Rectangle(x, upperEdge + gap, width, GameConstants.HEIGHT);
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

  public int getX() {
    return x;
  }
}