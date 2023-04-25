package Entity;

import TileMap.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends MapObject {

   // player stuff
   private int health;
   private int maxHealth;
   private int bullet;
   private int bulletDamage;
   private boolean dead;
   private boolean falling;



   // animations
   private ArrayList<BufferedImage[]> sprites;
   private final int[] numFrames = {3, 8, 2, 5, 2, 6 };

   //act


   // animation actions
   private int currentAct;
   private int IDLE= 3;
   private int RUN = 5;
   private int JUMP = 4;
   private int CROUCH = 0;
   private int FALLING = 2;
   private int DEAD=1;


   public Player(TileMap tm) {
      super(tm);
      System.out.println("Loading");
      // size
      width = 48;
      height = 48;
      cwidth = 30;
      cheight = 30;

      //Move
      moveSpeed = 0.3;
      sneakySpeed = 4 ;
      maxSpeed = 1.6;
      maxFall = 1;
      jumpStart = -5.0;
      fallSpeed = 0.3;
      slowFall = 0.0001;
      stopSpeed = 0.5;
      stopJumpSpeed=0.0001;

      facingRight=true;
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

   public void setGliding(boolean b){
      falling =b;
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
public void getNextPosition() {
//          int doublejump = 0;
   //move normal

   if((dx>0&!botRight)||(dx<0&!botLeft)){
      falling=true;
//      jumping=false;
   }
   if (left) {
      dx -= moveSpeed;
      //Update movement
      if (dx < -maxSpeed) dx = -maxSpeed;
   } else if (right) {
      dx += moveSpeed;
      if (dx > maxSpeed) dx = maxSpeed;
   } else{
      if (dx > 0) {
         dx -= stopSpeed*1.5;
         if (dx < 0) dx = 0;
   } else if (dx < 0) {
         dx += stopSpeed*1.5;
         if (dx > 0) dx = 0;
      }
   }
   System.out.println("dx= "+dx);

   // can move when act
//   if ( !(jumping || falling)) {
//      dx = 0;
//   }
   // jumping
   if(jumping && !falling){
      dy=jumpStart;
      falling=true;
   }
   System.out.println("Falling in Player: "+falling);
   System.out.println("dy="+dy);//falling
   if(falling){
//      System.out.println("dy="+dy);
      if(dy>0||dy==0){
         dy+=fallSpeed*1.2;}
      else dy+=fallSpeed;

      if(dy>0) jumping=false;
      if(dy<0&&!jumping) {
         dy+= stopJumpSpeed;
      }
      //Update movement
      if(dy>=maxFall||dy==0.0) {
         dy=maxFall;
         falling=false;
         jumping=false;
      }

   }

}

   public void update(){
      //update position
      checkCollision();
      getNextPosition();
      setPosition(xtemp,ytemp);
      System.out.println("tl: "+topLeft);
      System.out.println("tr: "+topRight);
      System.out.println("bl: "+botLeft);
      System.out.println("br: "+botRight);
      System.out.println("Right: " +right);
      System.out.println("Left: "+left);
      System.out.println();

      //set animation

      if(dy<0){
         if(currentAct!=JUMP){
            currentAct=JUMP;
            animation.setFrames(sprites.get(JUMP));
            animation.setDelay(-1);
            width=32;
         }
      }
      else if(dy > 0) {
         if(falling) {
            if(currentAct != FALLING) {
               currentAct = FALLING;
               animation.setFrames(sprites.get(FALLING));
               animation.setDelay(100);
               width = 25;
            }
         }
         }
      else if(left||right){
         if(currentAct!=RUN){
            currentAct=RUN;
            animation.setFrames(sprites.get(RUN));
            animation.setDelay(40);
            width=27;
         }
      }
      else if(currentAct!=IDLE){
         currentAct=IDLE;
         animation.setFrames(sprites.get(IDLE));
         animation.setDelay(400);
         width=27;
      }
         animation.update();
         if(currentAct==RUN){
         if(right) facingRight=true;
         if(left) facingRight=false;
      }
      System.out.println("Current Act: " + currentAct);
      System.out.println();
      }
   }


