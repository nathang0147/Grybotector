package GameState;

import Image.LoadSave.*;
import Sound.ThemeSong;
import TileMap.Background;
import UserInterface.GamePanel;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MenuState extends GameState {

    private Background bg;
    private int currentChoice = 0;
    private BufferedImage holder, title;

    private MenuButton[] buttons = new MenuButton[3];
    private final String[] options = {
            "Start",
            "Option",
            "Quit"
    };

    private Color titleColor;
    public Font titleFont;

    private Font font;
    private ThemeSong audioInput;


    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;

        try {
            title = ImageIO.read(getClass().getResourceAsStream("/GameUI/Grybotector.png"));
            holder = ImageIO.read(getClass().getResourceAsStream("/GameUI/holder.png"));
            bg = new Background("/Icon/Background.png", 0.5);
            //bg.setVector(-1, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filePath = "/Sound/ThemeSong.wav";
        new ThemeSong(filePath);
        loadButtons();
    }

    public void init() {
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(GamePanel.WIDTH/2-9, 130 + 0 * 30, 0);
        buttons[1] = new MenuButton(GamePanel.WIDTH/2-9, 130 + 1 * 30, 1);
        buttons[2] = new MenuButton(GamePanel.WIDTH/2-9, 130 + 2 * 30, 2);
    }

    public void update() {
        //draw bg
        bg.update();
        for (MenuButton mb : buttons)
            mb.update(currentChoice);
    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        g.drawImage(title, 50, 20, 225, 56, null);
        g.drawImage(holder, 116, 125, 90 , 100 , null);

        //draw title

        //g.drawString("GRYBOTECTOR", 80, 70);
//        g.setFont(font);
//        for (int i = 0; i < options.length; i++) {
//            if (i == currentChoice) {
//                g.setColor(Color.YELLOW);
//            } else {
//                g.setColor(Color.RED);
//            }
//            g.drawString(options[i], 145, 140 + i * 30);
//        }

        for (MenuButton mb : buttons)
            mb.draw(g);
    }

    private void select() {

            if (currentChoice == 0) {
                gsm.setState(1);
            }
            if (currentChoice == 1) {
                //help
                gsm.setState(3);

            }
            if (currentChoice == 2) {
                System.exit(0);
            }

    }

    public void keyPressed(int k) {

            if (k == KeyEvent.VK_ENTER) {
                select();
            }
            if (k == KeyEvent.VK_UP) {
                currentChoice--;
                if (currentChoice == -1) {
                    currentChoice = options.length - 1;
                }
            }
            if (k == KeyEvent.VK_DOWN) {
                currentChoice++;
                if (currentChoice == options.length) {
                    currentChoice = 0;
                }
            }


    }
    public void keyReleased(int k){

    }

    private void resetButtons() {
        for (MenuButton mb : buttons)
            mb.resetBools();

    }

}
