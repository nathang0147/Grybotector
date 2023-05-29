package Image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {
  public static final String MENU_BUTTONS = "ui_res/rsz_button_atlas.png";
  public static final String MENU_BACKGROUND = "GameUI/holder.png";

  public static BufferedImage GetSpriteAtlas(String fileName) {
    BufferedImage img = null;
    InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
    try {
      img = ImageIO.read(is);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return img;
  }
}
