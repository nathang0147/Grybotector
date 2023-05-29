package Enemies;

import Entity.Animation;
import Entity.Enemy;
import Entity.Player;
import TileMap.TileMap;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Enemy1 extends Enemy {
  private ArrayList<BufferedImage[]> sprites;
  private int[] numFrames = {10, 10};
  private int currentAction;
  private int RUN = 0;
  private int ATK = 1;

  private boolean isAttack = true;

  private ArrayList<Magicbutt> magicbutts;
  private int dame, damageCost, buttNum, maxButt;

  public Enemy1(TileMap tm) {
    super(tm);

    moveSpeed = 0.3;
    maxSpeed = 0.4;
    fallSpeed = 0.2;
    maxFall = 10.0;

    width = 62;
    height = 84;
    cheight = 20;
    cwidth = 20;

    health = maxHealth = 8;
    damage = 1;
    buttNum = maxButt = 600;
    damageCost = 10;
    dame = 1;

    magicbutts = new ArrayList<Magicbutt>();

    try {
      // load sprites
      BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Boss/Boss_1.png"));
      sprites = new ArrayList<BufferedImage[]>();
      for (int i = 0; i < 2; i++) {

        BufferedImage[] bi = new BufferedImage[numFrames[i]];

        for (int j = 0; j < numFrames[i]; j++) {
          if (i == 0) {
            bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
          } else {
            bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
          }
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

    right = true;
    facingRight = true;
  }

  private void getNextPosition() {
    if (left) {
      dx -= moveSpeed;
      // Update movement
      if (dx < -maxSpeed) dx = -maxSpeed;
    } else if (right) {
      dx += moveSpeed;
      if (dx > maxSpeed) dx = maxSpeed;
    }
    if (falling) {
      dy += fallSpeed;
    }
  }

  public void update(Player player) {
    getNextPosition();
    checkCollision();
    setPosition(xtemp, ytemp);
    if (System.nanoTime() % 20000 == 0) {
      isAttack = true;
    } else {
      isAttack = false;
    }

    buttNum += 1;

    if (buttNum > maxButt) buttNum = maxButt;
    if (!notOnScreen() && isAttack) {
      if (buttNum > damageCost) {
        buttNum -= damageCost;
        Magicbutt bl = new Magicbutt(tileMap, facingRight);
        bl.setPosition(x + 1, y - 1);
        magicbutts.add(bl);
      }
    }
    for (int i = 0; i < magicbutts.size(); i++) {
      magicbutts.get(i).update(player);
      if (magicbutts.get(i).shouldRemove()) {
        magicbutts.remove(i);
        i--;
      }
    }

    if (flinching) {
      long elapse = (System.nanoTime() - flinchedTime) / 1000000;
      if (elapse > 400) {
        flinching = false;
      }
    }

    if (!notOnScreen()) {
      if (currentAction != ATK) {
        currentAction = ATK;
        animation.setFrames(sprites.get(currentAction));
        animation.setDelay(400);
      }

    } else if (notOnScreen()) {
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

    } else if (left && dx == 0) {
      right = true;
      left = false;
      facingRight = true;
    }
    animation.update();
  }

  public void draw(Graphics2D g) {
    //        if(notOnScreen()) return;
    setMapPosition();
    super.draw(g);
    for (int i = 0; i < magicbutts.size(); i++) {
      magicbutts.get(i).draw(g);
    }
  }

  public void checkAttackEnemy(Player player) {
    for (int i = 0; i < magicbutts.size(); i++) {
      if (magicbutts.get(i).intersect(player)) {
        player.hitDamage(1);
        magicbutts.get(i).setHit();
        break;
      }
    }
  }
}
