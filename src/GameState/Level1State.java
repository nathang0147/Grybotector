package GameState;

import TileMap.TileMap;
import java.awt.*;
import TileMap.Background;

public class Level1State extends GameState{


    private TileMap tileMap;
    private Background bg;
    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(32);
        tileMap.loadTiles("/TilesandTileMap/Tilesheet.png");
        tileMap.loadMap("/TilesandTileMap/Tiledmap.tmx");

        bg = new Background("/assets/background.png", 0.1);
    }


    public void update() {

    }


    public void draw(Graphics2D g) {
        //clear bg ( not real bg)
//
//        g.setColor(new Color(127, 53, 24));
//        g.fillRect(0,0, GamePanel.WIDTH,GamePanel.HEIGHT);

        //Draw Image
        bg.draw(g);

        //Draw
        tileMap.draw(g);
    }


    public void keyPressed(int k) {

    }


    public void keyReleased(int k) {

    }
}
