package Enemies;

import Entity.Animation;
import Entity.Enemy;
import Entity.Player;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Boss extends Enemy {

    private boolean isAttack;
    //LOAD sprites
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames={
            6, 12, 15, 5, 22
    };

    //Numerical action
    private static final int IDLE=0;
    private static final int WALK=1;
    private static final int ATTACK=2;
    
    
    public Boss(TileMap tm) {
        super(tm);


        //move speed
        moveSpeed=0.3;
        maxSpeed=1.5;

        //Size image of boss
        height=160;
        width=288;

        //Real Size of boss
        cheight=40;
        cwidth=70;

        //Health
        health= maxHealth =100;

        //hit
        damage=3;

        //falling speed
        fallSpeed=0.2;
        maxFall=10;

        //direction
        facingRight=true;

        //flinching
        flinching=false;

        //load sprite
        try {
            sprites=new ArrayList<>();
            BufferedImage spritesheet= ImageIO.read(
                    getClass().getResourceAsStream("/supper_Boss/Big_boss.png"));

            for(int i=0;i<5;i++){
                BufferedImage[] bi= new BufferedImage[numFrames[i]];
                for(int j=0;j<numFrames[i];j++){
                    bi[j]=spritesheet.getSubimage(
                            j*width,
                            i*height,
                            width,
                            height
                    );
                }
                sprites.add(bi);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //Animation
        animation = new Animation();
        currentAct = WALK;
        animation.setFrames(sprites.get(currentAct));
        animation.setDelay(100);

        //direction
        right=true;
        facingRight=true;
    }

    public void getNextPosition(){

        //Calculate move speed
        if (left) {
            dx -= moveSpeed;
            //Update movement
            if (dx < -maxSpeed) dx = -maxSpeed;
        } else if (right) {
            dx += moveSpeed;
            if (dx > maxSpeed) dx = maxSpeed;
        }

        //falling
        if(falling){
            dy+=fallSpeed;
        }

        if(isAttack){
            dx = 0;

        }

    }

    public void update(Player player){
        //update position
        getNextPosition();
        checkCollision();
        setPosition(xtemp,ytemp);

        //flinching effect
        if(flinching) {
            long elapse = (System.nanoTime()-flinchedTime) / 1000000;
            if (elapse>400){
                flinching=false;
            }
        }

        if(System.nanoTime() % 20000 == 0){
            isAttack = true;
        }else {
            isAttack = false;
        }


        //update action
        if (!notOnScreen()){
            if(isAttack){
                if(currentAct != ATTACK){
                    currentAct = ATTACK;
                    animation.setFrames(sprites.get(ATTACK));
                    animation.setDelay(70);
                }
            }

        }
        else if (notOnScreen()) {
            right = true;
            facingRight = true;
            currentAct = WALK;
            animation.setFrames(sprites.get(currentAct));
            animation.setDelay(100);
        }


        if (right && dx == 0  ) {
            System.out.println("Turn left");
            right = false;
            left = true;
            facingRight = false;

        } else if (left && dx == 0) {
            right = true;
            left = false;
            facingRight = true;

        }


        animation.update();
    }




    public void draw(Graphics2D g){

        setMapPosition();
        if(!facingRight) {
            g.drawImage(
                    animation.getImage(),
                    (int)(x + xmap - width /2),
                    (int)(y + ymap - height /1.16),
                    null
            );
        }
        else {
            g.drawImage(
                    animation.getImage(),
                    (int)(x + xmap - width /2 + width),
                    (int)(y + ymap - height /1.16),
                    -width,
                    height,
                    null
            );
        }
    }

}