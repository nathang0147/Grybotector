package GameState;

import TileMap.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Level1State extends GameState {
    private TileMap tileMap;
    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() throws IOException {

        tileMap = new Tile("Resources/TileSet/Tilesheet.png", 1);
        tileMap.loadTiles("Resources/TileSet/Tilesheet.png");
        tileMap.loadMap("Resources/Map/TileMap1.txt");

    }
    public void update() {}
    public void draw(Graphics2D g) {
    }
    public void keyPressed(int k){}
    public void keyReleased(int k){}


}
