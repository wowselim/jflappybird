import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class GameStateManager {
  private List<GameState> gameStates;
  private int currentState;
  
  public static final int MENUSTATE = 0;
  public static final int LEVEL_1_STATE = 1;
  
  public GameStateManager() {
      gameStates = new ArrayList<>();
      
      currentState = MENUSTATE;
      //gameStates.add(null);
      gameStates.add(new MainGameState(this));
  }
  
  public void setState(int state) {
      currentState = state;
      gameStates.get(currentState).init();
  }
  
  public void update() {
      gameStates.get(currentState).update();
  }
  
  public void draw(Graphics2D g) {
      gameStates.get(currentState).draw(g);
  }
  
  public void keyPressed(int k) {
      gameStates.get(currentState).keyPressed(k);
  }
  
  public void keyReleased(int k) {
      gameStates.get(currentState).keyReleased(k);
  }
}
