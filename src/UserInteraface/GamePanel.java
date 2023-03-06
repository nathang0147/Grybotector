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
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    public final static int BOARD_WIDTH=320;
    public final static int BOARD_HEIGHT=240;
    public static final int SCALE = 2;

    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTinme = 1000 / FPS;

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

    private void init() {

            image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            g = (Graphics2D) g;

        running = true;

    }


    private void update() {

    }

    private void draw() {

    }

    private void drawtoScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
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
        init();

        long start;
        long elapsed;
        long wait;

        while(running) {

            start = System.nanoTime();
            update();
            draw();
            drawtoScreen();

            elapsed = System.nanoTime() - start;

            wait = targetTinme - elapsed - 1000000;

            try {
                Thread.sleep(wait);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}


