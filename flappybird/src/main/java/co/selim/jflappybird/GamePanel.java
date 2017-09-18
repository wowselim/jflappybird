package co.selim.jflappybird;

import co.selim.jflappybird.gamestates.GameStateManager;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.VolatileImage;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
    private boolean running;
    private final int FPS = 60;
    private final double RENDER_INTERVAL = 1000.0 / FPS;

    private VolatileImage image;
    private Graphics2D g;

    private GameStateManager gsm;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(GameConstants.WIDTH * GameConstants.SCALE, GameConstants.HEIGHT * GameConstants.SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        init();

        Thread gameThread = new Thread(this, "GameThread");
        addKeyListener(this);
        gameThread.start();
    }

    private void init() {
        image = createVolatileImage(GameConstants.WIDTH, GameConstants.HEIGHT);
        g = (Graphics2D) image.getGraphics();

        running = true;

        gsm = new GameStateManager();
    }

    @Override
    public void run() {
        double nextUpdate = System.currentTimeMillis();

        while (running) {
            while (System.currentTimeMillis() > nextUpdate) {
                update();

                nextUpdate += RENDER_INTERVAL;
            }

            draw();
        }
    }

    private void update() {
        gsm.update();
    }

    private void draw() {
        gsm.draw(g);
        drawToScreen();
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, GameConstants.WIDTH * GameConstants.SCALE, GameConstants.HEIGHT * GameConstants.SCALE, null);
        g2.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e.getKeyCode());
    }

}