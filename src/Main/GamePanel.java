package Main;

import Entity.Player;
import Main.Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    final public int tilesSize = originalTileSize * scale;
    final public int maxScreenCol = 16;
    final public int maxScreenRow = 12;
    final public int screenWidth = tilesSize * maxScreenCol;
    final public int screenHeight = tilesSize * maxScreenRow;

    final public int maxWorldCol = 50;
    final public int maxWorldRow = 50;
    final public int worldWidth = tilesSize * maxWorldCol;
    final public int maxWorldHeight = tilesSize * maxWorldRow;

    int framesPerSecond = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /framesPerSecond;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.udpate();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }

}
