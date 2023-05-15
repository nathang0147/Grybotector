package GameState;

import Enemies.Enemy1;
import Enemies.Enemy2;
import TileMap.TileMap;
import UserInterface.GamePanel;
import Entity.*;
import TileMap.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level1State extends GameState{
    private TileMap tileMap;
    private Player player;
    private Background bg;
    private ArrayList<Enemy> enemies;
    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(32);
        tileMap.loadTiles("/TileSet/Tilesheet.png");
        tileMap.loadMap("/Map/Map_edited.txt");
        tileMap.setPosition(0,0);


        bg=new Background("/assets/background.png",0.1);

        player=new Player(tileMap);
        player.setPosition(20,210);
        enemies= new ArrayList<Enemy>();
        Enemy1 e1= new Enemy1(tileMap);
        Enemy2 e2= new Enemy2(tileMap);
         enemies.add(e1);
         enemies.add(e2);
        e1.setPosition(150,100);
        e2.setPosition(200,100);
    }


    public void update() {
        player.update();
        tileMap.setPosition(
                GamePanel.WIDTH/2 - player.getX(),
                GamePanel.HEIGHT/2 - player.getY()
        );
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }
    }


     public void draw(Graphics2D g) {
        //bg ( not real bg)
        bg.draw(g);
        //Draw
        tileMap.draw(g);
        //Draw player
         System.out.println(enemies.size());
        player.draw(g);
        for (int i = 0; i < enemies.size(); i++) {
             enemies.get(i).draw(g);
         }
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
