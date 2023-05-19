package GameState;

import UserInterface.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import Image.LoadSave;

public class MenuButton {
    private int xPos, yPos, index,rowIndex;
    private int xOffsetCenter = 50 / 2;
    private BufferedImage[] imgs;
    private int B_WIDTH = 420/3;
    private int B_HEIGHT = 168/3;
    private boolean keyOver, keyPressed;
    private Rectangle bounds;
    public MenuButton(int xPos, int yPos, int rowIndex) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        loadImgs();
        initBounds();
    }
    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, 50,30);

    }

    public void loadImgs(){
            imgs = new BufferedImage[3];
            BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
            for(int i = 0; i< 3; i++){
                imgs[i] = temp.getSubimage(i*B_WIDTH, rowIndex*B_HEIGHT, B_WIDTH,B_HEIGHT);
            }
    }

    //Lúc đưa chuột vào xong nhấn thì sẽ có hoạt hoạ
    public void update(int currentChoice) {
        if(currentChoice == rowIndex){
            if(keyPressed){
                index = 2;
            }
            else index = 1;
        }
        else index = 0;
    }


    public void draw(Graphics2D g){g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);}

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

    public Rectangle getBounds() {
        return bounds;
    }

    //reset hoạt hoạ
    public void resetBools() {
        keyOver = false;
        keyPressed = false;
    }

}
