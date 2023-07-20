import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Wall {

    public static final int WALL_HEIGHT = 13;
    public static final int WALL_WIDTH = 10;
    
    private Rock[][] wall;
    private TreasureLayer treasureLayer;

    private ArrayList<Treasures> treasures;
    private Random r;

    private List<Class<? extends Treasures>> possibleTreasures = new ArrayList<Class<? extends Treasures>>();
    
    //Upon creation, creates a new randomly generated Wall.
    public Wall() {
        r = new Random();
        treasureLayer = new TreasureLayer();
        wall = generateNewWall();
        
        populatePossibleTreasures();
        generateTreasures();
        addTreasuresToTreasureLayer();
    }
    
    //Generate a new wall, with each rock having a health value between 1 and 3.
    public Rock[][] generateNewWall() {
        wall = new Rock[WALL_HEIGHT][WALL_WIDTH];
        for (int i = 0; i < wall.length; i++) {
            for (int j = 0; j < wall[i].length; j++) {
                wall[i][j] = new Rock(r.nextInt(1, 4));
            }
        }
        return wall;
    }

    private void generateTreasures() {
        treasures = new ArrayList<Treasures>();
        for (int i = 0; i < r.nextInt(2, 5); i++) {
            Class<? extends Treasures> treasure = possibleTreasures.get(r.nextInt(0, possibleTreasures.size()));
            try {
                treasures.add(treasure.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  
    }

    private void addTreasuresToTreasureLayer() {
        int row, col;
        boolean success;
        for (Treasures treasure : treasures) {
            do {
                row = r.nextInt(0, WALL_HEIGHT);
                col = r.nextInt(0, WALL_WIDTH);
                success = treasureLayer.addTreasure(treasure, row, col);
            } while (!success);
        } 
    }


    private void populatePossibleTreasures() {
        possibleTreasures.add(RedSphereSmall.class);
        possibleTreasures.add(RedSphereLarge.class);
        possibleTreasures.add(BlueSmallSphere.class);
        possibleTreasures.add(BlueSphereLarge.class);
        possibleTreasures.add(HeartScale.class);
        possibleTreasures.add(PrismSphereSmall.class);
        possibleTreasures.add(PrismSphereLarge.class);
        possibleTreasures.add(PaleSphereSmall.class);
        possibleTreasures.add(PaleSphereLarge.class);
        possibleTreasures.add(GreenSphereSmall.class);
        possibleTreasures.add(GreenSphereLarge.class);
    }

    public Rock[][] getWall() { return wall; }
    public List<Treasures> getTreasures() { return treasures; }
    public TreasureLayer getTreasureLayer() { return treasureLayer; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (Rock[] row : wall) {
            for (Rock rock : row) {
                sb.append(rock.getHealth() + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
