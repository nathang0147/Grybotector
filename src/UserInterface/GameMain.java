package UserInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class GameMain {
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame ("Grybotector");
        File f= new File("D:\\JAVA\\PROJECT\\IU_Grybotector\\Resources\\Icon\\army (1).png");
        BufferedImage img= ImageIO.read(f);
        window.setIconImage(img);
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
