import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MainGameState extends GameState {  
  
  private Bird bird;
  private int points;
  private boolean gameOver = false;
  private boolean passedHundred = false;
  private BufferedImage bg = ImageLoader.loadImage("res\\img\\bg.png");
  
  private List<Pipe> pipes = new ArrayList<>();
  private List<Pipe> pipesToRemove = new ArrayList<>();  
  
  long lastPipe;
  long pipeTimer = 1;
  
  private Random randomizer = new Random();
  
  public MainGameState(GameStateManager gsm) {
    this.gsm = gsm;
    init();
  }
  
  public void init() {
    bird = new Bird();
    bird.x = 50;
    bird.y = 100;
    points = 0;
    lastPipe = System.nanoTime();
  }
  
  public void update() {                        
    if(!gameOver) {
      long now = System.nanoTime();
      if ((now - lastPipe) / 1_000_000_000 > pipeTimer) {
        pipes.add(new Pipe(randomizer.nextInt(GamePanel.HEIGHT - 140) + 70)); 
        lastPipe = System.nanoTime();
      }
      bird.update();
      for(Pipe p : pipes) {
        p.update();
        if(bird.collides(p)) {
          gameOver = true;
        }
        if(!p.getPassed() && p.x < bird.x) {
          p.setPassed();
          points++;
        }
      }
      
      for(Pipe p : pipes) {
        if(p.notOnScreen()) {
          pipesToRemove.add(p);
        }
      }
      
      pipes.removeAll(pipesToRemove);
      
      if(bird.getY() + bird.getHeight() > GamePanel.HEIGHT) {
        gameOver = true;
      }
      return;
    }
    bird.y = GamePanel.HEIGHT - bird.height;
  }
  
  public void draw(Graphics2D g) {
    g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    g.drawImage(bg, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
    
    for(Pipe p : pipes) {
      p.render(g);
    }
    bird.render(g);
    
    g.setColor(Color.RED);
    g.drawString(String.valueOf(points), 5, 15);
    if(gameOver) {
      g.drawString("Game over. Press ESC to try again.", GamePanel.WIDTH / 2 - 95, GamePanel.HEIGHT / 2);
    }
  }
  
  public void restart() {
    for(Pipe p : pipes) {
      pipesToRemove.add(p);
    }
    
    init();
    
    points = 0;
    gameOver = false;
  }
  
  public void keyPressed(int k) {
    switch(k) {
      case KeyEvent.VK_SPACE: 
      bird.setFlapping();
      break;
      case KeyEvent.VK_ESCAPE:
      if(gameOver) {
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