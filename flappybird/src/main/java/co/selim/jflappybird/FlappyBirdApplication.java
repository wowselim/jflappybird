package co.selim.jflappybird;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class FlappyBirdApplication {
  public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> {
          JFrame window = new JFrame(GameConstants.TITLE);
          window.setContentPane(new GamePanel());
          window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          window.setResizable(false);
          window.pack();
          window.setLocationRelativeTo(null);
          window.setVisible(true);
      });
  }
}
