package UserInterface;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameMain {

  public static void main(String[] args) throws IOException {
    JFrame window = new JFrame("Grybotector");
    BufferedImage img = ImageIO.read(new File("Resources/Icon/fire.png"));
    window.setIconImage(img);
    GamePanel gp = GamePanel.getPanel();
    window.setContentPane(gp);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.pack();
    window.setVisible(true);
  }
}
