package UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class UrmButton extends  PauseButton{
    private BufferedImage[] imgs;
    private  int rowIndex,index;
    private boolean keyOver, keyPressed;
    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex=rowIndex;
        loadImgs();
    }
    private  void loadImgs(){
        try{
            BufferedImage temp= ImageIO.read(getClass().getResourceAsStream("/ui_res/urm_button.png"));
            this.imgs= new BufferedImage[3];
            for (int i = 0; i < imgs.length; i++) {
                imgs[i]=temp.getSubimage(i*24,rowIndex*24,24,24);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public  void update(int currentChoice){
        if(currentChoice == rowIndex){
            if(keyPressed){
                index = 2;
            }
            else index = 1;
        }
        else index = 0;

    }

    public boolean isKeyOver() {
        return keyOver;
    }

    public void setKeyOver(boolean keyOver) {
        this.keyOver = keyOver;
    }

    public boolean isKeyPressed() {
        return keyPressed;
    }

    public void setKeyPressed(boolean keyPressed) {
        this.keyPressed = keyPressed;
    }

    public  void draw(Graphics g){
        g.drawImage(imgs[index],x,y,24,24,null );
    }

    public  void resetBools(){
        keyOver=false;
        keyPressed=false;
    }

}