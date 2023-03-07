package UserInterface;

import javax.swing.*;
import java.awt.*;

public class GameMain {
    public static void main(String[] args) {
        JFrame window = new JFrame ("Grybotector");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
