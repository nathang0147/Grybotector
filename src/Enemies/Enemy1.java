package Enemies;

import Entity.Animation;
import Entity.Enemy;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy1  extends Enemy {
    protected BufferedImage[] sprites;

    public Enemy1(TileMap tm) {
        super(tm);

        moveSpeed=0.3;
        maxSpeed=0.4;
        fallSpeed=0.2;
        maxFall=10.0;

        width=50;
        height=50;
        cheight=20;
        cwidth=20;

        health= maxHealth=4;
        damage=2;

        try {
            // load sprites
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Boss/Boss_run.png"));
            if(spritesheet==null) System.out.println("Sprite null");
            System.out.println(spritesheet.getWidth());
            int numFrames= spritesheet.getWidth()/width;
            System.out.println(numFrames);
            sprites=  new  BufferedImage[numFrames];
            for (int i = 0; i < sprites.length; i++) {
                sprites[i]=spritesheet.getSubimage(i*width,0,width,height);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        animation= new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300);

        right= true;
        facingRight=true;

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
        setPosition(xtemp,ytemp);

        if(flinching) {
            long elapse = (System.nanoTime()-flinchedTime) / 1000000;
            if (elapse>400){
                flinching=false;
            }
        }

        if(right && dx==0){
            right=false;
            left=true;
            facingRight=false;
        }
        else if(left && dx==0){
            left=false;
            right=true;
            facingRight=true;
        }

        animation.update();
    }
    public void draw(Graphics2D g){
        if(notOnScreen()) return;
        setMapPosition();
        super.draw(g);

    }
}
