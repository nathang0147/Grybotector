package Entity;

import TileMap.Tile;
import TileMap.TileMap;
import UserInterface.GamePanel;
import java.awt.*;

public abstract class MapObject {

  // position and move
  protected double x;
  protected double y;
  protected double dx;
  protected double dy;
  protected int xmap, ymap;

  // dimension
  protected int height;
  protected int width;

  // collision box
  protected int cheight;
  protected int cwidth;
  // collision
  protected double ynext;
  protected double xnext;
  protected double xtemp;
  protected double ytemp;
  protected int currentCol;
  protected int currentRow;
  protected boolean topLeft;
  protected boolean topRight;
  protected boolean botLeft;
  protected boolean botRight;

  // tiles
  protected TileMap tileMap;
  protected int tileSize;
  protected Tile tile;

  // Move
  protected double moveSpeed;
  protected double sneakySpeed;
  protected double maxSpeed;
  protected double stopSpeed;
  protected double slowFall;
  protected double maxFall;
  protected double fallSpeed;
  protected double jumpStart;
  protected double stopJump;

  // animation
  protected int currentAct;
  protected int previousAct;
  protected Animation animation;
  protected boolean facingRight;
  // movement
  protected boolean jumping;
  protected boolean falling;
  protected double stopJumpSpeed;
  protected boolean up, down, right, left;

  public MapObject(TileMap tm) {
    tileMap = tm;
    tileSize = tm.getTilesize();
  }

  public boolean intersect(MapObject o) {
    Rectangle r1 = newRec();
    Rectangle r2 = o.newRec();
    return r1.intersects(r2);
  }

  public Rectangle newRec() {
    return new Rectangle((int) x - cwidth, (int) y - cheight, cwidth, cheight);
  }

  public void calculateConner(double x, double y) {
    int leftSide = (int) (x - cwidth / 2.2) / tileSize;
    int rightSide = (int) (x + cwidth / 2.2 - 1) / tileSize;
    int topSide = (int) (y - cheight / 2.75) / tileSize;
    int botSide = (int) (y + cheight / 2 - 1) / tileSize;

    int tl = tileMap.getTileType(topSide, leftSide);
    int tr = tileMap.getTileType(topSide, rightSide);
    int bl = tileMap.getTileType(botSide, leftSide);
    int br = tileMap.getTileType(botSide, rightSide);

    topLeft = tl == Tile.BLOCK;
    topRight = tr == Tile.BLOCK;
    botLeft = bl == Tile.BLOCK;
    botRight = br == Tile.BLOCK;
  }

  public void checkCollision() {
    currentCol = (int) x / tileSize;
    currentRow = (int) y / tileSize;

    xtemp = x;
    ytemp = y;

    xnext = x + dx;
    ynext = y + dy;

    calculateConner(x, ynext);
    if (dy > 0) {

      if (botLeft || botRight) {
        dy = 0;
        ytemp = (currentRow + 1) * tileSize - cheight / 2;
        falling = false;
      } else {
        ytemp += dy;
      }
    }

    if (dy < 0) {
      if (topLeft || topRight) {
        dy = 0;
        ytemp = currentRow * tileSize + cheight / 2;
      } else {
        ytemp += dy;
      }
    }

    calculateConner(xnext, y);
    if (dx > 0) {
      if (botRight || topRight) {
        dx = 0;
        xtemp = (currentCol + 1) * tileSize - cwidth / 2;
      } else {
        xtemp += dx;
      }
    }
    if (dx < 0) {
      if (botLeft || topLeft) {
        dx = 0;
        xtemp = currentCol * tileSize + (cwidth - 8) / 2;
      } else {
        xtemp += dx;
      }
    }

    if (!falling) {
      calculateConner(x, ynext + 1);
      if (!botLeft && !botRight) {
        falling = true;
      }
    }
  }

  public int getCheight() {
    return cheight;
  }

  public int getCwidth() {
    return cwidth;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public double getX() {
    return (int) x;
  }

  public double getY() {
    return (int) y;
  }

  public void setPosition(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public void setVector(double dx, double dy) {
    this.dx = dx;
    this.dy = dy;
  }

  public void setMovement() {
    x += dx;
    y += dy;
  }

  public void setMapPosition() {
    xmap = tileMap.getX();
    ymap = tileMap.getY();
  }

  public void setJumping(boolean jumping) {
    this.jumping = jumping;
  }

  public void setUp(boolean up) {
    this.up = up;
  }

  public void setDown(boolean down) {
    this.down = down;
  }

  public void setRight(boolean right) {
    this.right = right;
  }

  public void setLeft(boolean left) {
    this.left = left;
  }

  public boolean notOnScreen() {
    return x + width + xmap < 0
        || x + xmap - width > GamePanel.WIDTH
        || y + height + ymap < 0
        || y + ymap - height > GamePanel.HEIGHT;
  }

  public void draw(Graphics2D g) {
    if (facingRight) {
      g.drawImage(
          animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2), null);
    } else {
      g.drawImage(
          animation.getImage(),
          (int) (x + xmap - width / 2 + width),
          (int) (y + ymap - height / 2),
          -width,
          height,
          null);
    }
  }
}
