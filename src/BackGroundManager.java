import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class BackGroundManager {
    
    private Image wall100Health;
    private Image toolPickaxe;
    private Image toolHammer;

    private Toolkit toolkit;

    public BackGroundManager() {
        toolkit = Toolkit.getDefaultToolkit();
        wall100Health = toolkit.getImage("../res/underground_background.png");
        toolPickaxe = toolkit.getImage("../res/pickaxe.png");
        toolHammer = toolkit.getImage("../res/hammer.png");
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(wall100Health, 0, 0, null);
        g2d.drawImage(toolHammer, 860, 200, null);
        g2d.drawImage(toolPickaxe, 860, 400, null);
    }
}
