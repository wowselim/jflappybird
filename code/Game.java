import javax.swing.JFrame;

public class Game {
  public static void main(String[] args) {
    JFrame window = new JFrame(GamePanel.TITLE);
    window.setContentPane(new GamePanel());
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.pack();
    window.setLocationRelativeTo(null);
    window.setVisible(true);
    Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    //java.awt.GraphicsDevice gd = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    //gd.setFullScreenWindow(window);
    //gd.setDisplayMode(new java.awt.DisplayMode(GamePanel.WIDTH * GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE, 32, java.awt.DisplayMode.REFRESH_RATE_UNKNOWN));
  }
}
