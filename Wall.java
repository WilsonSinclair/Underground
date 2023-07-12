import java.util.Random;

public class Wall {
    
    private Rock[][] wall;

    public Wall() {
        wall = generateNewWall();
    }
    
    //Generate a new wall, with each rock having a health value between 1 and 3.
    public Rock[][] generateNewWall() {
        Random r = new Random();
        wall = new Rock[13][10];
        for (int i = 0; i < wall.length; i++) {
            for (int j = 0; j < wall[i].length; j++) {
                wall[i][j] = new Rock(r.nextInt(1, 4));
            }
        }
        return wall;
    }

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
