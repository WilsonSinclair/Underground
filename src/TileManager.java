import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;

public class TileManager {

    private GamePanel gp;
    private Tile[] tiles;
    private Wall wall;

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
        
        wall = Game.getCurrentWall();
        if (!wall.requiresUpdate()) return;

        int tileSize = GamePanel.getTileSize();
        
        for (int row = 0, y = 64; row < Wall.WALL_HEIGHT; row++, y += tileSize) {
            for (int col = 0, x = 0; col < Wall.WALL_WIDTH; col++, x += tileSize) {
                switch (wall.getRocks()[row][col].getHealth()) {
                    case 3:
                        g2.drawImage(tiles[0].image, x, y, tileSize, tileSize, null);
                        break;
                    case 2:
                        g2.drawImage(tiles[1].image, x, y, tileSize, tileSize, null);
                        break;
                    case 1:
                        g2.drawImage(tiles[2].image, x, y, tileSize, tileSize, null);
                        break;
                    default:
                        break;
                }
            }
        } 
        //mark the wall as updated on the screen so we don't have to keep drawing the same wall over and over.
        wall.setUpdatedStatus(true);
    }
}
