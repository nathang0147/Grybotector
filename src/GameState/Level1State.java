package GameState;

import Entity.Player;
import TileMap.TileMap;
import UserInterface.GamePanel;

import java.awt.*;

public class Level1State extends GameState{
    private TileMap tileMap;
    private Player player;
    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(32);
        tileMap.loadTiles("/TileSet/Tilesheet.png");
        tileMap.loadMap("/Map/TileMap1.txt");
        tileMap.setPosition(0.0,0.0);

        player = new Player(tileMap);
        player.setPosition(100,100);
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
