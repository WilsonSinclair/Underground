public class TreasureLayer {
    
    private Treasures[][] treasures;

    public TreasureLayer() {
        treasures = new Treasures[Wall.WALL_HEIGHT][Wall.WALL_WIDTH];
    }

    public boolean addTreasure(Treasures treasure, int row, int col) {
        if (treasure == null) {
            System.out.println("Cannot add null treasure");
            return false;
        }
        
        //If the desired location cannot fit the treasure, return false
        if (row + treasure.getHeight() > Wall.WALL_HEIGHT || col + treasure.getWidth() > Wall.WALL_WIDTH) {
            System.out.println(treasure.getName() + " cannot fit at [" + row + ", " + col + "]" );
            return false;
        }

        //If any of the positions contain a treasure already, return false
        for (int i = row; i < row + treasure.getHeight(); i++) {
            for (int j = col; j < col + treasure.getWidth(); j++) {
                if (containsTreasure(i, j)) {
                    System.out.println("Wall already contains treasure at [" + i + ", " + j + "]");
                    return false;
                } 
            }
        }
        
        //Place the treasures
        for (int i = row; i < row + treasure.getHeight(); i++) {
            for (int j = col; j < col + treasure.getWidth(); j++) {
                treasures[i][j] = treasure;
            }
        }
        return true;
    }

    public boolean containsTreasure(int row, int col) {
        return treasures[row][col] != null;
    }

    public Treasures[][] getTreasures() { return treasures; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < Wall.WALL_HEIGHT; row++) {
            for (int col = 0; col < Wall.WALL_WIDTH; col++) {
                if (containsTreasure(row, col)) {
                    sb.append("X ");
                }
                else {
                    sb.append("- ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
