import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
  
  public static final String TITLE = "Flappy Bird";
  public static final int WIDTH = 320;
  public static final int HEIGHT = 240;
  public static final int SCALE = 2;
  
  private Thread gameThread;
  private boolean running;
  private int FPS = 60;
  private long targetTime = 1000 / FPS;
  
  private BufferedImage image;
  private Graphics2D g;
  
  private GameStateManager gsm;
  
  public GamePanel() {
      super();
      setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
      setFocusable(true);
      requestFocus();
  }
  
  public void addNotify() {
      super.addNotify();
      if (Objects.isNull(gameThread)) {
        gameThread = new Thread(this, "GameThread");
        addKeyListener(this);
        gameThread.start();
      }
  }
  
  public void init() {
      image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
      g = (Graphics2D) image.getGraphics();
    
      running = true;
    
      gsm = new GameStateManager();
  }
  
  @Override
  public void run() {
      init();
    
      long start;
      long elapsed;
      long wait;
    
      while (running) {
      
        start = System.nanoTime();
      
        update();
        draw();
        drawToScreen();
      
        elapsed = System.nanoTime() - start;
      
        wait = targetTime - elapsed / 1_000_000;
      
        try {
            Thread.sleep(wait);
        } catch (Exception e) {
        }
      }
  }
  
  private void update() {
      gsm.update();
  }
  
  private void draw() {
      gsm.draw(g);
  }
  
  private void drawToScreen() {
      Graphics g2 = getGraphics();
      g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
      g2.dispose();
  }
  
  @Override
  public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
    
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