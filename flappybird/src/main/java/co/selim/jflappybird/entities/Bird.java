package co.selim.jflappybird.entities;

import co.selim.jflappybird.resources.ImageLoader;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bird implements Entity {
    private double x, y, width, height;
    private double fallSpeed, maxFallSpeed, currentFallSpeed;
    private BufferedImage sprite = ImageLoader.loadImage("bird.png");

    public Bird() {
        width = 30;
        height = 20;
        fallSpeed = 0.1D;
        maxFallSpeed = 10D;
    }

    public void update() {

        if (y < -30) {
            y = -30;
        }

        currentFallSpeed += fallSpeed;
        if (currentFallSpeed > maxFallSpeed) {
            currentFallSpeed = maxFallSpeed;
        }
        y += currentFallSpeed;

    }

    public void render(Graphics2D g) {
        if (notOnScreen()) {
            return;
        }
        g.drawImage(sprite, (int) x, (int) y, (int) width, (int) height, null);
    }

    public boolean collides(Entity other) {
        boolean doesCollide = false;
        for (Rectangle me : getHitboxes()) {
            for (Rectangle pipe : other.getHitboxes()) {
                if (me.intersects(pipe)) {
                    doesCollide = true;
                }
            }
        }
        return doesCollide;
    }

    public Rectangle[] getHitboxes() {
        Rectangle[] hitbox = new Rectangle[1];
        hitbox[0] = new Rectangle((int) x, (int) y, (int) width, (int) height);
        return hitbox;
    }

    public boolean notOnScreen() {
        return y + height < 1;
    }

    public void setFlapping() {
        currentFallSpeed -= 2.3D;
    }

    public int getY() {
        return (int) y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return (int) height;
    }

    public int getX() {
        return (int) x;
    }
}
