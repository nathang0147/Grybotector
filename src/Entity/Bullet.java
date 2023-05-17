package Entity;

import TileMap.Tile;
import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends MapObject {

    private boolean hit;
    private boolean remove;
    private BufferedImage[] sprites;
    private BufferedImage[] hitSprites;

    public Bullet (TileMap tm, boolean right) {
        super(tm);

        moveSpeed = 3.8;
        if (right) dx = moveSpeed;
        else dx = -moveSpeed;

        width = 6;
        height = 6;
        cwidth = 10;
        cheight = 10;

//        load spirtes
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Bullet/bullet3.png"));
            sprites = new BufferedImage[6];
            for (int i = 0; i < sprites.length; i++) {
                sprites[i] = spritesheet.getSubimage(i * width, 0, width, height * 2);
            }
            hitSprites = new BufferedImage[3];
            for (int i = 0; i < hitSprites.length; i++) {
                hitSprites[i] = spritesheet.getSubimage(i * width, height, width, height);
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
        animation.setFrames(hitSprites);
        animation.setDelay(70);
        dx = 0;
    }
    public boolean shouldRemove() {
        return remove;
    }

    public void update() {
        if (!notOnScreen()) {
            checkCollision();
            setPosition(xtemp, ytemp);


            if (dx == 0 && !hit) {
                setHit();
            }
            animation.update();
            if (hit && animation.hasPlayedOnece()) {
                remove = true;
            }
        }
    }
    public void draw (Graphics2D g) {
        setMapPosition();
        super.draw(g);
    }
}
