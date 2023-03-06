package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GameFrame extends JFrame {
    BufferedImage img;
    public GameFrame() throws IOException {
        GamePanel gp= new GamePanel();
        img= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Icon Image/army.png")));
        add(gp);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Grybotector");
        setIconImage(img);
        setVisible(true);
    }

}
