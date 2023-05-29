package TileMap;

import java.awt.image.BufferedImage;

public class Tile {
  public BufferedImage img;
  public int type;
  // tile Types: 1 Block ( vẽ ) 0 normal ( Ko vẽ)
  public static final int NORMAL = 0;
  public static final int BLOCK = 1;

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
