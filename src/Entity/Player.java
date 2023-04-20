package Entity;

import TileMap.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

    public class Player extends MapObject {

       // player stuff
       private int health;
       private int maxHealth;
       private int bullet;
       private int bulletDamage;
       private boolean dead;



       // animations
       private ArrayList<BufferedImage[]> sprites;
       private final int[] numFrames = {3, 8, 2, 5, 2, 6};

       //act


       // animation actions
       private int currentAct;
       private int IDLE= 3;
       private int RUN = 5;
       private int FALL = 2;
       private int JUMP = 4;
       private int CROUCH = 0;
       private int DEATH = 1;

       public Player(TileMap tm) {
          super(tm);

          // size
          width = 48;
          height = 48;
          cwidth = 20;
          cheight = 20;

          //Move
          moveSpeed = 0.4;
          sneakySpeed = 2;
          maxSpeed = 3;
          maxFall = 4;
          jumpStart = -8.0;
          fallSpeed = 3;
          slowFall = 0.0001;
          stopSpeed = 1;


          health = maxHealth = 5;
          bullet = 3;

          bulletDamage = 1;

          //load sprites
          try {

             BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Animation/spritesheet.png"));

             sprites = new ArrayList<BufferedImage[]>();
             for(int i = 0; i < 6; i++) {

                BufferedImage[] bi =
                        new BufferedImage[numFrames[i]];

                for(int j = 0; j < numFrames[i]; j++) {
                      bi[j] = spritesheet.getSubimage(
                              j * width,
                              i * height,
                              width,
                              height
                      );
                   }
                sprites.add(bi);
             }
          }
          catch(Exception e) {
             e.printStackTrace();
          }

          animation = new Animation();
          currentAct = IDLE;
          animation.setFrames(sprites.get(IDLE));
          animation.setDelay(400);
       }




       public int getHealth() {
          return health;
       }
       public void draw(Graphics2D g) {

          setMapPosition();

          // draw player

          if(facingRight) {
             g.drawImage(
                     animation.getImage(),
                     (int)(x + xmap - width / 2),
                     (int)(y + ymap - height / 2),
                     null
             );
          }
          else {
             g.drawImage(
                     animation.getImage(),
                     (int)(x + xmap - width / 2 + width),
                     (int)(y + ymap - height / 2),
                     -width,
                     height,
                     null
             );

          }

       }




       public int getMaxHealth() {
          return maxHealth;
       }
//
//       public boolean isShooting() {
//          return shooting;
//       }
//
//       public boolean isMelee() {
//          return melee;
//       }
//

       public void update(){
          //update position
          setNextPosition();
          checkCollision();
          setPosition(xtemp,ytemp);

          //set animation

           if(dy<0){
              if(currentAct!=JUMP){
                 currentAct=JUMP;
                 animation.setFrames(sprites.get(JUMP));
                 animation.setDelay(-1);
                 width=32;
              }
           }

           else if(left||right){
              if(currentAct!=RUN){
                 currentAct=RUN;
                 animation.setFrames(sprites.get(RUN));
                 animation.setDelay(40);
                 width=32;
              }
           }
           else{
              if(currentAct!=IDLE){
                 currentAct=IDLE;
                 animation.setFrames(sprites.get(IDLE));
                 animation.setDelay(400);
                 width=32;
              }
           }
           animation.update();
           if(currentAct==RUN){
              if(right) facingRight=true;
              if(left) facingRight=false;
           }
       }

       public void setNextPosition() {


          //move normal
          if (left) {
             dx -= moveSpeed;
             if(dx < -maxSpeed) dx = -maxSpeed;
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
//          if ( !(jumping || falling)) {
//             dx = 0;
//          }

       }
    }