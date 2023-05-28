package GameState;

import Enemies.Boss;
import Enemies.Enemy1;
import Enemies.Enemy2;
import Entity.Enemy;
import Entity.Explosion;
import Entity.HUD;
import Entity.Player;
import TileMap.Background;
import TileMap.TileMap;
import UserInterface.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level2State extends GameState{
    private ArrayList<Explosion> explosions;

    private TileMap tileMap;

    private Player player;
    private Background bg;
    private ArrayList<Enemy> enemies;
    private HUD hud;
    public Level2State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(32);
        tileMap.loadTiles("/TileSet/Tilesheet.png");
        tileMap.loadMap("/Map/Map_level2.txt");
        tileMap.setPosition(0,0);

        bg=new Background("/assets/background_level2.png",0.1);

        player = new Player(tileMap);
        player.setPosition(20,110);
        enemies = new ArrayList<Enemy>();
        explosions = new ArrayList<Explosion>();

        Enemy1 e1_Ene1 = new Enemy1(tileMap);
        //Enemy1 e3_Ene1 = new Enemy1(tileMap);
        //Enemy1 e4_Ene1 = new Enemy1(tileMap);

        Enemy2 e2_Ene2 = new Enemy2(tileMap);
        Enemy2 e5_Ene2 = new Enemy2(tileMap);
        Enemy2 e6_Ene2 = new Enemy2(tileMap);

        //add Boss
        Boss boss=new Boss(tileMap);

        //set boss position
        boss.setPosition(2200,145);

        enemies.add(e1_Ene1);
//        enemies.add(e3_Ene1);
//        enemies.add(e4_Ene1);

        enemies.add(e2_Ene2);
        enemies.add(e5_Ene2);
        enemies.add(e6_Ene2);

        enemies.add(boss);

        //e1_Ene1.setPosition(2080,210);

        //updated for map level 2
        e2_Ene2.setPosition(160,205);

//        e3_Ene1.setPosition(1216,210);
//        e4_Ene1.setPosition(1568,210);

        e5_Ene2.setPosition(2272,205);
        e6_Ene2.setPosition(3264,205);

        hud = new HUD(player);

    }
    public void update() {
        player.update();
        tileMap.setPosition(
                GamePanel.WIDTH/2 - player.getX(),
                GamePanel.HEIGHT/2 - player.getY()
        );
        player.checkAttack(enemies);

        //            update all enemies
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.checkAttackEnemy(player);
            e.update();
            if (e.isDead()) {
                enemies.remove(i);
                i--;}
                explosions.add(
                        new Explosion(e.getX(),e.getY())
                );
        }

        // Update explosions
        for( int i=0; i<explosions.size();i++){
            explosions.get(i).update();
            if(explosions.get(i).shouldRemove()){
                explosions.remove(i);
                i--;
            }
        }
    }
     public void draw(Graphics2D g) {
        //bg ( not real bg)
        bg.draw(g);
        //Draw
        tileMap.draw(g);
        //Draw player
         //System.out.println(enemies.size());
        player.draw(g);
        for (int i = 0; i < enemies.size(); i++) {
             enemies.get(i).draw(g);
         }

        //Draw HUD
        hud.draw(g);

         //         Draw explosion
         for (Explosion explosion : explosions) {
             explosion.setMapPosition(tileMap.getX(), tileMap.getY());
             explosion.draw(g);
         }
    }

//
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(true);
        if(k == KeyEvent.VK_RIGHT) player.setRight(true);
        if(k == KeyEvent.VK_UP) player.setJumping(true);
        if(k == KeyEvent.VK_DOWN) player.setDown(true);
        if (k == KeyEvent.VK_K) player.setShooting();
    }
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(false);
        if(k == KeyEvent.VK_RIGHT) player.setRight(false);
        if(k == KeyEvent.VK_UP) player.setJumping(false);
        if(k == KeyEvent.VK_DOWN) player.setDown(false);
    }
}
