import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class GamePanel extends JPanel implements Runnable {
   
    public final int originalTileSize = 32;
    public final int scale = 2;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 10;
    public final int maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    private Thread thread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); 
        this.setDoubleBuffered(true);
        startGameThread();
    }

    private void startGameThread() {
        thread = new Thread(this);
        thread.start();
        System.out.println("Game thread started...");
    }

    @Override
    public void run() {
        update();
        repaint();
    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.dispose();
    }

}
