public class Game {

    private static Wall wall;
    private static int currentTool; // 1 for hammer, 2 for pickaxe

    public Game() {
        currentTool = 1;
        wall = new Wall();
        new Window();
        System.out.println(wall.toString());
    }
    
    public static Wall getCurrentWall() {
        return wall;
    }

    public static int getCurrentTool() {
        return currentTool;
    }

    public static void hitWall(int wallX, int wallY, int currentTool) {
        //Get col and row
        int col = wallY / GamePanel.getTileSize();
        int row = wallX / GamePanel.getTileSize();
        int currentRockHealth = wall.getRocks()[col][row].getHealth();

        System.out.println("Row, Col: (" + row + ", " + col + ")");
        
        if (currentTool == 1) {
            wall.getRocks()[col][row].setHealth(currentRockHealth - 3);
        }
        else {
            wall.getRocks()[row][col].setHealth(currentRockHealth - 1);
        }
    }
}
