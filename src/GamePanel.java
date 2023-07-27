import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Color;

public class GamePanel extends JPanel implements Runnable {
   
    public final int originalTileSize = 16;
    public final int scale = 5;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 13;
    public final int maxScreenRow = 10;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    private Thread thread;

    private int FPS = 60;

    private Window window;
    
    private TileManager tileManager;

    public GamePanel(Window window) {
        this.window = window;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); 
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        tileManager = new TileManager(this);

        startGameThread();
    }

    private void startGameThread() {
        thread = new Thread(this);
        thread.start();
        System.out.println("Game thread started...");
    }

    @Override
    public void run() {

        double drawInterval = 1_000_000_000 / FPS;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int frames = 0;


        while (thread != null) {
            currentTime = System.nanoTime();

            deltaTime += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (deltaTime >= 1) {
                update();
                repaint();
                deltaTime--;
                frames++;
            }

            if (timer >= 1_000_000_000) {
                window.setTitle(window.title + " | FPS: " + frames);
                frames = 0;
                timer = 0;
            }
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        tileManager.draw(g2d);

        g2d.dispose();
    }
    
    //For when there is an option in game to change your target FPS
    public void setFPS(int targetFPS) {
        FPS = targetFPS;
    }
}
