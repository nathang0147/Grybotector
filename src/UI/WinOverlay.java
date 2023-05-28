package UI;

import UserInterface.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class WinOverlay {
    public BufferedImage backGroundImg;
    private  int bgX, bgY,bgW,bgH;
    public WinOverlay(){
        //load sprites

        try {
            BufferedImage background= ImageIO.read(getClass().getResourceAsStream("/GameUI/completed.png"));
            bgW=124;
            bgH=41;
            bgX= GamePanel.WIDTH/2-50;
            bgY=GamePanel.HEIGHT/2;
            backGroundImg=background.getSubimage(0,0,124,41);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void update(){

    }
    public  void draw(Graphics g){
        g.drawImage(backGroundImg,bgX,bgY,null);

    }
}
