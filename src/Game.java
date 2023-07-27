public class Game {

    private static Wall wall = new Wall();

    public Game() {
        new Window();
        System.out.println(wall.toString());
    }
    
    public static Wall getCurrentWall() {
        return wall;
    }
}
