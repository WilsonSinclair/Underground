import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class BackGroundManager {
    
    private Image wall100Health;
    private Toolkit toolkit;

    public BackGroundManager() {
        toolkit = Toolkit.getDefaultToolkit();
        wall100Health = toolkit.getImage("../res/underground_background.png");
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(wall100Health, 0, 0, null);
    }
    
}
