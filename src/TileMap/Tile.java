package TileMap;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage img;
    private int type;
    // tile Types: 1 Block ( vẽ ) 0 normal ( Ko vẽ)
    public final static int BLOCK=1;
    public final static int NORMAL=0;

    public  Tile(BufferedImage img,int type){
        this.img= img;
        this.type=type;

    }
    public BufferedImage getImg(){
        return  img;
    }

    public int getType() {
        return type;
    }
}
