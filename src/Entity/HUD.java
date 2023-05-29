package Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class HUD {
   private BufferedImage[] sprites;
   private Player player;
   
   private Font font;
   BufferedImage holder = new BufferedImage(128,48,BufferedImage.TYPE_INT_ARGB);


   //dimension
   private int height;
   private int width;


   public HUD(Player p) {
      player = p;
      width = 48;
      height = 17;
      sprites = new BufferedImage[5];

      try {
         holder = ImageIO.read(getClass().getResourceAsStream("/HUD/rsz_1holder.png"));
      } catch (IOException e) {
         e.printStackTrace();
      }

      try {
         BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/HUD/Health.png"));
         int spriteCount = spritesheet.getWidth()/48;
         //System.out.println(spriteCount);
            for(int j = 0; j < spriteCount; j++) {
               sprites[j] = spritesheet.getSubimage(j * width, 0, width, height);
            }
      }
      catch(Exception e) {
         e.printStackTrace();
      }
   }

   public void draw(Graphics2D g) {
      if(player.getHealth()>0) {
         font = new Font("EightBit", Font.BOLD, 10);
         g.drawImage(holder, 10, 10 - 5, null);
         g.drawImage(sprites[player.getHealth()-1], 10, 12 - 5, null);
         g.setFont(font);
         //set color = 0-255-51
         g.setColor(new Color(46, 149, 68));
         g.drawString(
                 player.getHealth() + "/" + player.getMaxHealth(),
                 45 + 10,
                 25 - 5
         );
      }
      else {
         font = new Font("EightBit", Font.BOLD, 10);
         g.drawImage(holder, 10, 10 - 5, null);
         g.drawImage(sprites[0], 10, 12 - 5, null);
         g.setFont(font);
         //set color = 0-255-51
         g.setColor(new Color(46, 149, 68));
         g.drawString(
                 player.getHealth() + "/" + player.getMaxHealth(),
                 45 + 10,
                 25 - 5
         );

      }
   }
   public void update(){

   }
}
