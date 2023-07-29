import java.awt.Image;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Point;

public class CursorManager {

    private Image squareCrossHair;

    private Cursor crossHairCursor;

    private Toolkit toolkit;

    private GamePanel gp;

    public CursorManager(GamePanel gp) {
        this.gp = gp;
        toolkit = Toolkit.getDefaultToolkit();
        squareCrossHair = toolkit.getImage("../res/square_crosshair.png");
        crossHairCursor = toolkit.createCustomCursor(squareCrossHair, new Point(0, 0), "square cross hair");
    }

    public void setCrossHairCursor() {    
       gp.setCursor(crossHairCursor); 
    }
}
