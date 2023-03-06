package GameState;

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

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;

        try {
            bg = new Background("/Icon/th.png", 1);
            bg.setVector(-0.5, 0);

            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Snap ITC", Font.PLAIN, 200);

            font = new Font("Showcard Gothic", Font.PLAIN, 20);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        g.drawString("Grybotector", 20, 50);
        g.setFont(titleFont);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setFont(font);
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i], 145, 140 + i * 15);
        }
    }

    private void select() {
        if (currentChoice == 0) {
            //start
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
    public void keyReleased( int k){}
}
