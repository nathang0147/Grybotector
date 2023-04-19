package Entity;

import TileMap.TileMap;
import TileMap.Tile;
import UserInterface.GamePanel;

import java.awt.*;

public abstract class MapObject {
    //position and move
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;
    protected int xmap,ymap;

    //dimension
    protected int height;
    protected int width;

    //collision
    protected boolean topLeft;
    protected boolean topRight;
    protected boolean botLeft;
    protected boolean botRight;
    protected int cheight;
    protected int cwidth;
    protected double ynext;
    protected double xnext;
    protected double xtemp;
    protected double ytemp;
    protected int currentCol;
    protected int currentRow;

    // tiles
    protected TileMap tileMap;
    protected int tileSize;
    protected Tile tile;

    //Move
    protected double moveSpeed;
    protected double sneakySpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double slowFall;
    protected double maxFall;
    protected int fallSpeed;
    protected double jumpStart;
    protected boolean falling;
    //animation
    protected boolean facingRight;
    protected boolean jumping;
    protected boolean up, down, right, left;


    public MapObject(TileMap tm) {
        tileMap = tm;
        tileSize = tm.getTilesize();
    }

    public boolean intersect(MapObject o){
        Rectangle r1 = newRec();
        Rectangle r2 = o.newRec();
        return r2.intersects(r1);
    }

    public Rectangle newRec(){
        return new Rectangle((int)x - cwidth,
                (int) y - cheight,
                cwidth,
                cheight);
    }

    public void calculateConner(double x, double y){
        int leftSide = (int) (x - cwidth / 2)/tileSize;
        int rightSide = (int) (x + cwidth/ 2) / tileSize;
        int topSide  = (int) (y - cheight/ 2) /tileSize;
        int botSide = (int) (y + cheight /2 ) /tileSize;

        topLeft = tile.BLOCK ==  tileMap.getTileType(topSide,leftSide);
        topRight =tile.BLOCK ==  tileMap.getTileType(topSide,rightSide);
        botLeft = tile.BLOCK == tileMap.getTileType(botSide, leftSide);
        botRight =tile.BLOCK ==  tileMap.getTileType(botSide, rightSide);
    }

    public void checkCollision(){
        currentCol = (int) x/tileSize;
        currentRow = (int) y/tileSize;

        xtemp = x;
        ytemp = y;

        xnext = x+dx;
        ynext = y + dy;

        calculateConner(x, ynext);
        if(dy > 0) {
            if (botLeft || botRight) {
                dy = 0;
                falling = false;
                ynext = (currentRow+1) * tileSize - cheight / 2;
            } else {
                ynext += dy;
            }
        }

        if(dy < 0){
            if (topLeft || topRight) {
                dy = 0;
                ynext = (currentRow) * tileSize + cheight/2;
            }

        }

        calculateConner(xnext, y);
        if(dx > 0){
            if(botRight|| topRight){
                dx = 0;
                xnext=  (currentCol+1) * tileSize - cwidth/2;
            }else {
                xnext += dx;
            }
        }
        if(dx < 0){
            if(botRight|| topRight){
                dx = 0;
                xnext=  currentCol * tileSize + cwidth/2;
            }
        }
        if (!falling){
            calculateConner(x,ynext+1);
            if(!(botLeft || botRight)){
                ynext = (currentRow + 1) * tileSize +cheight/2;
                falling=true;
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
        return (int)x;
    }

    public double getY() {
        return (int)y;
    }

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }
    public void setVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }
    public void setMovement(){
        x += dx;
        y += dy;
    }
    public void setMapPosition(){
        xmap = tileMap.getX();
        ymap = tileMap.getY();
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
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
    public boolean notOnScreen(){
        return x + width + xmap < 0||
                x + xmap - width > GamePanel.WIDTH||
                y + height + ymap < 0||
                y + ymap - height > GamePanel.HEIGHT;
    }

}
