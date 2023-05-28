package Enemies;

import Entity.Animation;
import Entity.Bullet;
import Entity.Enemy;
import Entity.Player;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Enemy2 extends Enemy {

    private int currentAction;
    private int IDLE = 0;
    private int ATK = 1;


    private ArrayList<BufferedImage[]> sprites;
    private  int[] numFrames= {4,8};

    private ArrayList<Magicbutt> magicbutts;
    private int dame, damageCost, buttNum, maxButt;
    public Enemy2(TileMap tm) {
        super(tm);

        width = (int) 49.25;

        health = maxHealth = 5;
        damage = 3;

        height =(int) 40;
        cheight = 10;
        cwidth = 10;

        health = maxHealth = 5;
        damage = 2;

        buttNum = maxButt = 600;
        damageCost = 10;
        dame = 1;
        magicbutts = new ArrayList<Magicbutt>();


        try {
            //load sprites
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Boss/Boss2.png"));
            sprites = new ArrayList<BufferedImage[]>();
            for(int i = 0; i < 2; i++) {

                BufferedImage[] bi =
                        new BufferedImage[numFrames[i]];

                for(int j = 0; j < numFrames[i]; j++) {
                    if(i==0){
                        bi[j] = spritesheet.getSubimage(
                                j * width,
                                i * height,
                                width,
                                height
                        );
                    }
                    else {
                        bi[j] = spritesheet.getSubimage(
                                j * width,
                                i * height,
                                width,
                                height
                        );}
                }
                sprites.add(bi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentAction = IDLE;
        animation= new Animation();
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(300);

    }
    public void update(){
        checkCollision();
        setPosition(xtemp,ytemp);

//        buttNum += 1;
//        if (buttNum > maxButt) buttNum = maxButt;
//        if (!notOnScreen()) {
//            if (buttNum > damageCost) {
//                buttNum -= damageCost;
//                Magicbutt bl = new Magicbutt(tileMap, facingRight);
//                bl.setPosition(x +1, y - 1);
//                magicbutts.add(bl);
//            }
//        }
//        for(int i = 0; i < magicbutts.size(); i++){
//            magicbutts.get(i).update();
//            if(magicbutts.get(i).shouldRemove()){
//                magicbutts.remove(i);
//                i--;
//            }
//        }

        if (notOnScreen()&& currentAction!=IDLE){
            currentAction = IDLE;
            animation.setFrames(sprites.get(currentAction));
            animation.setDelay(400);
            //System.out.println("IDLEEEEEEE" + currentAction);
        }
        else if (!notOnScreen()&& currentAction!=ATK){
            currentAction = ATK;
            animation.setFrames(sprites.get(currentAction));
            animation.setDelay(400);
            //System.out.println("ATKKKKKK" + currentAction);
        }
        if(flinching){
            long elapse=(System.nanoTime()-flinchedTime)/1000000;
            if(elapse>400){
                flinching=false;
            }
        }
        animation.update();
    }
    public void draw(Graphics2D g){
        setMapPosition();
        super.draw(g);
        for(int i  = 0; i < magicbutts.size(); i++){
            magicbutts.get(i).draw(g);
        }
    }

    public void checkAttackEnemy(Player player){
        for(int i = 0; i < magicbutts.size(); i ++){
            if(magicbutts.get(i).intersect(player)){
                player.hitDamage(damage);
                magicbutts.get(i).setHit();
                break;
            }
        }
    }
}
