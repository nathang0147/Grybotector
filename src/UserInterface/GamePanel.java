package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import GameState.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener {
    //Dimension
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 3;

    //game thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;
    private Timer timer;
    //image
    private BufferedImage image;
    private Graphics2D g;

    //game state manager
    private GameStateManager gsm;
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
//        timer=new Timer(100, (ActionListener) this);
//        timer.start();

    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            addMouseListener(this);
            thread.start();
        }
    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        running = true;

        gsm = new GameStateManager();
    }
    public void run() {
        init();

        long start;
        long elapsed;
        long wait;
        //game loop
        while(running) {
            update();
            draw();
            drawToScreen();

            start = System.nanoTime();
            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;

            try {
                Thread.sleep(wait);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void update() {
        gsm.update();
//        repaint();
    }
    private void draw() {
        gsm.draw(g);
    }
    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0 ,0, WIDTH *SCALE, HEIGHT * SCALE, null);
        g2.dispose();
    }
    public void keyTyped(KeyEvent key) {};
    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    };
    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());
    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {
    }
}

