package touhou;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * land = 56;
 *
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;
    private Graphics2D windowGraphics;
    private BufferedImage background;
    private BufferedImage pIdle;
    private BufferedImage backbufferedImage;
    private Graphics2D backbufferedGraphics;
    //private pIdleY= 68+ sizeC;
    private int bgX = 0;



    public GameWindow() {
        background = SpriteUtils.loadImage("assets/images/background/Background.png");
        pIdle = SpriteUtils.loadImage("assets/images/players/Idle/Idle__000.png");
        setupGameLoop();
        setupWindow();
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(928, 700);
        this.setTitle("Ninja = Made by vhtbcvt");
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
        bgX -= 1;
    }

    private void render() {
        backbufferedGraphics.setColor(Color.black);
        backbufferedGraphics.fillRect(0,0,getWidth(),getHeight());
        if (bgX<0) bgX+=getWidth();
        backbufferedGraphics.drawImage(background, bgX-getWidth(), -93, null);
        backbufferedGraphics.drawImage(background, bgX, -93, null);
        backbufferedGraphics.drawImage(pIdle, 100, getHeight()-(56+439), null);

        windowGraphics.drawImage(backbufferedImage,0,0,null);
    }
}
