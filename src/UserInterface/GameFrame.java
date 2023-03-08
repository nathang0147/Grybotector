package UserInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JFrame {
    public final static int WIDTH = 500;
    public final static int HEIGHT = 306;
    public static int SCALE = 2;
    BufferedImage img;
    Image image;
    Graphics g;

    public GameFrame() throws IOException {
        // lấy cái icon trên cái thanh nhó
        img = ImageIO.read(getClass().getResourceAsStream("/icons/army1.png"));
        GamePanel gp = new GamePanel();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        add(gp);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Grybotector");
        setIconImage(img);
        loadImage();

        setLocationRelativeTo(null);
        setVisible(true);

    }
    // hàm này để load cái image để vẽ cái nền bên phải
    public void loadImage() throws IOException {
        File f= new File("Resources/icons/th1.png");
        image=ImageIO.read(f);
    }
    // hàm này để vẽ hình nền bên trái
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(image,650,0,this);
    }


}
