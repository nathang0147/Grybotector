package GameState;

import Enemies.Boss;
import Enemies.Enemy1;
import Enemies.Enemy2;
import Entity.*;
import TileMap.Background;
import TileMap.TileMap;
import UI.GameOver;
import UI.PauseOverlay;
import UI.WinOverlay;
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

    public  int currentchoice;
    private GameOver gameOver;
    private PauseOverlay pauseOverlay;
    private  boolean isPaused=false;
    private Gate gate;
    public static boolean isWin=false;
    public WinOverlay winOverlay;
    public static boolean isDead=false;
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
        player.setPosition(10,113);
        enemies = new ArrayList<Enemy>();
        explosions = new ArrayList<Explosion>();

        Enemy1 e1_Ene1 = new Enemy1(tileMap);

        Enemy2 e2_Ene2 = new Enemy2(tileMap);
        Enemy2 e5_Ene2 = new Enemy2(tileMap);
        Enemy2 e6_Ene2 = new Enemy2(tileMap);
        // add ui
        gate= new Gate(tileMap);
        gameOver= new GameOver();

        //add Boss
        Boss boss=new Boss(tileMap);

        //set boss position
        boss.setPosition(2304,145);

//        enemies.add(e1_Ene1);
        enemies.add(e2_Ene2);
//        enemies.add(e5_Ene2);
//        enemies.add(e6_Ene2);

        enemies.add(boss);

        e1_Ene1.setPosition(2236,205);

        //updated for map level 2
        e2_Ene2.setPosition(160,205);
        e5_Ene2.setPosition(2272,205);
        e6_Ene2.setPosition(3264,205);

        hud = new HUD(player);
        pauseOverlay= new PauseOverlay();
        winOverlay= new WinOverlay();

    }
    public void update() {
        if(isPaused==false && isWin==false && isDead==false) {
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
                gate.setPosition(3232, 192);
                if (gate.intersect(player)){
                    isWin=true;
                }
            }
            gate.update();
            if(player.getHealth()==0){
                isDead=true;
            }

        }
        pauseOverlay.update(currentchoice);


    }
     public void draw(Graphics2D g) {
         //bg ( not real bg)
         bg.draw(g);
         //Draw
         tileMap.draw(g);
         //Draw player

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
         if(isPaused==true) {
             pauseOverlay.draw(g);
         }
         if(enemies.size()==0) {
             gate.draw(g);
         }
         if(isWin==true){
             winOverlay.draw(g);
         }
         if(isDead==true){
             gameOver.draw(g);
         }
    }
    private void select() {
        if (currentchoice == 0) {
            isPaused=false;
        }
        if (currentchoice == 1) {
            gsm.setState(2);
            isPaused=false;
        }
        if (currentchoice == 2) {
            gsm.setState(0);
            isPaused=false;
        }
    }

    public void keyPressed(int k) {
        if(isPaused==false && isWin==false && isDead==false) {
            if (k == KeyEvent.VK_LEFT) player.setLeft(true);
            if (k == KeyEvent.VK_RIGHT) player.setRight(true);
            if (k == KeyEvent.VK_UP) player.setJumping(true);
            if (k == KeyEvent.VK_DOWN) player.setDown(true);
            if (k == KeyEvent.VK_K) player.setShooting();
            if (k==KeyEvent.VK_ESCAPE) isPaused=true;
        }else if(isPaused==true) {
            if (k == KeyEvent.VK_ENTER) {
                select();
            }
            if (k == KeyEvent.VK_RIGHT) {
                currentchoice--;
                if (currentchoice == -1) {
                    currentchoice = 3 - 1;
                }

            }
            if (k == KeyEvent.VK_LEFT) {
                currentchoice++;
                if (currentchoice == 3) {
                    currentchoice = 0;
                }

            }
        }
        else if(isWin==true){
            if(k==KeyEvent.VK_ESCAPE){
                isWin=false;
                gsm.setState(0);

            }
        } else if (isDead==true) {
            if(k==KeyEvent.VK_ESCAPE){
                isDead=false;
                gsm.setState(0);

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
