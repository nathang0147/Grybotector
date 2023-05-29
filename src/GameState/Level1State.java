package GameState;

import Enemies.Enemy1;
import Enemies.Enemy2;
import Sound.AudioPlayer;
import TileMap.TileMap;
import UI.GameOver;
import UI.PauseOverlay;
import UI.WinOverlay;
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
    private ArrayList<Explosion> explosions;
    private HUD hud;
    private AudioPlayer bgMusic;
    public int currentChoice;
    private GameOver gameOver;
    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
        bgMusic = new AudioPlayer("/Sound/level1Sound.mp3");
        bgMusic.play();
    }
    private PauseOverlay pauseOverlay;
    private  boolean isPaused=false;
    private Gate gate;
    public static boolean isWin=false;
    public WinOverlay winOverlay;
    public static boolean isDead=false;

    public void init() {

        tileMap = new TileMap(32);
        tileMap.loadTiles("/TileSet/Tilesheet.png");
        tileMap.loadMap("/Map/Map_level1.txt");
        tileMap.setPosition(0,0);

        bg=new Background("/assets/background_level1.png",0.1);

        player = new Player(tileMap);
        player.setPosition(20,210);
        enemies = new ArrayList<Enemy>();
        gate= new Gate(tileMap);
        gameOver= new GameOver();
//        gate.setPosition(3360,192);

        Enemy1 e1_Ene1 = new Enemy1(tileMap);
        Enemy1 e3_Ene1 = new Enemy1(tileMap);
        Enemy1 e4_Ene1 = new Enemy1(tileMap);

        Enemy2 e2_Ene2 = new Enemy2(tileMap);
        Enemy2 e5_Ene2 = new Enemy2(tileMap);
        Enemy2 e6_Ene2 = new Enemy2(tileMap);


        enemies.add(e1_Ene1);
        enemies.add(e3_Ene1);
        enemies.add(e4_Ene1);

        enemies.add(e2_Ene2);
        enemies.add(e5_Ene2);
        enemies.add(e6_Ene2);



        e1_Ene1.setPosition(352,210);

        e2_Ene2.setPosition(192,110);

        e3_Ene1.setPosition(1216,210);
        e4_Ene1.setPosition(1568,210);

        e5_Ene2.setPosition(2272,142);
        e6_Ene2.setPosition(3164-32*5,210);

        explosions = new ArrayList<Explosion>();
        hud = new HUD(player);
        pauseOverlay= new PauseOverlay();
        winOverlay= new WinOverlay();


    }
    public void update() {
        if(!isPaused&& !isWin&& !isDead) {
            player.update();
            tileMap.setPosition(
                    GamePanel.WIDTH / 2 - player.getX(),
                    GamePanel.HEIGHT / 2 - player.getY()
            );
            bg.setPosition(tileMap.getX(), tileMap.getY());
            player.checkAttack(enemies);

          //  update all enemies
            for (int i = 0; i < enemies.size(); i++) {
                Enemy e = enemies.get(i);
                e.checkAttackEnemy(player);
                e.update(player);
                if (e.isDead()) {
                    enemies.remove(i);
                    i--;
                    explosions.add(
                            new Explosion(e.getX(),e.getY())
                    );
                    System.out.println("Enemies is dead");
                }
            }
            // Update explosions
            for( int i=0; i<explosions.size();i++){
                explosions.get(i).update();
                if(explosions.get(i).shouldRemove()){
                    explosions.remove(i);
                    i--;
                }
            }
            if(enemies.size()==0) {
                gate.setPosition(3264, 192);
                if (gate.intersect(player)){
                    gsm.setState(2);
                }
            }
            gate.update();
            if(player.getHealth()==0){
                isDead=true;
            }

        }
        pauseOverlay.update(currentChoice);

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

         //         Draw explosion
         for (Explosion explosion : explosions) {
             explosion.setMapPosition(tileMap.getX(), tileMap.getY());
             explosion.draw(g);
         }

        //Draw HUD
        hud.draw(g);
         if(isPaused) {
             pauseOverlay.draw(g);
         }
         if(enemies.size()==0) {
            gate.draw(g);
         }
         if(isWin){
             winOverlay.draw(g);
         }
         if(isDead){
             System.out.println("Game is false");
             gameOver.draw(g);
         }

    }
    private void select() {
        if (currentChoice == 0) {
            isPaused=false;
        }
        if (currentChoice == 1) {
            gsm.setState(1);
            isPaused=false;
        }
        if (currentChoice == 2) {
            gsm.setState(0);
            isPaused=false;
        }
    }

//
    public void keyPressed(int k) {
        if(!isPaused&& !isWin&& !isDead) {
            if (k == KeyEvent.VK_LEFT) player.setLeft(true);
            if (k == KeyEvent.VK_RIGHT) player.setRight(true);
            if (k == KeyEvent.VK_UP) player.setJumping(true);
            if (k == KeyEvent.VK_DOWN) player.setDown(true);
            if (k == KeyEvent.VK_K) player.setShooting();
            if (k==KeyEvent.VK_ESCAPE) isPaused=true;
        }else if(isPaused) {
            if (k == KeyEvent.VK_ENTER) {
                select();
            }
            if (k == KeyEvent.VK_RIGHT) {
                    currentChoice--;
                    if (currentChoice == -1) {
                        currentChoice = 3 - 1;
                    }
                    System.out.println("On the right");
            }
            if (k == KeyEvent.VK_LEFT) {
                    currentChoice++;
                    if (currentChoice == 3) {
                        currentChoice = 0;
                    }
                    System.out.println("on the left");
            }
        }
        else if(isWin){
            if(k==KeyEvent.VK_ESCAPE){
                isWin=false;
                gsm.setState(0);
                System.out.println("game win");
            }
        } else if (isDead) {
            if(k==KeyEvent.VK_ESCAPE){
                isDead=false;
                gsm.setState(0);
                System.out.println("false");
            }

        }
    }
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT) player.setLeft(false);
        if(k == KeyEvent.VK_RIGHT) player.setRight(false);
        if(k == KeyEvent.VK_UP) player.setJumping(false);
        if(k == KeyEvent.VK_DOWN) player.setDown(false);
    }

}
