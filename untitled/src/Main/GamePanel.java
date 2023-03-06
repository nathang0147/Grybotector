package Main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public final static int WIDTH=320;
    public final  static int HEIGHT=240;
    public final static  int GAMESCALE=2;

    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH*GAMESCALE,HEIGHT*GAMESCALE));
        setFocusable(true);

    }
}
