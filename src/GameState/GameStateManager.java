package GameState;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
public class GameStateManager {
    private ArrayList<GameState> gameStates;
    private int currentState;

    private static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
    public GameStateManager() {
        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new Level1State(this));

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

    //Check coi chuột vào ô chưa(hoạt hoạ)
    public boolean isIn(MouseEvent e, MenuButton mb) {

        return mb.getBounds().contains(e.getX(), e.getY());
    }

    public void keyPressed(int k) {
        gameStates.get(currentState).keyPressed(k);
    }
    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);
    }

    public void mouseClicked(MouseEvent e) {

    }

    public  void mousePressed(MouseEvent e){
        gameStates.get(currentState).mouseReleased(e);
    };

    public  void mouseReleased(MouseEvent e){
        gameStates.get(currentState).mouseReleased(e);
    };

    public  void mouseMoved(MouseEvent e){};
}