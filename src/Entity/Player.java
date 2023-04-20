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
       private int currentAct;
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

          //load sprites
          try {

             BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Animation/spritesheet.png"));

             sprites = new ArrayList<BufferedImage[]>();
             for(int i = 0; i < 7; i++) {

                BufferedImage[] bi =
                        new BufferedImage[numFrames[i]];

                for(int j = 0; j < numFrames[i]; j++) {

                   if(i != 6) {
                      bi[j] = spritesheet.getSubimage(
                              j * width,
                              i * height,
                              width,
                              height
                      );
                   }
                   else {
                      bi[j] = spritesheet.getSubimage(
                              j * width * 2,
                              i * height,
                              width,
                              height
                      );
                   }

                }

                sprites.add(bi);

             }

          }
          catch(Exception e) {
             e.printStackTrace();
          }

          animation = new Animation();
          currentAction = IDLE;
          animation.setFrames(sprites.get(IDLE));
          animation.setDelay(400);

       }

       public int getHealth() { return health; }
       public int getMaxHealth() { return maxHealth; }
       public int getFire() { return fire; }
       public int getMaxFire() { return maxFire; }

       public void setFiring() {
          firing = true;
       }
       public void setScratching() {
          scratching = true;
       }
       public void setGliding(boolean b) {
          gliding = b;
       }

       private void getNextPosition() {

          // movement
          if(left) {
             dx -= moveSpeed;
             if(dx < -maxSpeed) {
                dx = -maxSpeed;
             }
          }
          else if(right) {
             dx += moveSpeed;
             if(dx > maxSpeed) {
                dx = maxSpeed;
             }
          }
          else {
             if(dx > 0) {
                dx -= stopSpeed;
                if(dx < 0) {
                   dx = 0;
                }
             }
             else if(dx < 0) {
                dx += stopSpeed;
                if(dx > 0) {
                   dx = 0;
                }
             }
          }

          // cannot move while attacking, except in air
          if(
                  (currentAction == SCRATCHING || currentAction == FIREBALL) &&
                          !(jumping || falling)) {
             dx = 0;
          }

          // jumping
          if(jumping && !falling) {
             dy = jumpStart;
             falling = true;
          }

          // falling
          if(falling) {

             if(dy > 0 && gliding) dy += fallSpeed * 0.1;
             else dy += fallSpeed;

             if(dy > 0) jumping = false;
             if(dy < 0 && !jumping) dy += stopJumpSpeed;

             if(dy > maxFallSpeed) dy = maxFallSpeed;

          }

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

       public void setNextPosition() {
          int doublejump = 0;

          //move normal
          if (left) {
             dx -= moveSpeed;
             if (dx < -maxSpeed) dx = maxSpeed;
          } else if (right) {
             dx += moveSpeed;
             if (dx > maxSpeed) dx = maxSpeed;
          } else {
             if (dx > 0) {
                dx -= stopSpeed;
                if (dx < stopSpeed) dx = 0;
             } else if (dx < 0) {
                dx += stopSpeed;
                if (dx > -stopSpeed) dx = 0;
             }
          }

          // can move when act
          if (currentAct == SHOOTING || currentAct == MELEE && !(jumping || falling)) {
             dx = 0;
          }

       }
    }