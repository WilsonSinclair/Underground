import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Point;

public class CursorManager {

    private Image squareCrossHair;

    private Cursor crossHairCursor, handCursor;

    private Toolkit toolkit;

    private GamePanel gp;

    public CursorManager(GamePanel gp) {
        this.gp = gp;
        toolkit = Toolkit.getDefaultToolkit();
        squareCrossHair = toolkit.getImage("../res/square_crosshair.png");
        crossHairCursor = toolkit.createCustomCursor(squareCrossHair, new Point(32, 32), "square cross hair");

        handCursor = new Cursor(Cursor.HAND_CURSOR);
    }

    public void setCrossHairCursor() {    
       gp.setCursor(crossHairCursor); 
    }

    public void setHandCursor() {
        gp.setCursor(handCursor);
    }

    public void update() {
    }
}
