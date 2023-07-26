import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;

public class TileManager {

    private GamePanel gp;
    private Tile[] tiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tiles = new Tile[3];

        getTileImage();
    }

    public void getTileImage() {
        try {
            File file;
            FileInputStream fs;

            file = new File("../res/top_layer.png");
            fs = new FileInputStream(file);
            tiles[0] = new Tile("Top Layer");
            tiles[0].image = ImageIO.read(fs);
            
            file = new File("../res/middle_layer.png");
            fs = new FileInputStream(file);
            tiles[1] = new Tile("Middle Layer");
            tiles[1].image = ImageIO.read(fs);
            
            file = new File("../res/bottom_layer.png");
            fs = new FileInputStream(file);
            tiles[2] = new Tile("Bottom Layer");
            tiles[2].image = ImageIO.read(fs);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(tiles[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[1].image, 100, 100, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tiles[2].image, 200, 200, gp.tileSize, gp.tileSize, null);
    }
}

