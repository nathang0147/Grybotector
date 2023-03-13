package TileMap;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage img;
    public int type;
    public final static int NORMAL=0;
    public  final static  int BLOCK=1;

    public Tile(BufferedImage img, int type) {
        this.img = img;
        this.type = type;
    }

    public BufferedImage getImg() {
        return img;
    }

    public int getType() {
        return type;
    }

}
