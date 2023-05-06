package GameState;

import TileMap.TileMap;
import UserInterface.GamePanel;
import Entity.*;
import TileMap.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState{
    private TileMap tileMap;
    private Player player;
    private Background bg;
    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(32);
        tileMap.loadTiles("/TileSet/Tilesheet.png");
        tileMap.loadMap("/Map/TileMap1.txt");
        tileMap.setPosition(0,0);

        bg=new Background("/assets/background.png",0.01);

        player=new Player(tileMap);
        player.setPosition(20,210);
    }


    public void update() {
        player.update();
        tileMap.setPosition(
                GamePanel.WIDTH - player.getX(),
                GamePanel.HEIGHT - player.getY()
        );
    }


    public void draw(Graphics2D g) {
        //bg ( not real bg)
        bg.draw(g);
        //Draw
        tileMap.draw(g);

        //Draw player
        player.draw(g);
    }

//
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(true);
        if(k == KeyEvent.VK_RIGHT) player.setRight(true);
        if(k == KeyEvent.VK_UP) player.setJumping(true);
        if(k == KeyEvent.VK_DOWN) player.setDown(true);
//        if(k == KeyEvent.VK_W) player.setJumping(true);
    }
//
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(false);
        if(k == KeyEvent.VK_RIGHT) player.setRight(false);
        if(k == KeyEvent.VK_UP) player.setJumping(false);
        if(k == KeyEvent.VK_DOWN) player.setDown(false);
//        if(k == KeyEvent.VK_SPACE) player.setJumping(false);
    }

}
