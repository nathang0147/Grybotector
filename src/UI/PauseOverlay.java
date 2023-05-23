package UI;

import UserInterface.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PauseOverlay {
    public BufferedImage backgroundImg;
    private  int bgX, bgY,bgW,bgH;
    private  UrmButton urmButton;
    private UrmButton menuB,replayB,unpauseB;
    public PauseOverlay(){
        //load sprite

        try {
            BufferedImage background= ImageIO.read(getClass().getResourceAsStream("/ui_res/pausemenu1.png"));
            bgW=103;
            bgH=155;
            bgX= GamePanel.WIDTH/2- bgW/2;
            bgY= GamePanel.HEIGHT/2-bgH/2-20;
            backgroundImg= background.getSubimage(0,0,100,155);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int menuX= (int) 120;
        int replayX=120+28;
        int exitX= 120+28*2;
        int By=140;
        menuB= new UrmButton(menuX,By,24,24,2);
        replayB= new UrmButton(replayX,By,24,24,1);
        unpauseB= new UrmButton(exitX,By,24,24,0);
    }
    public void update(int currentChoice){
        menuB.update(currentChoice);
        replayB.update(currentChoice);
        unpauseB.update(currentChoice);

    }
    public  void draw(Graphics g){
        g.drawImage(backgroundImg,bgX,bgY,bgW,bgH,null);
        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);
    }

}