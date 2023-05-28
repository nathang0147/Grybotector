package Enemies;

import Entity.Animation;
import Entity.MapObject;
import Entity.Player;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Magicbutt extends MapObject {

    private TileMap tm;
    private boolean hit;
    private boolean remove;
    private BufferedImage[] sprites;
    private BufferedImage[] hitSprites;

    public Magicbutt (TileMap tm, boolean right) {
        super(tm);

        moveSpeed = 2;
        if (right) dx = moveSpeed;
        else dx = -moveSpeed;

        width = 60;
        height = 38;
        cwidth = 10;
        cheight = 10;

//        load spirtes
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Bullet/stone_bullet.png"));
            sprites = new BufferedImage[3];
            for (int i = 0; i < sprites.length; i++) {
                sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
            }
            hitSprites = new BufferedImage[2];
            for (int i = 0; i < hitSprites.length; i++) {
                hitSprites[i] = spritesheet.getSubimage(i * width,0*height, width, height);
            }
            animation = new Animation();
            animation.setFrames(sprites);
            animation.setDelay(70);

        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public void setHit() {
        if (hit) return;
        hit = true;
        System.out.println("HIT!!!!!!!!!!!!!!");
        animation.setFrames(hitSprites);
        animation.setDelay(70);
        dx = 0;


    }
    public boolean shouldRemove() {
        return remove;
    }

    public void update(Player player) {
        if (!notOnScreen()) {
                checkCollision();
                setPosition(xtemp, ytemp);


                if (this.intersect(player) || !hit && dx == 0) {
                    setHit();
                }
                animation.update();
                if (hit && animation.hasPlayedOnece() ) {
                    remove = true;
                }


        }
    }
    public void draw (Graphics2D g) {
        setMapPosition();
        super.draw(g);
    }

}
