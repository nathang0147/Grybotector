package Enemies;

import Entity.Animation;
import Entity.MapObject;
import Entity.Player;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Blades extends MapObject {
    private BufferedImage [] sprites;
    private boolean hit;
    private boolean remove;
    private double xStart, yStart, xGoal, yGoal;



    public Blades(TileMap tm, boolean right) {
        super(tm);

        moveSpeed = 2;

        width = 17;
        height = 17;
        cheight = 10;
        cwidth = 10;

        //logic

        //load sprite
        try {
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/Bullet/bullet4.png"));
            sprites = new BufferedImage[7];
            for (int i = 0; i < sprites.length; i++) {
                sprites[i] = spriteSheet.getSubimage(i * width, 0, width, height);
            }
            animation = new Animation();
            animation.setFrames(sprites);
            animation.setDelay(60);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setPath(boolean right){

        double xPath = Math.abs(xGoal - xStart);
        double yPath = Math.abs(yGoal - yStart);
        if(right) {
            dx = (xPath / 10) + 3;
            dy = (yPath / 10) + 3;
        }else {
            dx = - ((xPath / 10) + 3);
            dy = - ((yPath / 10) + 3);
        }
    }

    public void getNextPosition(Enemy1 e, Player player){
        xStart = e.getX();
        yStart = e.getY();

        xGoal = player.getX();
        yGoal = player.getY();

        //for x axis
        if(x == xGoal){
            dx = 0;
        }


    }

    public void setHit() {
        if (hit) return;
        hit = true;
    }

    public void update(Enemy1 e, Player player){
        getNextPosition(e, player);
        if(!notOnScreen()){
            checkCollision();
            setPosition(xtemp,ytemp);

            animation.update();
            if(hit && animation.hasPlayedOnece()){
                remove = true;
            }

        }
    }
    public void draw(Graphics2D g){
        setMapPosition();
        super.draw(g);
    }
}
