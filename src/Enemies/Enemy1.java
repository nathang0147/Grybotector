package Enemies;

import Entity.Animation;
import Entity.Enemy;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Enemy1  extends Enemy {
    private ArrayList<BufferedImage[]> sprites;
    private  int[] numFrames= {10,10};
    private int currentAction;
    private int RUN = 0;
    private int ATK = 1;


    public Enemy1(TileMap tm) {
        super(tm);

        moveSpeed=0.3;
        maxSpeed=0.4;
        fallSpeed=0.2;
        maxFall=10.0;

        width=62;
        height=84;
        cheight=20;
        cwidth=20;

        health = maxHealth = 4;
        damage=2;

        try {
            //load sprites
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Boss/Boss_1.png"));
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

        currentAction = RUN;
        animation = new Animation();
        animation.setFrames(sprites.get(currentAction));
        animation.setDelay(300);

        right= true;
        facingRight =true;

    }

    private void getNextPosition(){
        if (left) {
            dx -= moveSpeed;
            //Update movement
            if (dx < -maxSpeed) dx = -maxSpeed;
        } else if (right) {
            dx += moveSpeed;
            if (dx > maxSpeed) dx = maxSpeed;
        }
        if(falling){
            dy+=fallSpeed;
        }
    }
    public void update(){
        getNextPosition();
        checkCollision();
        setPosition(xtemp, ytemp);

        if(flinching) {
            long elapse = (System.nanoTime()-flinchedTime) / 1000000;
            if (elapse>400){
                flinching=false;
            }
        }

        if (!notOnScreen()){
            if (currentAction!=ATK){
                currentAction = ATK;
                animation.setFrames(sprites.get(currentAction));
                animation.setDelay(400);
            }

        }
        else if (notOnScreen()) {
                right = true;
                facingRight = true;
                currentAction = RUN;
                animation.setFrames(sprites.get(currentAction));
                animation.setDelay(300);

        }
        if (flinching) {
            long elapse = (System.nanoTime() - flinchedTime) / 1000000;
            if (elapse > 400) {
                flinching = false;
            }
        }
        if (right && dx == 0) {
            right = false;
            left = true;
            facingRight = false;
            //System.out.println("left");
        } else if (left && dx == 0) {
            right = true;
            left = false;
            facingRight = true;
            //System.out.println("right");
        }
        animation.update();
    }
    public void draw(Graphics2D g){
//        if(notOnScreen()) return;
        setMapPosition();
        super.draw(g);

    }
}
