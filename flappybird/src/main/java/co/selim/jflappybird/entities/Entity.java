package co.selim.jflappybird.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface Entity {
    void update();

    void render(Graphics2D g);

    boolean collides(Entity other);

    Rectangle[] getHitboxes();

    boolean notOnScreen();
}