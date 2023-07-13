import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Wall {
    
    private Rock[][] wall;
    private ArrayList<Treasures> treasures;
    private Random r;
    private List<Class<? extends Treasures>> possibleTreasures = new ArrayList<Class<? extends Treasures>>();
    
    //Upon creation, creates a new randomly generated Wall.
    public Wall() {
        r = new Random();
        wall = generateNewWall();
        
        populatePossibleTreasures();
        generateTreasures();
    }
    
    //Generate a new wall, with each rock having a health value between 1 and 3.
    public Rock[][] generateNewWall() {
        wall = new Rock[13][10];
        for (int i = 0; i < wall.length; i++) {
            for (int j = 0; j < wall[i].length; j++) {
                wall[i][j] = new Rock(r.nextInt(1, 4));
            }
        }
        return wall;
    }

    public void generateTreasures() {
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

    private void populatePossibleTreasures() {
        possibleTreasures.add(RedSphereSmall.class);
        possibleTreasures.add(RedSphereLarge.class);
    }

    public Rock[][] getWall() { return wall; }
    public List<Treasures> getTreasures() { return treasures; }

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
