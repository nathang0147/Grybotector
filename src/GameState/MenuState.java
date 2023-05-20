package GameState;

import Sound.ThemeSong;
import TileMap.Background;
import UserInterface.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Struct;

public class MenuState extends GameState {
    private MenuButton[] buttons = new MenuButton[3];
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

        //Background and title
        try {
            bg = new Background("/Icon/IU.jpg", 0.5);
            bg.setVector(-1, 0);

            titleColor = new Color(208, 0, 0);
            titleFont = new Font("Showcard Gothic", Font.CENTER_BASELINE,25 );

            font = new Font("Arial", Font.PLAIN, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filePath = "/Sound/ThemeSong.wav";
        new ThemeSong(filePath);

        //Load image of button
        loadButton();
    }

    public void init() {
    }

    public void update() {
        //draw bg
        bg.update();
        for (MenuButton mb : buttons)
            mb.update(currentChoice);
    }
    private void loadButton(){
        buttons[0] = new MenuButton(GamePanel.WIDTH / 2, (int) (130 + 0 * 30), 0);
        buttons[1] = new MenuButton(GamePanel.WIDTH / 2, (int) (130 + 1 * 30), 1);
        buttons[2] = new MenuButton(GamePanel.WIDTH / 2, (int) (130 + 2 * 30), 2);
    }

    public void draw(Graphics2D g) {
        bg.draw(g);

        //draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Grybotector", 80, 70);
        g.setFont(font);
//        for (int i = 0; i < options.length; i++) {
//            if (i == currentChoice) {
//                g.setColor(Color.YELLOW);
//            } else {
//                g.setColor(Color.RED);
//            }
//            g.drawString(options[i], 145, 140 + i * 30);
//        }

        for (MenuButton mb :
                buttons) {
            mb.draw(g);
        }

    }

    private void select() {
        if (currentChoice == 0) {
            gsm.setState(1);
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
//        for (MenuButton mb :
//                buttons) {
//            if (k == KeyEvent.VK_ENTER) {
//                mb.setKeyPressed(true);
//                break;
//            }
//            if(k == KeyEvent.VK_DOWN){
//                mb.setKeyOver(true);
//                currentChoice++;
//
//            }
//            if(k == KeyEvent.VK_UP){
//                mb.setKeyOver(true);
//                currentChoice--;
//
//            }
//        }
        for (MenuButton mb : buttons)
            mb.setKeyOver(false);
    }
    public void keyReleased(int k){
        for (MenuButton mb :
                buttons) {
            if (mb.isKeyPressed()) {
                select();
            }
        }
    }

    private void resetButtons() {
        for (MenuButton mb : buttons)
            mb.resetBools();

    }

    public String[] getOptions() {
        return options;
    }
}
