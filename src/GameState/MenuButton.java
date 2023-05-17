package GameState;

import UserInterface.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton {
    private int xPos, yPos, index;
    private int xOffsetCenter = 50 / 2;

    private boolean mouseOver, mousePressed;
    private Rectangle bounds;
    public MenuButton(int xPos, int yPos, int rowIndex) {
        this.xPos = xPos;
        this.yPos = yPos;
        initBounds();
    }
    private void initBounds() {
        bounds = new Rectangle(xPos , yPos, 50,30);

    }

    //Lúc đưa chuột vào xong nhấn thì sẽ có hoạt hoạ
    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    //reset hoạt hoạ
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

}
