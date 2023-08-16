import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

public class TileManager {

    private GamePanel gp;
    private Tile[] tiles;
    private Wall wall;
    private boolean[][] drawnTreasureTiles;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tiles = new Tile[9];

        getTileImage();
    }

    public void getTileImage() {
        try {
            File file;
            FileInputStream fs;

            // Wall Tiles

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

            file = new File("../res/dirt_layer_blank.png");
            fs = new FileInputStream(file);
            tiles[3] = new Tile("Blank Dirt Layer");
            tiles[3].image = ImageIO.read(fs);
            
            // Treasures
            
            file = new File("../res/heart_scale.png");
            fs = new FileInputStream(file);
            tiles[4] = new Tile("Heart Scale");
            tiles[4].image = ImageIO.read(fs);

            file = new File("../res/small_pale_sphere.png");
            fs = new FileInputStream(file);
            tiles[5] = new Tile("Small Pale Sphere");
            tiles[5].image = ImageIO.read(fs);
            
            file = new File("../res/large_pale_sphere.png");
            fs = new FileInputStream(file);
            tiles[6] = new Tile("Large Pale Sphere");
            tiles[6].image = ImageIO.read(fs);

            file = new File("../res/small_blue_sphere.png");
            fs = new FileInputStream(file);
            tiles[7] = new Tile("Small Blue Sphere");
            tiles[7].image = ImageIO.read(fs);

            file = new File("../res/large_blue_sphere.png");
            fs = new FileInputStream(file);
            tiles[8] = new Tile("Large Blue Sphere");
            tiles[8].image = ImageIO.read(fs);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        
        wall = Game.getCurrentWall();

        int tileSize = GamePanel.getTileSize();
        
        //We draw the treasures first to place them "underneath" the wall when it gets drawn after this
        drawTreasures(g2, tileSize);
        
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
                        if (!wall.getTreasureLayer().containsTreasure(row, col)) {
                            g2.drawImage(tiles[3].image, x, y, tileSize, tileSize, null);
                        }
                }
            }
        } 
    }
    
    /*
     * Draws the treasures "underneath the wall"
     */
    private void drawTreasures(Graphics2D g2d, int tileSize) {
        Treasures treasure;
        drawnTreasureTiles = new boolean[Wall.WALL_HEIGHT][Wall.WALL_WIDTH];
        for (boolean[] row : drawnTreasureTiles) {
            Arrays.fill(row, false);
        }

        for (int row = 0, y = 64; row < Wall.WALL_HEIGHT; row++, y += tileSize) {
            for (int col = 0, x = 0; col < Wall.WALL_WIDTH; col++, x += tileSize) {
                treasure = wall.getTreasureLayer().getTreasure(row, col);
                if (treasure == null || drawnTreasureTiles[row][col] == true) {
                    //no treasure to draw here or a treasure is already drawn, continue the loop
                    continue;
                }

                switch (treasure.getName()) {
                    case "Heart Scale":
                        g2d.drawImage(tiles[4].image, x, y, tileSize, tileSize, null);
                        drawnTreasureTiles[row][col] = true;
                        break;

                    case "Small Pale Sphere":
                        g2d.drawImage(tiles[5].image, x, y, tileSize * treasure.getWidth(), tileSize * treasure.getHeight(), null); 
                        markTilesAsDrawn(false, row, col); 
                        break;
                    
                    case "Large Pale Sphere":
                        g2d.drawImage(tiles[6].image, x, y, tileSize * treasure.getWidth(), tileSize * treasure.getHeight(), null); 
                        markTilesAsDrawn(true, row, col); 
                        break;

                    case "Small Blue Sphere":
                        g2d.drawImage(tiles[7].image, x, y, tileSize * treasure.getWidth(), tileSize * treasure.getHeight(), null);
                        markTilesAsDrawn(false, row, col); 
                        break;

                    case "Large Blue Sphere":
                        g2d.drawImage(tiles[8].image, x, y, tileSize * treasure.getWidth(), tileSize * treasure.getHeight(), null); 
                        markTilesAsDrawn(true, row, col); 
                        break;
                    default:
                }
            }
        }
    }
    
    private void markTilesAsDrawn(boolean treasureLarge, int row, int col) {

        if (!treasureLarge) {
            drawnTreasureTiles[row][col] = true;
            drawnTreasureTiles[row + 1][col + 1] = true;
            drawnTreasureTiles[row + 1][col] = true;
            drawnTreasureTiles[row][col + 1] = true;
        }
        else {
            drawnTreasureTiles[row][col] = true;
            drawnTreasureTiles[row + 1][col + 1] = true;
            drawnTreasureTiles[row + 1][col] = true;
            drawnTreasureTiles[row][col + 1] = true;
            drawnTreasureTiles[row + 2][col + 2] = true;
            drawnTreasureTiles[row + 2][col] = true;
            drawnTreasureTiles[row][col + 2] = true;
            drawnTreasureTiles[row + 1][col + 2] = true;
            drawnTreasureTiles[row + 2][col + 1] = true;
        }
    }
}
