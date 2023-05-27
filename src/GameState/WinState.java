package GameState;

import TileMap.Background;
import UserInterface.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class WinState extends  GameState{
    private Background bg;
    private BufferedImage gameWin;
    public WinState(GameStateManager gsm) {
        this.gsm= gsm;
        init();
    }

    @Override
    public void init() {
        bg= new Background("/Icon/Background.png",0.0);
        try{
           BufferedImage complete= ImageIO.read(getClass().getResourceAsStream("/GameUI/completed.png"));
            gameWin=complete.getSubimage(0,0,124,41);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        g.drawImage(gameWin, GamePanel.WIDTH/2-50,GamePanel.HEIGHT/2,null);

    }

    @Override
    public void keyPressed(int k) {
        if(k== KeyEvent.VK_ESCAPE) gsm.setState(0);

    }

    @Override
    public void keyReleased(int k) {

    }
}
