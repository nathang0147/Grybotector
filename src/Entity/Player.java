package Entity;

import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

    public class Player extends MapObject {
        //default
        private int health;
        private int maxHealth;
        private int bullet;
        private int bulletDamage;
        private int meleeDamage;
        private int meleeRange;

        ArrayList<BufferedImage[]> animation;
        //act
        private boolean shooting;
        private boolean melee;

        //animation
        private  int currentAct ;
        private int STAY = 0;
        private int MOVE = 1;
        private int JUMPING = 2;
        private int FALLING = 3;
        private int CROUCH = 4;
        private int SHOOTING = 5;
        private int MELEE = 6;
        public Player(TileMap tm) {
            super(tm);

            // size
            width = 32;
            height = 32;
            cwidth = 20;
            cheight = 20;

            //Move
            moveSpeed = 4;
            sneakySpeed = 2;
            maxSpeed = 10;
            maxFall = 4;
            jumpStart = -8.0;
            fallSpeed = 3;
            slowFall = 0.0001;
            stopSpeed = 0.0111;


            health = maxHealth = 5;
            bullet = 3;

            bulletDamage = 1;
            meleeDamage = 3;
            meleeRange = 55;

            //loadImg
            try {


            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        public int getHealth() {
            return health;
        }

        public int getMaxHealth() {
            return maxHealth;
        }

        public boolean isShooting() {
            return shooting;
        }

        public boolean isMelee() {
            return melee;
        }

        public void setNextPosition(){
            int doublejump = 0;

            //move normal
            if(left){
                dx -= moveSpeed;
                if(dx < -maxSpeed) dx = maxSpeed;
            }else if(right){
                dx += moveSpeed;
                if(dx > maxSpeed) dx = maxSpeed;
            }else {
                if(dx > 0){
                    dx -= stopSpeed;
                    if(dx < stopSpeed) dx = 0;
                }else if(dx < 0){
                    dx += stopSpeed;
                    if(dx > -stopSpeed) dx = 0;
                }
            }

            // can move when act
            if(currentAct == SHOOTING || currentAct == MELEE &&!(jumping || falling)){
                dx = 0;
            }

            //jumping
            if(jumping){
                dy = jumpStart;
                falling = true;
                doublejump++;
            }
            //falling
            if(falling){
                dy = fallSpeed;
                if(doublejump >= 2){
                    jumping = false;
                    doublejump = 0;
                };
                if ( dy < 0 && !jumping) dy += slowFall;
                if(dy > maxFall) dy = maxFall;
            }
        }
        public void update(){
            setNextPosition();
            checkCollision();
            setPosition(xtemp,ytemp);
        }

        public void draw(Graphics2D g){
            setMapPosition();
            //draw
            g.setColor( new Color(240,232,205));
            g.fillRect((int) x,(int) y,width,height);
            //

        }
    }
