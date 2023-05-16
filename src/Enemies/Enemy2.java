package Enemies;

import Entity.Animation;
import Entity.Enemy;
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
    public Enemy2(TileMap tm) {
        super(tm);

        width = (int) 49.25;
        height =(int) 44.5;
        cheight = 30;
        cwidth = 30;

        health = maxHealth = 5;
        damage = 3;

        height =(int) 40;
        cheight = 10;
        cwidth = 10;

        health = maxHealth = 5;
        damage = 2;

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
    }
}
