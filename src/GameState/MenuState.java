package GameState;

import Sound.ThemeSong;
import TileMap.Background;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

    private Background bg;
    private int currentChoice = 0;
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
            bg = new Background("/Icon/IU.jpg", 0.5);
            bg.setVector(-1, 0);

            titleColor = new Color(200, 0, 0);
            titleFont = new Font("EightBit", Font.CENTER_BASELINE,25 );

            font = new Font("EightBit", Font.PLAIN, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filePath = "/Sound/ThemeSong.wav";
        new ThemeSong(filePath);

    }

    public void init() {
    }

    public void update() {
        //draw bg
        bg.update();
    }

    public void draw(Graphics2D g) {
        bg.draw(g);

        //draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("GRYBOTECTOR", 80, 70);
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i], 145, 140 + i * 30);
        }
    }

    private void select() {
        if (currentChoice == 0) {
            gsm.setState(2);
        }
        if (currentChoice == 1) {
            //help
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
    public void keyReleased(int k){}


}
