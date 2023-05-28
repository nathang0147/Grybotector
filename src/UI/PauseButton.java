package UI;

import java.awt.*;

public class PauseButton {
    protected  int x,y,width,height;
    protected Rectangle bounds;
    public PauseButton(int x, int y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        createBounds();
    }
    public  void createBounds(){
        bounds= new Rectangle(x,y,width,height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}