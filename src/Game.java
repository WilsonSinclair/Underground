public class Game {

    private static Wall wall;
    private static Tools currentTool; 

    public Game() {
        currentTool = Tools.Hammer;
        wall = new Wall();
        new Window();
        System.out.println(wall.toString());
    }
    
    public static Wall getCurrentWall() {
        return wall;
    }

    public static Tools getCurrentTool() {
        return currentTool;
    }

    public static void setCurrentTool(int tool) {
        if (tool == 1) {
            currentTool = Tools.Hammer;
        }
        else {
            currentTool = Tools.Pickaxe;
        }
        System.out.println("Tool changed to " + currentTool);
    }

    public static void hitWall(int wallX, int wallY, Tools currentTool) {
        //Get col and row
        int row = wallY / GamePanel.getTileSize();
        int col = wallX / GamePanel.getTileSize();

        System.out.println("Row, Col: (" + row + ", " + col + ")");
        
        if (currentTool == Tools.Hammer) {
            wall.doDamage(row, col, 1);
            if (col > 0) {
                wall.doDamage(row, col - 1, 1);
            }
            if (col < Wall.WALL_WIDTH - 1) {
                wall.doDamage(row, col + 1, 1);
            }
            if (row > 0) {
                wall.doDamage(row - 1, col, 1);
            }
            if (row < Wall.WALL_HEIGHT - 1) {
                wall.doDamage(row + 1, col, 1);
            }
        }
        else {
            wall.doDamage(row, col, 1);
        }
    }

    public static void update() {
        if (wall.getWallHealth() <= 0) {
            wall.generateNewWall();
        }
    }
}
