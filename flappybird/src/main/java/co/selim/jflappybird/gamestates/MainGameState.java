package co.selim.jflappybird.gamestates;

import co.selim.jflappybird.GameConstants;
import co.selim.jflappybird.GameState;
import co.selim.jflappybird.entities.Bird;
import co.selim.jflappybird.entities.Pipe;
import co.selim.jflappybird.resources.ImageLoader;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MainGameState extends GameState {
    private Bird bird;
    private int points;
    private boolean gameOver = false;
    private BufferedImage bg = ImageLoader.loadImage("bg.png");

    private List<Pipe> pipes = new ArrayList<>();
    private List<Pipe> pipesToRemove = new ArrayList<>();

    long lastPipe;
    long pipeTimer = 1;

    private ThreadLocalRandom randomizer = ThreadLocalRandom.current();

    public MainGameState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        bird = new Bird();
        bird.setX(50);
        bird.setY(100);
        points = 0;
        lastPipe = System.nanoTime();
    }

    public void update() {
        if (!gameOver) {
            long now = System.nanoTime();
            if ((now - lastPipe) / 1_000_000_000 > pipeTimer) {
                pipes.add(new Pipe(randomizer.nextInt(GameConstants.HEIGHT - 140) + 70));
                lastPipe = System.nanoTime();
            }
            bird.update();
            for (Pipe p : pipes) {
                p.update();
                if (bird.collides(p)) {
                    gameOver = true;
                }
                if (!p.getPassed() && p.getX() < bird.getX()) {
                    p.setPassed();
                    points++;
                }
            }

            for (Pipe p : pipes) {
                if (p.notOnScreen()) {
                    pipesToRemove.add(p);
                }
            }

            pipes.removeAll(pipesToRemove);
            pipesToRemove.clear();

            if (bird.getY() + bird.getHeight() > GameConstants.HEIGHT) {
                gameOver = true;
            }
            return;
        }
        bird.setY(GameConstants.HEIGHT - bird.getHeight());
    }

    public void draw(Graphics2D g) {
        g.clearRect(0, 0, GameConstants.WIDTH, GameConstants.HEIGHT);
        g.drawImage(bg, 0, 0, GameConstants.WIDTH, GameConstants.HEIGHT, null);

        for (Pipe p : pipes) {
            p.render(g);
        }
        bird.render(g);

        g.setColor(Color.RED);
        g.drawString(String.valueOf(points), 5, 15);
        if (gameOver) {
            g.drawString("Game over. Press ESC to try again.", GameConstants.WIDTH / 2 - 95, GameConstants.HEIGHT / 2);
        }
    }

    public void restart() {
        pipes.clear();

        init();

        points = 0;
        gameOver = false;
    }

    public void keyPressed(int k) {
        switch (k) {
            case KeyEvent.VK_SPACE:
                bird.setFlapping();
                break;
            case KeyEvent.VK_ESCAPE:
                if (gameOver) {
                    restart();
                } else {
                    System.exit(0);
                }

                break;
            default:

        }
    }

    public void keyReleased(int k) {
    }
}
