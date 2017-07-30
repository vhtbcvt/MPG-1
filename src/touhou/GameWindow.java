package touhou;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by vhtbcvt on 7/30/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;
    private Graphics2D windowGraphics;
    private BufferedImage background;
    private BufferedImage players;
    private BufferedImage backbufferedImage;
    private Graphics2D backbufferedGraphics;
    private int playersX = 192;
    private int playersY = 650;
    private int backgroundY = 768;
    private int dx ;
    private int dy ;


    public GameWindow() {
        background = SpriteUtils.loadImage("assets/images/background/0.png");
        players = SpriteUtils.loadImage("assets/images/players/straight/1.png");
        setupGameLoop();
        setupWindow();
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(384, 768);
        this.setTitle("Touhou = Remade by vhtbcvt");
        this.setVisible(true);

        this.backbufferedImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        this.backbufferedGraphics = (Graphics2D) this.backbufferedImage.getGraphics();
        this.windowGraphics = (Graphics2D) this.getGraphics();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_RIGHT:
                        dx = 3;
                        break;
                    case KeyEvent.VK_LEFT:
                        dx = -3;
                        break;
                    case KeyEvent.VK_UP:
                        dy = -3;
                        break;
                    case KeyEvent.VK_DOWN:
                        dy = +3;
                        break;
                }

            }
            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_RIGHT:
                        dx = 0;
                        break;
                    case KeyEvent.VK_LEFT:
                        dx = 0;
                        break;
                    case KeyEvent.VK_UP:
                        dy = 0;
                        break;
                    case KeyEvent.VK_DOWN:
                        dy = 0;
                        break;
                }
            }

        });



    }

    public void loop() {
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.currentTimeMillis();
            }
            currentTime = System.currentTimeMillis();
            if (currentTime - lastTimeUpdate > 17) {
                run();
                render();
                lastTimeUpdate = currentTime;
            }
        }
    }

    private void run() {
        playersX+=dx;
        playersY+=dy;
    }

    private void render() {
        backbufferedGraphics.setColor(Color.black);
        backbufferedGraphics.fillRect(0,0,getWidth(),getHeight());
        backbufferedGraphics.drawImage(background,0,backgroundY-3109,null);
        backgroundY+=1;
        backbufferedGraphics.drawImage(players,playersX,playersY,null);

        windowGraphics.drawImage(backbufferedImage,0,0,null);
    }
}
