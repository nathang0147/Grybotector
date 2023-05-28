package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class StoryState extends  GameState{
    private Background bg;
    public StoryState(GameStateManager gameStateManager) {
        this.gsm=gameStateManager;
        init();
    }

    @Override
    public void init() {
        bg= new Background("/Icon/game_story.png",0.0);

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,320,240);
        bg.draw(g);

    }
    private void select(){

    }

    @Override
    public void keyPressed(int k) {
        if(k==KeyEvent.VK_ESCAPE) gsm.setState(0);

    }

    @Override
    public void keyReleased(int k) {

    }
}
