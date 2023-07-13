public class Main {

    public static void main(String[] args) {
        Wall wall = new Wall();
        System.out.println(wall.toString());
        
        for (Treasures treasure : wall.getTreasures()) {
            System.out.println(treasure.getName());
        }
        
    }
}
