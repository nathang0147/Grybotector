package GameState;

import TileMap.TileMap;
import UserInterface.GamePanel;

import java.awt.*;

public class Level1State extends GameState{
    private TileMap tileMap;
    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(32);
        tileMap.loadTiles("/TileSet/Tilesheet.png");
        tileMap.loadMap("/Map/TileMap1.txt");

    }


    public void update() {

    }


    public void draw(Graphics2D g) {
        //clear bg ( not real bg)
        g.setColor(Color.BLUE);
        g.fillRect(0,0, GamePanel.WIDTH,GamePanel.HEIGHT);
        //Draw
        tileMap.draw(g);
    }


    public void keyPressed(int k) {

    }


    public void keyReleased(int k) {

    }
}
