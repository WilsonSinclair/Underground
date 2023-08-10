import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Wall {

    public static final int WALL_HEIGHT = 10;
    public static final int WALL_WIDTH = 13;

    private int wallHealth;
    
    private Rock[][] wall;
    private TreasureLayer treasureLayer;

    private ArrayList<Treasures> treasures;
    private Random r;

    private List<Class<? extends Treasures>> possibleTreasures = new ArrayList<Class<? extends Treasures>>();

    private boolean requiresUpdate;
    
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
        wallHealth = 50;
        requiresUpdate = true;

        for (int i = 0; i < wall.length; i++) {
            for (int j = 0; j < wall[i].length; j++) {
                wall[i][j] = new Rock(r.nextInt(1, 4));
            }
        }
        return wall;
    }
    
    //At the moment, each treasures has an equal chance of being generated, but in the future the Treasure's rarity
    //should dictate how likely it is to be generated.
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

    public void doDamage(int row, int col, int damage) { 
        wall[row][col].setHealth(wall[row][col].getHealth() - damage); 
        wallHealth -= damage; 

        //the wall has been damaged and now needs to be redrawn to reflect those changes.
        requiresUpdate = true;
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

    public Rock[][] getRocks() { return wall; }
    public List<Treasures> getTreasures() { return treasures; }
    public TreasureLayer getTreasureLayer() { return treasureLayer; }
    public int getWallHealth() { return wallHealth; }

    public boolean requiresUpdate() { return requiresUpdate; }
    public void setUpdatedStatus(boolean status) { requiresUpdate = status; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (Rock[] row : wall) {
            for (Rock rock : row) {
                sb.append(rock.getHealth() + " ");
            }
            sb.append("\n");
        }
        for (Treasures treasure : treasures) {
            sb.append(treasure.getName() + "\n");
        }
        return sb.toString();
    }
}
