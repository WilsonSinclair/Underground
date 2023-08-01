public class Game {

    private static Wall wall;
    private static Tools currentTool; // 1 for hammer, 2 for pickaxe

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
        int col = wallY / GamePanel.getTileSize();
        int row = wallX / GamePanel.getTileSize();
        int currentRockHealth = wall.getRocks()[col][row].getHealth();

        System.out.println("Row, Col: (" + row + ", " + col + ")");
        
        if (currentTool == Tools.Hammer) {
            wall.getRocks()[col][row].setHealth(currentRockHealth - 3);
        }
        else {
            wall.getRocks()[col][row].setHealth(currentRockHealth - 1);
        }
    }
}
