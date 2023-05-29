package Attack;

import Entity.Animation;
import Entity.MapObject;
import TileMap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends MapObject {
    
    

    private boolean hit;
    private boolean remove;


    public Bullet(TileMap tm, boolean right) {
        super(tm);
        cheight = 14;
        cwidth = 14;
        height = 16;
        width = 16;
        moveSpeed = 3.0;

        if(right) dx = moveSpeed;
        else dx = - moveSpeed;



        //fireball tile

        //Load image
        animation = new Animation();

    }

    public void isHit(){
        if(hit) return;
        hit = true;
        //Animation
    }

    public boolean shouldRemove(){return remove;}

    public void update(){
        checkCollision();
        setPosition(xtemp, ytemp);

        animation.update();
        if(hit&& !remove){
            remove = true;
        }
    }

    public void draw(Graphics2D g){
        setMapPosition();

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
                    -width-25,
                    height,
                    null
            );

        }
    }
}
