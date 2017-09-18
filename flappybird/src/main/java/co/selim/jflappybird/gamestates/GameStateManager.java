package co.selim.jflappybird.gamestates;

import co.selim.jflappybird.GameState;

import java.awt.Graphics2D;

public class GameStateManager {
    private GameState currentState;

    public GameStateManager() {
        this.currentState = new MainGameState(this);
    }

    public void update() {
        currentState.update();
    }

    public void draw(Graphics2D g) {
        currentState.draw(g);
    }

    public void keyPressed(int k) {
        currentState.keyPressed(k);
    }

    public void keyReleased(int k) {
        currentState.keyReleased(k);
    }
}
