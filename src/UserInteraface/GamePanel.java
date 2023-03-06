package UserInteraface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    public final static int BOARD_WIDTH=650;
    public final static int BOARD_HEIGHT=612;
    public static final int SCALE = 2;

    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTinme = 1000/60;

    private BufferedImage image;
    private Graphics2D g;

    public GamePanel() throws IOException {
        // set cái màn hình chơi mốt có code thfi xài Board_width với lại Board_height nhó
        super();
        setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }
    // hàm để chia frame bên trái là khu vực chơi còn bên phaải là hình tĩnh
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.black);
        // giới hạn của màn hình chơi
        g.drawRect(0,0,BOARD_WIDTH,BOARD_HEIGHT);
        // giới hạn cuủa hình nền
        g.drawRect(650,0,350,612);

    }

    public void init() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    // hàm để chạy thread Long chưa đụng đến nhưng mà insert sẵn
    @Override
    public void run() {
    }
}


