package Enemies;

import Entity.Animation;
import Entity.Enemy;
import TileMap.TileMap;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Enemy3 extends Enemy {
  private BufferedImage[] sprites;

  public Enemy3(TileMap tm) {

    super(tm);

    moveSpeed = 0.3;
    maxSpeed = 0.3;
    fallSpeed = 0.2;

    width = 30;
    height = 30;
    cwidth = 20;
    cheight = 20;

    health = maxHealth = 2;
    damage = 1;

    // load sprites
    try {

      BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Boss/slugger.gif"));

      sprites = new BufferedImage[3];
      for (int i = 0; i < sprites.length; i++) {
        sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    animation = new Animation();
    animation.setFrames(sprites);
    animation.setDelay(300);

    right = true;
    facingRight = true;
  }

  private void getNextPosition() {

    // movement
    if (left) {
      dx -= moveSpeed;
      if (dx < -maxSpeed) {
        dx = -maxSpeed;
      }
    } else if (right) {
      dx += moveSpeed;
      if (dx > maxSpeed) {
        dx = maxSpeed;
      }
    }

    // falling
    if (falling) {
      dy += fallSpeed;
    }
  }

  public void update() {

    // update position
    getNextPosition();
    checkCollision();
    setPosition(xtemp, ytemp);

    // check flinching
    if (flinching) {
      long elapsed = (System.nanoTime() - flinchedTime) / 1000000;
      if (elapsed > 400) {
        flinching = false;
      }
    }

    // if it hits a wall, go other direction
    if (right && dx == 0) {
      right = false;
      left = true;
      facingRight = false;
    } else if (left && dx == 0) {
      right = true;
      left = false;
      facingRight = true;
    }

    // update animation
    animation.update();
  }

  public void draw(Graphics2D g) {

    setMapPosition();

    super.draw(g);
  }
}
