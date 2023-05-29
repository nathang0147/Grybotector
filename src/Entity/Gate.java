package Entity;

import TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Gate extends  MapObject{

    
    private BufferedImage[]  sprite;
    private   boolean isNext=false;
    public Gate(TileMap tm) {
        super(tm);
        width= 100;
        height=100;
        cheight=30;
        cwidth=30;

        //load sprite
        try{
            BufferedImage spritesheet= ImageIO.read(getClass().getResourceAsStream("/assets/transition.png"));
            sprite= new BufferedImage[8];
            for (int i = 0; i < 8; i++) {
                sprite[i]= spritesheet.getSubimage(i*width,0,width,height);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        animation= new Animation();
        animation.setFrames(sprite);
        animation.setDelay(70);
    }
    public  void draw(Graphics2D g){
        setMapPosition();
        super.draw(g);
    }
    public  void update(){
        checkCollision();
        setPosition(xtemp,ytemp);
        animation.update();

    }
    public void checkState(Player player){
        if(this.intersect(player)){
            this.isNext=true;
        }
    }

}
